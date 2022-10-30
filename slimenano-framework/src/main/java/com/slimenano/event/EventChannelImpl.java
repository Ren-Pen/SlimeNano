package com.slimenano.event;

import com.slimenano.api.annotation.Framework;
import com.slimenano.api.annotation.Listener;
import com.slimenano.api.annotation.Subscribe;
import com.slimenano.api.event.EventChannel;
import com.slimenano.api.event.SubscribeType;
import com.slimenano.api.event.types.Event;
import com.slimenano.api.exception.bot.EventPropagationException;
import com.slimenano.api.logger.Marker;
import com.slimenano.utils.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.PreDestroy;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

@Framework
@Slf4j
@Marker("事件总线")
public class EventChannelImpl implements EventChannel, ApplicationListener<ContextRefreshedEvent> {

    private final CopyOnWriteArrayList<MethodMeta> consumer = new CopyOnWriteArrayList<>();
    private final Set<Class<?>> registerObject = ConcurrentHashMap.newKeySet();

    /**
     * 异步事件执行器，最大堆积事件 64
     */
    private final ThreadPoolExecutor postExecutor = new ThreadPoolExecutor(4, 8, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<>(), new NamedThreadFactory("event"));


    @Override
    public void register(Object o) {
        synchronized (EventChannelImpl.class) {
            if (registerObject.contains(o.getClass())) {
                log.warn("一个对象试图重复注册到事件总线 {}", o.getClass());
                return;
            }
            registerObject.add(o.getClass());
            Set<Class<?>> regClasses = new HashSet<>();
            regClasses.add(o.getClass());
            Collections.addAll(regClasses, o.getClass().getAnnotation(Listener.class).target());

            for (Class<?> aClass : regClasses) {
                for (Method method : aClass.getDeclaredMethods()) {
                    method.setAccessible(true);
                    Subscribe subscribe = method.getAnnotation(Subscribe.class);
                    if (subscribe != null) {
                        Class<?> eventType = method.getParameterTypes()[0];
                        if (method.getParameterCount() != 1 || !Event.class.isAssignableFrom(eventType)) {
                            log.error("无效的监听器：{}.{}", aClass, method);
                            continue;
                        }
                        MethodMeta e = new MethodMeta(o, method, eventType, subscribe.priority(), subscribe.type());
                        log.debug("{}", e);
                        consumer.add(e);
                    }

                }
                consumer.sort(MethodMeta::compareTo);
            }

        }

    }

    @Override
    public void unregister(Object o) {
        synchronized (EventChannelImpl.class) {

        }
    }

    @Override
    public void post(Event e) {
        postExecutor.execute(() -> postBlock(e));
    }

    @Override
    public void postBlock(Event e) {

        try {
            consumer.stream().filter(m -> m.eventType().isAssignableFrom(e.getClass()))
                    .forEach(m -> new EventTask(e, m).run());
        } catch (EventPropagationException exception) {
            log.debug("事件传播被终止！", exception);
        }
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        Map<String, Object> beansWithAnnotation = context.getBeansWithAnnotation(Listener.class);
        for (Object o : beansWithAnnotation.values()) {
            register(o);
        }
    }

    @PreDestroy
    public void preDestroy() {
        log.debug("正在停止事件总线...");
        postExecutor.shutdownNow();
    }

    public record MethodMeta(Object o, Method method, Class eventType, int priority,
                             SubscribeType type) implements Comparable<MethodMeta> {

        @Override
        public int compareTo(MethodMeta o) {
            if (o.type() == SubscribeType.OBSERVER && type() == SubscribeType.CONSUMER) {
                return 1;
            } else if (o.type() == SubscribeType.CONSUMER && type() == SubscribeType.OBSERVER) {
                return -1;
            }


            return Integer.compare(o.priority(), priority());
        }

        @Override
        public String toString() {
            String simpleName = eventType.getSimpleName();
            return String.format("[ 监听器  %s.%s(%s %s)  %d  %s ]",
                    method.getDeclaringClass(),
                    method().getName(),
                    simpleName,
                    simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1),
                    priority(),
                    type()
            );
        }
    }
}

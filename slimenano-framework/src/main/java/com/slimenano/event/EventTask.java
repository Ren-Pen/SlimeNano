package com.slimenano.event;

import com.slimenano.api.event.SubscribeType;
import com.slimenano.api.event.types.Event;
import com.slimenano.api.exception.bot.EventPropagationException;
import com.slimenano.api.logger.Marker;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Marker("事件任务")
@Slf4j
public class EventTask implements Runnable {


    private final long timestamp = System.currentTimeMillis();
    private final Event event;
    private final EventChannelImpl.MethodMeta meta;

    public EventTask(Event event, EventChannelImpl.MethodMeta meta) {
        this.event = event;
        this.meta = meta;
    }


    @Override
    public void run() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Thread.currentThread().setContextClassLoader(meta.o().getClass().getClassLoader());
            meta.method().invoke(meta.o(), event);
        } catch (IllegalAccessException e) {
            log.error("错误的方法访问权限，任务无法访问！", e);
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof EventPropagationException e1) {
                if (meta.type() == SubscribeType.CONSUMER)
                    throw e1;
            } else {
                log.error("事件方法执行出现异常", e.getCause());
            }
        } finally {
            Thread.currentThread().setContextClassLoader(classLoader);
        }
    }

    @Override
    public String toString() {
        return String.format("[ 任务 事件类型:%s 创建时间:%d ]", event.getClass(), timestamp);
    }
}

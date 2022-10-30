package com.slimenano.spring;

import com.slimenano.SlimeNanoApplication;
import com.slimenano.api.event.EventChannel;
import com.slimenano.test.TestEvent;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SlimeNanoSpringApplication {

    private static AnnotationConfigApplicationContext context;

    public static void start(Class<?> startup){
        context = new AnnotationConfigApplicationContext(startup);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {

            context.close();
            LogManager.shutdown();

        }, "cleanUp"));


        EventChannel channel = context.getBean(EventChannel.class);
        channel.post(new TestEvent("testEvent!"));
    }

}

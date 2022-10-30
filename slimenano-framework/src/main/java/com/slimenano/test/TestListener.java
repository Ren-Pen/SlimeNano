package com.slimenano.test;

import com.slimenano.api.annotation.Framework;
import com.slimenano.api.annotation.Listener;
import com.slimenano.api.annotation.Subscribe;
import com.slimenano.api.event.SubscribeType;
import com.slimenano.api.logger.Marker;
import lombok.extern.slf4j.Slf4j;

@Listener(target = TestListener.class)
@Slf4j
@Marker("测试事件")
@Framework
public class TestListener {

    @Subscribe(type = SubscribeType.OBSERVER)
    public void onTestEvent(TestEvent e) {
        log.info("观察者");
    }

    @Subscribe(priority = 9999)
    public void newest(TestEvent e) {
        log.info("最开始的");
    }

    @Subscribe(priority = 9998)
    public void stop(TestEvent e) {
        log.info("终止线程的");
        e.stopPropagation();
    }

    @Subscribe(priority = 9997)
    public void end(TestEvent e) {
        log.info("最后的");
    }

    @Subscribe(type = SubscribeType.OBSERVER)
    public void endWatch(TestEvent e) {
        log.info("观察者最后的");
    }

}

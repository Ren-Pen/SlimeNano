package com.slimenano.api.event;

public enum SubscribeType {

    CONSUMER("消费者"),
    OBSERVER("观察者");

    private final String title;

    SubscribeType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

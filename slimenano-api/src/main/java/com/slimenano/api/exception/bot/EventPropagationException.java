package com.slimenano.api.exception.bot;

public final class EventPropagationException extends RuntimeException {

    public EventPropagationException() {
        super("事件传播被终止");
    }
}

package com.slimenano.api.event.types;

public abstract non-sealed class SysEvent<S> extends Event<S> {

    public SysEvent(S source) {
        super(source);
    }
}

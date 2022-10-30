package com.slimenano.api.event.types;

public abstract non-sealed class CustomEvent<S> extends Event<S> {

    public CustomEvent(S source){
        super(source);
    }

}

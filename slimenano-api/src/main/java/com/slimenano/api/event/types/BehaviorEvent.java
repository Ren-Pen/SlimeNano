package com.slimenano.api.event.types;

import com.slimenano.api.interactive.Interactive;

public abstract non-sealed class BehaviorEvent<S extends Interactive> extends Event<S> {

    public BehaviorEvent(S interactive){
        super(interactive);
    }

}

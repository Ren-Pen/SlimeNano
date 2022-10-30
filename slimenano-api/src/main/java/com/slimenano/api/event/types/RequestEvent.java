package com.slimenano.api.event.types;

import com.slimenano.api.interactive.Interactive;

public abstract non-sealed class RequestEvent<S extends Interactive> extends Event<S> {

    public abstract void response(int flag);

    public RequestEvent(S interactive){
        super(interactive);
    }

}

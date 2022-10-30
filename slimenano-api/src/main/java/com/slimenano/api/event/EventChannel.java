package com.slimenano.api.event;

import com.slimenano.api.event.types.Event;

public interface EventChannel {

    public void register(Object o);

    public void unregister(Object o);

    public void post(Event e);

    public void postBlock(Event e);

}

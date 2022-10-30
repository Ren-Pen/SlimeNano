package com.slimenano.test;

import com.slimenano.api.event.types.CustomEvent;
import com.slimenano.api.event.types.SysEvent;

public class TestEvent extends CustomEvent<Object> {
    public TestEvent(Object source) {
        super(source);
    }

    public static class SubTestEvent extends TestEvent{

        public SubTestEvent(Object source) {
            super(source);
        }
    }
}

package com.slimenano.api.event.types;


import com.slimenano.api.exception.bot.EventPropagationException;

public sealed abstract class Event<S>
        permits SysEvent, MessageEvent, BehaviorEvent, RequestEvent, CustomEvent {

    /**
     * 事件触发原始对象
     */
    private final S source;


    public Event(S source){
        this.source = source;
    }

    public S getSource(){
        return this.source;
    }

    /**
     * 阻止事件传播
     */
    public void stopPropagation(){
        throw new EventPropagationException();
    }

}

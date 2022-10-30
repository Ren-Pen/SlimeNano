package com.slimenano.api.event.types;

import com.slimenano.api.interactive.user.User;
import com.slimenano.api.message.MessageChain;

public abstract non-sealed class MessageEvent<S extends User> extends Event<S> {

    private final MessageChain chain;

    public MessageEvent(S user, MessageChain chain){
        super(user);
        this.chain = chain;
    }

    public MessageChain getChain(){
        return chain;
    }

    public S getFrom(){
        return this.getSource();
    }

}

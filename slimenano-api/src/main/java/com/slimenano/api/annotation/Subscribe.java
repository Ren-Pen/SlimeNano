package com.slimenano.api.annotation;

import com.slimenano.api.event.SubscribeType;

import java.lang.annotation.*;

import static com.slimenano.api.event.SubscribeType.CONSUMER;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {

    int priority() default 0;

    SubscribeType type() default CONSUMER;

}

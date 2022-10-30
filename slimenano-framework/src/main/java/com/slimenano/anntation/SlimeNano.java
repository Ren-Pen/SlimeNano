package com.slimenano.anntation;

import com.slimenano.api.annotation.Framework;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

@Configuration
@ComponentScan
@Framework
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface SlimeNano {
}

package com.slimenano.api.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 标识一个 bean 是属于框架的 bean，插件扫描包如果与框架包重名则会跳过这些 bean 以避免重复加载
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Framework {
    @AliasFor(annotation = Component.class)
    String value() default "";
}

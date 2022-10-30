package com.slimenano.api.annotation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 标记一个监听器，可以被事件总线注册
 * 被监听器标记的类，其子类也将被是为监听器
 * 使用target设置扫描层次，默认的，事件总线只会扫描当前类中的订阅方法，若要扫描父类的方法即可在target中添加父类类型
 */
@Scope("singleton")
@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Listener {
    Class<?>[] target() default {};
}

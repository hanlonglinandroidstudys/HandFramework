/*
 * Copyright (c) 2019/4/1
 * Create at 0:17:9
 * Wriitten by hanlonglin
 */

package hanlonglin.com.eventbusframwork.eventbus.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Subscrib {
    ThreadMode threadMode() default ThreadMode.POSTING;
}

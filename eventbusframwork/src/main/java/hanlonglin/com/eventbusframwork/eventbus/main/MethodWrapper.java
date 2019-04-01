/*
 * Copyright (c) 2019/4/1
 * Create at 1:5:32
 * Wriitten by hanlonglin
 */

package hanlonglin.com.eventbusframwork.eventbus.main;

import java.lang.reflect.Method;

import hanlonglin.com.eventbusframwork.eventbus.annotation.ThreadMode;

public class MethodWrapper {
    //方法参数类型
    Class<?> clazz;
    //方法本身
    Method method;
    //注解参数 模式
    ThreadMode thredMode;

    public MethodWrapper(Class<?> clazz, Method method, ThreadMode thredMode) {
        this.clazz = clazz;
        this.method = method;
        this.thredMode = thredMode;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public ThreadMode getThredMode() {
        return thredMode;
    }

    public void setThredMode(ThreadMode thredMode) {
        this.thredMode = thredMode;
    }
}

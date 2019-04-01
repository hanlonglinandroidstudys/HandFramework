/*
 * Copyright (c) 2019/4/1
 * Create at 0:18:22
 * Wriitten by hanlonglin
 */

package hanlonglin.com.eventbusframwork.eventbus.annotation;

public enum  ThreadMode {
    /**
     * 发布和接受消息在统一线程
     */
    POSTING,

    /**
     * 接受消息在主线程
     */
    MAIN,

    /**
     * 接受消息在新的线程，非主线程
     */
    BACKGROUND


}

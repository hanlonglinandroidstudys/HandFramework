/*
 * Copyright (c) 2019/4/1
 * Create at 0:22:3
 * Wriitten by hanlonglin
 */

package hanlonglin.com.eventbusframwork.eventbus.main;

import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hanlonglin.com.eventbusframwork.eventbus.annotation.Subscrib;
import hanlonglin.com.eventbusframwork.eventbus.annotation.ThreadMode;

public class EventBus {

    private static EventBus instance;

    public static EventBus getInstance() {
        if (instance == null) {
            synchronized (EventBus.class) {
                if (instance == null) {
                    instance = new EventBus();
                }
            }
        }
        return instance;
    }

    private EventBus() {

    }

    /**
     * 完成两件事
     * 1.注册事件
     * 2.发布事件
     */

    /**
     * 事件map
     * object activity
     * list<?> 所有方法
     */
    HashMap<Object, List<MethodWrapper>> eventMap = new HashMap<>();

    Handler handler=new Handler(Looper.getMainLooper());

    /**
     * 注册
     * 在actiivty onCreate中调用
     * @param obj
     */
    public void register(Object obj) {
        List<MethodWrapper> methodWrappers = eventMap.get(obj);
        if(methodWrappers==null){
            methodWrappers=getSubscribMethods(obj);
            eventMap.put(obj,methodWrappers);
        }
    }

    /**
     * 注销
     * 在activity onDestory()中调用
     * @param obj
     */
    public void unregister(Object obj){
        eventMap.remove(obj);
    }

    /**
     * 发送消息
     * @param evobj
     */
    public void post(Object evobj){
        for(Object object:eventMap.keySet()){
            List<MethodWrapper> methodWrappers = eventMap.get(object);
            if(methodWrappers!=null) {
                for (MethodWrapper methodWrapper : methodWrappers) {
                    if (methodWrapper.getClazz().isAssignableFrom(evobj.getClass())) {
                        //参数类型（事件类型）相同 ，则触发订阅者
                        performSubscrib(methodWrapper,object,evobj);
                    }
                }
            }
        }
    }

    /**
     *
     * @param methodWrapper
     * @param object
     * @param evobj
     */
    private void performSubscrib(final MethodWrapper methodWrapper, final Object object, final Object evobj) {
        switch (methodWrapper.thredMode){
            case POSTING:
                try {
                    methodWrapper.getMethod().invoke(object,evobj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                break;
            case MAIN:
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            methodWrapper.getMethod().invoke(object,evobj);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case BACKGROUND:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            methodWrapper.getMethod().invoke(object,evobj);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }

    }

    /**
     *
     * @param obj
     * @return
     */
    private List<MethodWrapper> getSubscribMethods(Object obj) {
        List<MethodWrapper> list = new ArrayList<>();
        //Method[] methods = obj.getClass().getMethods();                 //获取类中所有方法 包括本身的方法和父类中的所有方法
        Method[] declaredMethods = obj.getClass().getDeclaredMethods();   //只获取本身声明的方法
        for (Method method : declaredMethods) {
            Subscrib annotation = method.getAnnotation(Subscrib.class);
            if (annotation == null)
                continue;
            //进行更严格的交验
            //效验返回值是否为void

            //效验参数数量是不是一个

            //获取
            Class<?>[] parameterTypes = method.getParameterTypes();
            ThreadMode threadMode = annotation.threadMode();
            MethodWrapper methodWrapper=new MethodWrapper(parameterTypes[0],method,threadMode);
            list.add(methodWrapper);
        }
        return list;
    }
}

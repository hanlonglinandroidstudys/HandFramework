/*
 * Copyright (c) 2019/3/26
 * Create at 7:26:3
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.lifecircle;

import hanlonglin.com.glideframework.handglide.cache.DoubleBitmapCache;

public class LifecircleObservable {

    private static LifecircleObservable instance;

    public static LifecircleObservable getInstance() {
        if(instance==null){
            synchronized (LifecircleObservable.class){
                if(instance==null)
                    instance=new LifecircleObservable();
            }
        }
        return instance;
    }

    private LifecircleObservable(){}

    public void onStart(int activityCode){

    }

    public void onStop(int activityCode){

    }

    public void onDestory(int activityCode){

        //清除对应activity的内存缓存
        DoubleBitmapCache.getInstance().remove(activityCode);
    }
}

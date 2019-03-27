/*
 * Copyright (c) 2019/3/26
 * Create at 3:6:39
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.cache;

import android.graphics.Bitmap;

import hanlonglin.com.glideframework.handglide.request.BitmapRequest;

public class DiskBitmapCache implements BitmapCache {

    static DiskBitmapCache instance;
    public static DiskBitmapCache getInstance(){
        if(instance==null){
            synchronized (DiskBitmapCache.class){
                if(instance==null){
                    instance=new DiskBitmapCache();
                }
            }
        }
        return instance;
    }
    private DiskBitmapCache(){

    }
    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {

    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return null;
    }

    @Override
    public void remove(BitmapRequest request) {

    }

    @Override
    public void remove(int activityCode) {

    }
}

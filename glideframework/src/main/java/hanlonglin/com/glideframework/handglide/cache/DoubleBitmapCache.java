/*
 * Copyright (c) 2019/3/26
 * Create at 3:30:22
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.cache;

import android.graphics.Bitmap;

import hanlonglin.com.glideframework.handglide.request.BitmapRequest;

public class DoubleBitmapCache implements BitmapCache {

    private static DoubleBitmapCache instance;

    public static DoubleBitmapCache getInstance() {
        if (instance == null) {
            synchronized (DoubleBitmapCache.class) {
                if (instance == null)
                    instance = new DoubleBitmapCache();
            }
        }
        return instance;
    }

    private DoubleBitmapCache() {
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        DiskBitmapCache.getInstance().put(request, bitmap);
        MemoryLruCache.getInstance().put(request, bitmap);
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        Bitmap bitmap = null;
        bitmap = MemoryLruCache.getInstance().get(request);
        if (bitmap != null)
            return bitmap;
        bitmap = DiskBitmapCache.getInstance().get(request);
        if (bitmap != null)
            return bitmap;
        return bitmap;
    }

    @Override
    public void remove(BitmapRequest request) {
        MemoryLruCache.getInstance().remove(request);
        DiskBitmapCache.getInstance().remove(request);
    }

    @Override
    public void remove(int activityCode) {
        MemoryLruCache.getInstance().remove(activityCode);
        DiskBitmapCache.getInstance().remove(activityCode);
    }
}

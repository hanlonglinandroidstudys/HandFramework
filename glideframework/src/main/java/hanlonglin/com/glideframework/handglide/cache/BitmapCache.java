/*
 * Copyright (c) 2019/3/26
 * Create at 1:37:41
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.cache;

import android.graphics.Bitmap;

import hanlonglin.com.glideframework.handglide.request.BitmapRequest;

public interface BitmapCache {

    /**
     * 存入
     * @param request
     * @param bitmap
     */
    void put(BitmapRequest request, Bitmap bitmap);

    /**
     * 获取
     * @param request
     * @return
     */
    Bitmap get(BitmapRequest request);

    /**
     * 清除缓存的图片
     * @param request
     */
    void remove(BitmapRequest request);

    /**
     * 清除所属的activity图片
     * @param activityCode
     */
    void remove(int activityCode);

}

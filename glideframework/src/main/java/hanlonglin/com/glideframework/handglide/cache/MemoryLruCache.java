/*
 * Copyright (c) 2019/3/26
 * Create at 1:37:14
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import hanlonglin.com.glideframework.handglide.request.BitmapRequest;

public class MemoryLruCache implements BitmapCache {

    private LruCache<String, Bitmap> LruCacheBitmap;

    // key    url
    // value  activityCode
    // 如果一个actiivty销毁了，先从HashMapActivity中找到key,即url地址，然后再从LruCacheBitmap中找到url，找到bitmap,将他销毁
    private HashMap<String, Integer> HashMapActivity;

    private final static int LRU_CACHE_SIZE = 1024 * 1024 * 100;  //10M

    static MemoryLruCache instance;

    public static MemoryLruCache getInstance() {
        if (instance == null) {
            synchronized (MemoryLruCache.class) {
                if (instance == null)
                    instance = new MemoryLruCache();
            }
        }
        return instance;
    }

    private MemoryLruCache() {
        LruCacheBitmap = new LruCache<>(LRU_CACHE_SIZE);
        HashMapActivity = new HashMap<>();
    }

    @Override
    public void put(BitmapRequest request, Bitmap bitmap) {
        if (bitmap != null) {
            LruCacheBitmap.put(request.getUrlMD5(), bitmap);
            HashMapActivity.put(request.getUrlMD5(),request.getContext().hashCode());
        }
    }

    @Override
    public Bitmap get(BitmapRequest request) {
        return LruCacheBitmap.get(request.getUrlMD5());
    }

    @Override
    public void remove(BitmapRequest request) {
        LruCacheBitmap.remove(request.getUrlMD5());
    }

    @Override
    public void remove(int activityCode) {
        //找到actiivty对应的url地址集合
        List<String> tempUrlList=new ArrayList<>();
        for(String urlMd5:HashMapActivity.keySet()){
            if(HashMapActivity.get(urlMd5)==activityCode){
                tempUrlList.add(urlMd5);
            }
        }
        //清除 回收内存
        for(String urlMd5:tempUrlList){
            HashMapActivity.remove(urlMd5);
            Bitmap bitmap=LruCacheBitmap.get(urlMd5);
            if(bitmap!=null && !bitmap.isRecycled()){
                bitmap.recycle();
            }
            bitmap=null;

            LruCacheBitmap.remove(urlMd5);
        }
        //触发gc
        if(!tempUrlList.isEmpty()){
            System.gc();
        }

    }
}

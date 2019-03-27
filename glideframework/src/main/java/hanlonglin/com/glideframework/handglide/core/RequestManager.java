/*
 * Copyright (c) 2019/3/26
 * Create at 10:5:8
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.core;

import android.util.Log;

import java.util.concurrent.LinkedBlockingDeque;

import hanlonglin.com.glideframework.handglide.listener.RequestListener;
import hanlonglin.com.glideframework.handglide.request.BitmapRequest;

public class RequestManager {
    private static final String TAG = "RequestManager";
    private static RequestManager ourInstance = null;

    boolean isStart=false; //是否启动

    public static RequestManager getInstance() {
        if (ourInstance == null) {
            synchronized (RequestListener.class) {
                if (ourInstance == null)
                    ourInstance = new RequestManager();
            }
        }
        return ourInstance;
    }

    private RequestManager() {
    }

    //核心线程
    BitmapDispatcher bitmapDispatchers[];
    //等待队列
    LinkedBlockingDeque<BitmapRequest> queue = new LinkedBlockingDeque<>();

    public void addBitmapRequest(BitmapRequest request) {
        if (!queue.contains(request)) {
            queue.add(request);
        } else {
            Log.i(TAG, "不能添加重复请求！");
        }
    }

    public void start() {
        if(isStart){
            Log.i(TAG, "图片加载引擎已经启动！");
            return;
        }
        Log.i(TAG, "启动图片加载引擎！");
        int threadCount = Runtime.getRuntime().availableProcessors();
        bitmapDispatchers = new BitmapDispatcher[threadCount];
        for (BitmapDispatcher bitmapDispatcher : bitmapDispatchers) {
            bitmapDispatcher = new BitmapDispatcher(queue);
            bitmapDispatcher.start();
        }
        isStart=true;
    }

    public void stop() {
        if(!isStart){
            Log.i(TAG, "图片加载引擎已经停止！");
            return;
        }
        Log.i(TAG, "关闭图片加载引擎！");
        for (BitmapDispatcher bitmapDispatcher : bitmapDispatchers) {
            if (bitmapDispatcher != null) {
                if (!bitmapDispatcher.isInterrupted())
                    bitmapDispatcher.interrupt();
            }
        }
        isStart=false;
    }
}

/*
 * Copyright (c) 2019/3/27
 * Create at 11:45:58
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.core1;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import hanlonglin.com.glideframework.handglide.request.BitmapRequest;
import hanlonglin.com.glideframework.handglide.utils.LogUtil;

/**
 * 线程池实现
 */
public class RequestManager2 {

    private static RequestManager2 instance;

    public static RequestManager2 getInstance() {
        if (instance == null) {
            synchronized (RequestManager2.class) {
                if (instance == null)
                    instance = new RequestManager2();
            }
        }
        return instance;
    }

    ThreadPoolExecutor threadPoolExecutor;
    LinkedBlockingDeque<BitmapRequest> queue = new LinkedBlockingDeque<>();
    boolean isStart=false;

    private RequestManager2() {
        threadPoolExecutor = new ThreadPoolExecutor(4, 20, 15, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(8), rejectedExecutionHandler);
    }

    RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            threadPoolExecutor.execute(r);
        }
    };

    public void addBitmapRequest(BitmapRequest request) {
        try {
            if (!queue.contains(request))
                queue.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if(isStart){
            LogUtil.log(getClass().getSimpleName()+" 加载引擎已经启动");
            return;
        }
        //
        threadPoolExecutor.execute(MainLoop);
        isStart=true;
    }

    public void stop() {
        if(!isStart){
            LogUtil.log(getClass().getSimpleName()+" 加载引擎已经停止");
            return;
        }
        //

        isStart=false;
    }

    Runnable MainLoop=new Runnable() {
        @Override
        public void run() {
          while(true){
              try {
                  BitmapRequest request = queue.take();
                  BitmapTask bitmapTask=new BitmapTask(request);
                  threadPoolExecutor.execute(bitmapTask);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
        }
    };
}

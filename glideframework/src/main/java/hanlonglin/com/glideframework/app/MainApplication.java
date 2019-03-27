/*
 * Copyright (c) 2019/3/27
 * Create at 9:3:19
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.app;

import android.app.Application;

import hanlonglin.com.glideframework.handglide.core.RequestManager;
import hanlonglin.com.glideframework.handglide.core1.RequestManager2;
import hanlonglin.com.glideframework.handglide.utils.LogUtil;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.log(getClass().getSimpleName()+" onCreate()");
        RequestManager.getInstance().start();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        LogUtil.log(getClass().getSimpleName()+" onTerminate()");
        RequestManager.getInstance().stop();
    }
}

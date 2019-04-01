/*
 * Copyright (c) 2019/4/1
 * Create at 2:17:29
 * Wriitten by hanlonglin
 */

package hanlonglin.com.eventbusframwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;

import hanlonglin.com.eventbusframwork.eventbus.annotation.Subscrib;
import hanlonglin.com.eventbusframwork.eventbus.annotation.ThreadMode;
import hanlonglin.com.eventbusframwork.eventbus.main.EventBus;
import hanlonglin.com.eventbusframwork.events.XiaoMi;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        EventBus.getInstance().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getInstance().unregister(this);
    }

    @Subscrib(threadMode = ThreadMode.POSTING)
    public void getXiaomimi1(XiaoMi xiaomi){
        Log.e(getClass().getSimpleName(), "background获取小米:name:" + xiaomi.getName() + ",price:" + xiaomi.getPrice() + ",thread:" + Thread.currentThread());
    }

    @Subscrib(threadMode = ThreadMode.MAIN)
    public void getXiaomimi2(XiaoMi xiaomi){
        Log.e(getClass().getSimpleName(), "background获取小米:name:" + xiaomi.getName() + ",price:" + xiaomi.getPrice() + ",thread:" + Thread.currentThread());
    }

    @Subscrib(threadMode = ThreadMode.BACKGROUND)
    public void getXiaomimi3(XiaoMi xiaomi){
        Log.e(getClass().getSimpleName(), "background获取小米:name:" + xiaomi.getName() + ",price:" + xiaomi.getPrice() + ",thread:" + Thread.currentThread());
    }
}

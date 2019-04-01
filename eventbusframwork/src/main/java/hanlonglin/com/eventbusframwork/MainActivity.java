/*
 * Copyright (c) 2019/4/1
 * Create at 0:14:53
 * Wriitten by hanlonglin
 */

package hanlonglin.com.eventbusframwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.BatchUpdateException;

import hanlonglin.com.eventbusframwork.eventbus.annotation.Subscrib;
import hanlonglin.com.eventbusframwork.eventbus.annotation.ThreadMode;
import hanlonglin.com.eventbusframwork.eventbus.main.EventBus;
import hanlonglin.com.eventbusframwork.events.XiaoMi;

public class MainActivity extends AppCompatActivity {

    Button btn_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册activity
        EventBus.getInstance().register(this);

        btn_go = (Button) findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getInstance().unregister(this);
    }

    @Subscrib(threadMode = ThreadMode.POSTING)
    public void getXiaomi1(XiaoMi xiaomi) {
        Log.e(getClass().getSimpleName(), "posting获取小米:name:" + xiaomi.getName() + ",price:" + xiaomi.getPrice() + ",thread:" + Thread.currentThread());
    }

    @Subscrib(threadMode = ThreadMode.MAIN)
    public void getXiaomi2(XiaoMi xiaomi) {
        Log.e(getClass().getSimpleName(), "main获取小米:name:" + xiaomi.getName() + ",price:" + xiaomi.getPrice() + ",thread:" + Thread.currentThread());
    }

    @Subscrib(threadMode = ThreadMode.BACKGROUND)
    public void getXiaomi3(XiaoMi xiaomi) {
        Log.e(getClass().getSimpleName(), "background获取小米:name:" + xiaomi.getName() + ",price:" + xiaomi.getPrice() + ",thread:" + Thread.currentThread());
    }
}

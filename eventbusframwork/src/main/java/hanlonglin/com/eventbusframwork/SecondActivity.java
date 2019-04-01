/*
 * Copyright (c) 2019/4/1
 * Create at 1:47:34
 * Wriitten by hanlonglin
 */

package hanlonglin.com.eventbusframwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hanlonglin.com.eventbusframwork.eventbus.main.EventBus;
import hanlonglin.com.eventbusframwork.events.XiaoMi;

public class SecondActivity extends AppCompatActivity {
    Button btn1, btn2,btn3_goto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3_goto=(Button)findViewById(R.id.btn_go);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getInstance().post(new XiaoMi("小米6",2999));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getInstance().post(new XiaoMi("小米9",3499));
                    }
                }).start();
            }
        });

        btn3_goto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
            }
        });
    }
}

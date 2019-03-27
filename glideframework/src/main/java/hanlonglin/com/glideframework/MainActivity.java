/*
 * Copyright (c) 2019/3/26
 * Create at 8:4:22
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hanlonglin.com.glideframework.handglide.glide.Glide;
import hanlonglin.com.glideframework.handglide.utils.LogUtil;

public class MainActivity extends AppCompatActivity {

    Button btn_goto_lauch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.log(getClass().getSimpleName()+" onCreate()");
        Glide.with(this);

        btn_goto_lauch=(Button)findViewById(R.id.btn_goto);
        btn_goto_lauch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LachuActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.log(getClass().getSimpleName()+" onStart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.log(getClass().getSimpleName()+" onDestory()");
    }
}

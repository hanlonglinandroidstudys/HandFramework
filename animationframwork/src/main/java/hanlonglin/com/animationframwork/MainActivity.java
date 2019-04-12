/*
 * Copyright (c) 2019/4/12
 * Create at 9:49:15
 * Wriitten by hanlonglin
 */

package hanlonglin.com.animationframwork;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import hanlonglin.com.animationframwork.Animation.AnimatorPath;

public class MainActivity extends AppCompatActivity {

    ImageView img_qq;
    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        img_qq = (ImageView) findViewById(R.id.img_qq);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAnimation(img_qq);
            }
        });
        img_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAnimation(v);
            }
        });
    }

    private void performAnimation(View v) {
        AnimatorPath animatorPath = new AnimatorPath();
        animatorPath.moveTo(0, 0);
        animatorPath.curbicTo(-200, 200, -400, 100, -600, 50);
        animatorPath.lineTo(-600, 200);
        animatorPath.startAnimation(v, 2000);
    }
}

/*
 * Copyright (c) 2019/3/26
 * Create at 8:54:55
 * Wriitten by hanlonglin
 * 官方Glide使用
 */

package hanlonglin.com.glideframework;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import hanlonglin.com.glideframework.handglide.glide.Glide;
import hanlonglin.com.glideframework.handglide.listener.RequestListener;
import hanlonglin.com.glideframework.handglide.utils.LogUtil;

//import com.bumptech.glide.Glide;

public class LachuActivity extends Activity {
    Button btn_load_one;
    Button btn_load_many;
    ScrollView scrollView;
    LinearLayout li_scroll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lachu);

        initView();
    }

    private void initView() {
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        li_scroll = (LinearLayout) findViewById(R.id.li_scroll);
        btn_load_one = (Button) findViewById(R.id.btn_load_one);
        btn_load_many = (Button) findViewById(R.id.btn_load_many);
        btn_load_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadOne();
            }
        });
        btn_load_many.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMany();
            }
        });
    }

    //加载一张图片
    private void loadOne() {
        li_scroll.removeAllViews();
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
        imageView.setLayoutParams(layoutParams);
        //showWithGlide("http://pic75.nipic.com/file/20150821/9448607_145742365000_211.jpg",imageView);
        showWithMyGlide("http://pic75.nipic.com/file/20150821/9448607_145742365000_211.jpg",imageView);
        li_scroll.addView(imageView);
    }

    //加载100张图片
    private void loadMany() {
        String urlList[] = {
                "http://pic49.nipic.com/file/20140923/12106414_110747139072_2.jpg",
                "http://pic.qiantucdn.com/58pic/11/01/09/17V58PICsEi.jpg",
                "http://img1.ph.126.net/SSvFbcJzwGBlqy4xon6FjA==/6608832342050415367.jpg",
                "http://pic9.nipic.com/20100812/4922457_105743986611_2.jpg",
                "http://pic31.nipic.com/20130720/5793914_122325176000_2.jpg",
                "http://pic17.nipic.com/20111107/8242808_160158804000_2.jpg",
                "http://pic24.nipic.com/20121023/10744759_103142209315_2.jpg",
                "http://pic49.nipic.com/file/20140926/9422601_102539153000_2.jpg",
                "http://pic41.nipic.com/20140512/8905855_001810317000_2.jpg",
                "http://pic13.nipic.com/20110331/7053919_100607336160_2.jpg",
                "http://pic25.nipic.com/20121205/10197997_003647426000_2.jpg",
                "http://pic34.nipic.com/20131104/13264764_101028322111_2.jpg",
                "http://pic34.nipic.com/20131104/13264764_101028322111_2.jpg"

        };
        li_scroll.removeAllViews();
        timeStart=System.currentTimeMillis();
        for (int i = 0; i < urlList.length; i++) {
            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            imageView.setLayoutParams(layoutParams);
            //showWithGlide(urlList[i],imageView);
            showWithMyGlide(urlList[i],imageView);
            li_scroll.addView(imageView);
        }


    }


    private void showWithGlide(String url,ImageView imageView){
       // Glide.with(this).load(url).placeholder(R.drawable.ic_launcher_background).into(imageView);
    }

    private void showWithMyGlide(String url,ImageView imageView){
        Glide.with(this).placeHolder(R.drawable.ic_launcher_background).load(url).setRequestListener(requestListener).into(imageView);

       // Glide.with(this);
    }

    long timeStart,timeEnd;
    mRequestListener requestListener=new mRequestListener();
    private class mRequestListener implements RequestListener{

        @Override
        public void onComplete() {
            timeEnd=System.currentTimeMillis();
            LogUtil.log("加载用时："+(timeEnd-timeStart));

            /**
             * 线程池方式
             * 网络加载用时4060毫秒
             * 内存加载用时5毫秒
             */
        }

        @Override
        public void onError() {

        }
    }
}

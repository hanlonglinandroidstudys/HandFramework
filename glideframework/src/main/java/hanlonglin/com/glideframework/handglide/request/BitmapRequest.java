/*
 * Copyright (c) 2019/3/26
 * Create at 9:44:31
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.request;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

import hanlonglin.com.glideframework.handglide.core1.RequestManager2;
import hanlonglin.com.glideframework.handglide.lifecircle.RequestFragment;
import hanlonglin.com.glideframework.handglide.core.RequestManager;
import hanlonglin.com.glideframework.handglide.listener.RequestListener;
import hanlonglin.com.glideframework.handglide.utils.LogUtil;
import hanlonglin.com.glideframework.handglide.utils.MD5Util;

public class BitmapRequest {

    String url;           //请求地址
    String urlMD5;        //url转换md5
    int placeHolder;      //默认图片id
    SoftReference<ImageView> softReferenceImage;  //图片软引用
    Context context;
    RequestListener requestListener;

    public BitmapRequest(Context context) {
        this.context = context;
    }

    public BitmapRequest placeHolder(int rid) {
        this.placeHolder = rid;
        return this;
    }

    public BitmapRequest load(String url) {
        this.url = url;
        this.urlMD5 = MD5Util.toMd5(url);
        return this;
    }


    public void into(ImageView imageView) {
        this.softReferenceImage = new SoftReference<>(imageView);
        imageView.setTag(urlMD5); //为了解决图片错位问题
        RequestManager.getInstance().addBitmapRequest(this);
    }

    public BitmapRequest setRequestListener(RequestListener requestListener) {
        this.requestListener = requestListener;
        return this;
    }


    //get


    public int getPlaceHolder() {
        return placeHolder;
    }

    public SoftReference<ImageView> getSoftReferenceImage() {
        return softReferenceImage;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlMD5() {
        return urlMD5;
    }

    public Context getContext() {
        return context;
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }
}

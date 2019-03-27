/*
 * Copyright (c) 2019/3/27
 * Create at 11:45:58
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.core1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import hanlonglin.com.glideframework.handglide.cache.DoubleBitmapCache;
import hanlonglin.com.glideframework.handglide.request.BitmapRequest;


public class BitmapTask implements Runnable {

    private final static String TAG="BitmapTask";
    BitmapRequest request;

    public BitmapTask(BitmapRequest request) {
        this.request = request;
    }

    @Override
    public void run() {
        //显示加载图片
        showPlaceHolder(request);
        //三级缓存中查找bitmap
        Bitmap bitmap=findBitmap(request);
        if(bitmap!=null){
            showBitmapOnView(bitmap,request);
            if(request.getRequestListener()!=null)
                request.getRequestListener().onComplete();
        }else{
            if(request.getRequestListener()!=null)
                request.getRequestListener().onError();
        }
    }

    Handler handler=new Handler(Looper.getMainLooper());

    private void showPlaceHolder(final BitmapRequest request) {
        final ImageView imageView = request.getSoftReferenceImage().get();
        final int resId = request.getPlaceHolder();
        if (imageView == null) {
            Log.e(TAG, "imageView is null");
            return;
        }
        if (resId < 0) {
            Log.e(TAG, "placeHolder is null");
            return;
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageResource(resId);
            }
        });
    }

    private void showBitmapOnView(final Bitmap bitmap, final BitmapRequest request) {
        final ImageView imageView = request.getSoftReferenceImage().get();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (imageView != null && bitmap != null && request.getUrlMD5().equals(imageView.getTag()))
                    imageView.setImageBitmap(bitmap);
            }
        });
    }

    private Bitmap findBitmap(BitmapRequest request) {
        Bitmap bitmap = null;
        //=============去内存中找=====================
        //=============去硬盘中找=====================
        //以上两步放在DoubleBitmapCache
        bitmap = DoubleBitmapCache.getInstance().get(request);
        if (bitmap != null) {
            Log.e(TAG, "二级缓存中找到" + request.getUrl());
            return bitmap;
        }
        //=============网络加载=====================
        bitmap = downloadImage(request.getUrl());
        if (bitmap != null) {
            //设置缓存
            DoubleBitmapCache.getInstance().put(request, bitmap);
            //
            return bitmap;
        }
        return bitmap;
    }


    private Bitmap downloadImage(String url) {
        FileOutputStream fos = null;
        InputStream is = null;
        Bitmap bitmap = null;

        try {
            URL Url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}

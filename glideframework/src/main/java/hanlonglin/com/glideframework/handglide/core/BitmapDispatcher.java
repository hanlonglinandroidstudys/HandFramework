/*
 * Copyright (c) 2019/3/26
 * Create at 10:10:0
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.core;

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
import java.util.concurrent.LinkedBlockingDeque;

import hanlonglin.com.glideframework.handglide.cache.DoubleBitmapCache;
import hanlonglin.com.glideframework.handglide.request.BitmapRequest;

import static android.support.constraint.Constraints.TAG;

public class BitmapDispatcher extends Thread {

    LinkedBlockingDeque<BitmapRequest> queue;
    Handler handler = new Handler(Looper.getMainLooper());

    public BitmapDispatcher(LinkedBlockingDeque<BitmapRequest> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                BitmapRequest request = queue.take();

                //=============显示placeHolder===========================================
                showPlaceHolder(request);
                //=============从依次从内存，硬盘，网络获取图片=============================
                Bitmap bitmap = findBitmap(request);
                if (bitmap != null) {
                    showBitmapOnView(bitmap, request);
                    if (request.getRequestListener() != null)
                        request.getRequestListener().onComplete();
                } else {
                    if (request.getRequestListener() != null)
                        request.getRequestListener().onError();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

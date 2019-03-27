package hanlonglin.com.httpframework.http;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.UnsupportedEncodingException;

import static android.content.ContentValues.TAG;

public class HttpTask<T> implements Runnable {
    IHttpService httpService;
    IHttpListener httpListener;

    public HttpTask(T requestInfo, String url, IHttpService httpService, IHttpListener httpListener) {
        this.httpListener = httpListener;
        this.httpService = httpService;
        httpService.setUrl(url);
        httpService.setHttpCallback(httpListener);

        //把请求信息的对象封装成json格式
        String requestContent = JSON.toJSONString(requestInfo);
        if (requestContent != null) {
            try {
                httpService.setRequestData(requestContent.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void run() {
        httpService.excute();
        Log.e(TAG, "httpService run " );
    }
}

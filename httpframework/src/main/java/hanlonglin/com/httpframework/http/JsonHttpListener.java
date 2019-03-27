package hanlonglin.com.httpframework.http;

import android.os.Handler;
import android.os.Looper;

import com.alibaba.fastjson.JSON;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.LogRecord;

public class JsonHttpListener<M> implements IHttpListener {
    //需要知道用户接受的对象是什么样的类型
    Class<M> responseClass;
    //我们得到的结果按json转换成的对象，怎么样交给调用者
    IDataListener<M> dataListener;
    //用于线程切换
    Handler handler = new Handler(Looper.getMainLooper());

    public JsonHttpListener(Class<M> responseClass, IDataListener<M> dataListener) {
        this.responseClass = responseClass;
        this.dataListener = dataListener;
    }

    @Override
    public void onSuccess(InputStream is) {

        //inputStream转化为字符串
        String content = getContent(is);
        //转为对象
        final M response = JSON.parseObject(content, responseClass);
        //传到主线程
        handler.post(new Runnable() {
            @Override
            public void run() {
                dataListener.onSuccess(response);
            }
        });
    }

    @Override
    public void onFail(final String errMsg) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                dataListener.onFail(errMsg);
            }
        });
    }


    private String getContent(InputStream is) {
        String content = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }
}

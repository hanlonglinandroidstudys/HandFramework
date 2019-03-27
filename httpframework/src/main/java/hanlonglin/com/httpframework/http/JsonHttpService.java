package hanlonglin.com.httpframework.http;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonHttpService implements IHttpService {
    String url = null;
    byte[] requestData;
    IHttpListener httpListener;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }

    @Override
    public void setHttpCallback(IHttpListener httpListener) {
        this.httpListener = httpListener;
    }

    @Override
    public void excute() {
        //真实的网络操作
        doPost();
    }

    private void doPost() {
        //OutputStreamWriter out = null;
        HttpURLConnection conn=null;
        BufferedOutputStream out = null;
        try {
            URL url = new URL(this.url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置为true
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //设置连接超时时间和读取超时时间
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            out = new BufferedOutputStream(conn.getOutputStream());
            out.write(requestData);
            out.flush();
            out.close();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //取得输入流
                InputStream is = conn.getInputStream();
                httpListener.onSuccess(is);
            }else{
                httpListener.onFail("请求失败，返回responseCode:"+conn.getResponseCode()+","+conn.getResponseMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            httpListener.onFail(e.getMessage());
        }
        //关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if(conn!=null){
                    conn.disconnect();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}

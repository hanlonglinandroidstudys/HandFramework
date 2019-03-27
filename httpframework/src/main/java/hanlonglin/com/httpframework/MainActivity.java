package hanlonglin.com.httpframework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import hanlonglin.com.httpframework.bean.RequestData;
import hanlonglin.com.httpframework.http.IDataListener;
import hanlonglin.com.httpframework.http.NetFrameworkApi;

public class MainActivity extends AppCompatActivity {

    private final String TAG="MainActivity";

    Button btn_send_json_post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_send_json_post=(Button)findViewById(R.id.btn_send_json_post);
        btn_send_json_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRequest();
            }
        });
    }

    /**
     * 使用网络请求框架
     */

    private void doRequest() {
        String url="http://v.juhe.cn/weather/index";
        HashMap<String,String> params=new HashMap<>();

        params.put("cityname","长沙");
        params.put("key","");

        NetFrameworkApi.sendJsonRequest(params, url, RequestData.class, new IDataListener<RequestData>() {
            @Override
            public void onSuccess(RequestData requestData) {
                Log.e(TAG, "onSuccess: "+requestData.toString()+"，current_thread:"+Thread.currentThread());
            }

            @Override
            public void onFail(String errMsg) {
                Log.e(TAG, "onFail: "+errMsg);
            }
        });
    }


}

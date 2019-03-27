package hanlonglin.com.httpframework.http;

import java.io.InputStream;

/**
 * 处理相应结果
 */
public interface IHttpListener {

    //接受上一个接口的结果
    void onSuccess(InputStream is);
    void onFail(String errMsg);

}

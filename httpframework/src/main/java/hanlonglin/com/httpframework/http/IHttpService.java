package hanlonglin.com.httpframework.http;

/**
 * 处理请求
 */

public interface IHttpService {
    void setUrl(String url);
    void setRequestData(byte[] requestData);
    void excute();
    void setHttpCallback(IHttpListener httpListener);
}

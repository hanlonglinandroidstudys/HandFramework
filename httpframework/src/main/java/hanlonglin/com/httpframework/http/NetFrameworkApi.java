package hanlonglin.com.httpframework.http;

/**
 * 提供api给用户调用
 */

public class NetFrameworkApi {

    public static<T,M> void sendJsonRequest(T requestInfo,String url,Class<M> resonseClass,IDataListener<M> dataListener){
         IHttpService httpService=new JsonHttpService();
         IHttpListener httpListener=new JsonHttpListener<>(resonseClass,dataListener);
         HttpTask httpTask=new HttpTask(requestInfo,url,httpService,httpListener);
         ThreadPoolManager.getInstance().excute(httpTask);
    }
}

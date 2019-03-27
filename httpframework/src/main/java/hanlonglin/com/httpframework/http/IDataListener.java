package hanlonglin.com.httpframework.http;

/**
 * 面向用户的回调
 */

public interface IDataListener<M> {
   void onSuccess(M m);
   void onFail(String errMsg);
}

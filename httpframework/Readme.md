# 手写网络框架
## 核心原理：
* 实现多线程并发。此项目将请求信息封装成IHttpService,将响应动作封装成IHttpListener,然后将请求和响应都放到HttpTask线程中去执行；当然HttpTask的执行需要使用线程池和队列管理；

## 模块说明：

>> HttpTask 请求网络线程，包含IHttpService和IHttpListener信息；

>> ThreadPoolManager 线程管理类，负责阻塞队列维护和线程调控


>接口

>>IHttpService 网络请求接口

>>IHttpListener  网络回调接口

>> IDataListener 用户接口，存在意义：当IHttpListener回调后，IDataListener负责将结果回传到主线程

>>实现

>>JsonHttpService json请求的实现
>>JsonHttpListener json响应实现




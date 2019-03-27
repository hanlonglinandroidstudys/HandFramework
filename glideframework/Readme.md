# 手写Glide框架

## 核心原理
* 1.多线程并发；这里我使用了两种方式：
* * 1).使用一个阻塞队列，创建cpu核心数相同的线程，每个线程while循环获取阻塞队列中的BitmapRequest,然后处理；实现见core包；
* * 2).使用线程池管理，创建一个线程池和阻塞队列，然后创建一个管理线程，负责while循环从阻塞队列中取BitmapRequest,取到后丢入线程池执行，实现见core1包；
* 2.缓存机制：
* * 原理简单，三级缓存，就是寻找bitmap的过程；具体过程:
  <br>从内存中找，找到返回，未找到向下-->
  <br>从硬盘中找，找到返回，未找到向下-->
  <br>从网络加载, 加载成功后，存入内存缓存，存入硬盘缓存，返回；加载失败，则此次获取bitmap失败；
* 3.生命周期管理：
* * 管理activity生命周期是通过让RequestFragment和activity绑定，监听requestFragment来实现的，当退出activity,调用RequestFragment的onDestory(),然后调用LifeCircleObserver里的onDestory,将bitmap资源释放；具体见lifecircle包；生命周期控制简单原理见[生命周期](生命周期.md)


## 各个模块说明：

>>`cache` 缓存模块，实现硬盘和内存缓存；

>>`core/core1`: 核心模块，加载图片的线程和管理线程的类

>>`glide` 用户调用的接口，并负责绑定fragment

>>`lifecircle` 负责管理生命周期的Fragment和感知生命周期并清除缓存的LifeCircleObservable;

>>`listener` 图片加载完成监听

>>`request` 请求封装类，负责封装url，ImageView,listener 等信息；

>>`utils` 工具类，现在有打印日志的工具LogUtil 和 md5转化的工具MD5Util




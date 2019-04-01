# 手写EventBus 事件总线

## 核心原理：
* 发布订阅模式，不同于观察者模式，发布订阅模式中发布者和订阅者互相不知道，耦合性小，而观察者模式需要将观察者对象注入被观察者；
* 发布订阅模式除了发布者和订阅者，还有一个代理中间类，即事件总线；
* 订阅者订阅的操作，实际上是将自己添加到事件总线中的hash表中，使用当前所在的类对象当做key,获取对象中使用了注解的方法集合作为value;
* 发布者发布的操作，实际上是遍历事件总线中的hash表，找到相同和发布相同事件类型的订阅方法，通过反射调用并传入自己的事件对象参数；

# 实现：
> ----annotion
>> Subscrib 订阅者注解，被注解的方法标记为订阅方法；

>> ThreadMode 线程模式 定义了三种模式

> ----main
>> EventBus 事件总线中间代理类，完成注册，注销，订阅，发布功能；

>> MethodWrapper Method包装类，主要包装的是订阅方法，属性有Method本身（用来调用），订阅事件类型（用来和发布者事件匹配），ThreadMode(用来决定决定订阅方法执行的线程)


# 使用 ：
* 订阅 

onCreate方法中注册：

```
  EventBus.getInstance().register(this);
```

定义一个方法：

```
@Subscrib(threadMode = ThreadMode.POSTING)
    public void getXiaomi1(XiaoMi xiaomi) {
        Log.e(getClass().getSimpleName(), "posting获取小米:name:" + xiaomi.getName() + ",price:" + xiaomi.getPrice() + ",thread:" + Thread.currentThread());
    }
```

onDestory中注销（防止内存泄漏）：

```
    EventBus.getInstance().unregister(this);
```    

* 发布
```
EventBus.getInstance().post(new XiaoMi("小米9",3499));
```



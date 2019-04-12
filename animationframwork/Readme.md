# 动画框架

## 功能：
* 对于任意View，可以对其进行任意的平滑移动,支持直线运动和二阶贝塞尔曲线运动。

## 核心思想：
* * 1.属性动画，实质是通过反射调用对象的setXXX方法；
* * 2.如何实现平滑的曲线运动？--贝塞尔曲线，通过估值器实现；
* * 3.估值器Evaluator，自定义估值器，使用贝塞尔曲线公式；

## 实现代码
>hanlonglin.com.animationframework
>>AnimatorPath 动画执行主类

>>PathEveluator 自定义估值器

>>PathPoint  封装的指令集 包括坐标，拐点，类型 

## 使用方式：
```
 AnimatorPath animatorPath = new AnimatorPath();
 animatorPath.moveTo(0, 0);
 animatorPath.curbicTo(-200, 200, -400, 100, -600, 50);
 animatorPath.lineTo(-600, 200);
 animatorPath.startAnimation(v, 2000);
```
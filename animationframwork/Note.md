### 高级UI-动画框架

* 难点：如何让一个View实现一个曲线运动？
1.属性动画---- ObjectAninmator.ofFloat(view,"translationX",10,20)
             ObjectAninmator.ofFloat(view,"translationX",10,20)

2.描绘一个曲线
贝塞尔曲线---Path的方法  quaTo()  cubicTo()
贝塞尔曲线的特点：
需要有起始坐标，拐点

3.如何让view沿着贝塞尔曲线运动。属性动画本质就是不断修改属性的值，所以把改变属性的规则-->估值器改成我们需要的贝塞尔曲线就可以了；

4.封装成别人可以使用的框架：模仿android 源码 Path的使用；
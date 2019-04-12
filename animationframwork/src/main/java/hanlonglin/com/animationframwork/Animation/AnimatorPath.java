/*
 * Copyright (c) 2019/4/12
 * Create at 10:47:43
 * Wriitten by hanlonglin
 */

package hanlonglin.com.animationframwork.Animation;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 能够存储一系列的指令  moveTo linTo cubicTo
 * 一系列的路径指令： 路径指令类 集合
 */
public class AnimatorPath {

    List<PathPoint> mPoints = new ArrayList<>();
    private View view;

    public void moveTo(float x, float y) {
        mPoints.add(new PathPoint(PathPoint.MOVE, x, y));
    }

    public void lineTo(float x, float y) {
        mPoints.add(new PathPoint(PathPoint.LINE, x, y));
    }

    public void curbicTo(float x1, float y1, float x2, float y2, float x, float y) {
        mPoints.add(new PathPoint(PathPoint.CUBIC, x1, y1, x2, y2, x, y));
    }

    public Collection<PathPoint> getmPoints() {
        return mPoints;
    }

    public void startAnimation(View view, int Duration) {
        this.view = view;
        //执行动画
        //--属性动画的本质 ==反射调用View.setXXX
        // ObjectAnimator.ofObject(view,"translationX",new FloatEvaluator(),100,100);
        ObjectAnimator animator = ObjectAnimator.ofObject(this, "haha", new PathEvaluator(), getmPoints().toArray());
        animator.setDuration(Duration);
        animator.start();
    }

    public void setHaha(PathPoint pathPoint) {
        view.setTranslationX(pathPoint.mX);
        view.setTranslationY(pathPoint.mY);
    }

}

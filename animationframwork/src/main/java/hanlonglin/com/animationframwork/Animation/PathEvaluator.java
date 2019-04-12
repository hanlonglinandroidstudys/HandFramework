/*
 * Copyright (c) 2019/4/12
 * Create at 2:50:43
 * Wriitten by hanlonglin
 */

package hanlonglin.com.animationframwork.Animation;

import android.animation.TypeConverter;
import android.animation.TypeEvaluator;

class PathEvaluator implements TypeEvaluator<PathPoint> {
    @Override
    public PathPoint evaluate(float t, PathPoint startValue, PathPoint endValue) {
        float x = 0, y = 0; //起始点
        //判断是哪一种操作
        switch (endValue.mOperation) {
            case PathPoint.CUBIC:
                //贝塞尔曲线
                //P = (1-t)^3*P0 + 3*(1-t)^2*t*P1 + 3(1-t)*t^2*P2 + t^3*P3
                float oneMinusT = 1 - t;
                x = oneMinusT * oneMinusT * oneMinusT * startValue.mX +
                        3 * oneMinusT * oneMinusT * t * endValue.mControl0X +
                        3 * oneMinusT * t * t * endValue.mControl1X +
                        t * t * t * endValue.mX;
                y = oneMinusT * oneMinusT * oneMinusT * startValue.mY +
                        3 * oneMinusT * oneMinusT * t * endValue.mControl0Y +
                        3 * oneMinusT * t * t * endValue.mControl1Y +
                        t * t * t * endValue.mY;
                break;
            case PathPoint.MOVE:
                //move
                x = endValue.mX;
                y = endValue.mY;
                break;
            case PathPoint.LINE:
                //直线运动的方式
                x = startValue.mX + (endValue.mX - startValue.mX) * t;
                y = startValue.mY + (endValue.mY - startValue.mY) * t;
                break;
        }
        return new PathPoint(PathPoint.MOVE, x, y);
    }
}

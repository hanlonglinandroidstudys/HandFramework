/*
 * Copyright (c) 2019/4/12
 * Create at 10:52:19
 * Wriitten by hanlonglin
 */

package hanlonglin.com.animationframwork.Animation;

/**
 * 指令类
 */

public class PathPoint {

    public static final int MOVE = 0;
    public static final int LINE = 1;
    public static final int CUBIC = 2;

    int mOperation;
    float mX, mY;
    float mControl0X, mControl1X;
    float mControl0Y, mControl1Y;

    public PathPoint(int operation, float x, float y) {
        this.mOperation = operation;
        this.mX = x;
        this.mY = y;
    }

    public PathPoint(int operation, float c0x, float c0y, float c1x, float c1y, float x, float y) {
        this.mOperation = operation;
        this.mX = x;
        this.mY = y;
        this.mControl0X = c0x;
        this.mControl0Y = c0y;
        this.mControl1X = c1x;
        this.mControl1Y = c1y;
    }
}

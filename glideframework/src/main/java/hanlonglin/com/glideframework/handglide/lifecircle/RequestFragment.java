/*
 * Copyright (c) 2019/3/26
 * Create at 7:18:9
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.lifecircle;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import hanlonglin.com.glideframework.handglide.utils.LogUtil;

public class RequestFragment extends Fragment {
    private int activityCode;
    LifecircleObservable lifecircleObservable=LifecircleObservable.getInstance();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.log(getClass().getSimpleName()+" onCreate()");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.log(getClass().getSimpleName()+" onAttach()");
        activityCode=((Activity)context).hashCode();
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.log(getClass().getSimpleName()+" onStart()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.log(getClass().getSimpleName()+" onDetach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.log(getClass().getSimpleName()+" onDestory()");
        lifecircleObservable.onDestory(activityCode);
    }
}

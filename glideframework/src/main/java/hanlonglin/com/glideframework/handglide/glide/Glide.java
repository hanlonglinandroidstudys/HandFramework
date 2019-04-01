/*
 * Copyright (c) 2019/3/26
 * Create at 9:43:57
 * Wriitten by hanlonglin
 */

package hanlonglin.com.glideframework.handglide.glide;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelStore;

import hanlonglin.com.glideframework.handglide.lifecircle.RequestFragment;
import hanlonglin.com.glideframework.handglide.request.BitmapRequest;
import hanlonglin.com.glideframework.handglide.utils.LogUtil;

public class Glide {

    public static BitmapRequest with(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        RequestFragment requestFragment = (RequestFragment) fragmentManager.findFragmentByTag("com.requestFragment");
        if (requestFragment == null) {
            requestFragment = new RequestFragment();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.add(requestFragment, "com.requestFragment");
//            fragmentTransaction.commit();
            fragmentManager.beginTransaction().add(requestFragment,"com.requestFragment").commitAllowingStateLoss();
            LogUtil.log("添加requestFragment");
        } else {
            LogUtil.log("存在requestFragment");
        }
        return new BitmapRequest(activity);
    }

}

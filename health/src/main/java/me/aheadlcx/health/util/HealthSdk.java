package me.aheadlcx.health.util;

import android.content.Context;

/**
 * Description:
 * author: aheadlcx
 * Date:2016/12/6 下午11:25
 */

public class HealthSdk {
    public static HealthSdk sInstance;
    private Context mContext;
    private HealthSdk() {
    }

    public static HealthSdk getInstance(){
        return sInstance;
    }

    public static void initHealth(Context context){
        if (sInstance == null) {
            sInstance = new HealthSdk();
        }
        sInstance.setContext(context);
    }

    public HealthSdk setContext(Context context) {
        mContext = context;
        return this;
    }

    public Context getContext() {

        return mContext;
    }
}

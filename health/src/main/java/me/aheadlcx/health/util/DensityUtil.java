package me.aheadlcx.health.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Description:
 * author: aheadlcx
 * Date:2016/12/6 下午11:25
 */

public class DensityUtil {
    public static int dip2px(float dipValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue,
                HealthSdk.getInstance().getContext().getResources().getDisplayMetrics()) + 0.5f);
    }


    public static int px2dp(float pxValue) {
        final float scale = HealthSdk.getInstance().getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}

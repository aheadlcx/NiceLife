package me.aheadlcx.health.di.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import me.aheadlcx.health.di.PerActivity;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午10:47
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity activity(){
        return mActivity;
    }
}

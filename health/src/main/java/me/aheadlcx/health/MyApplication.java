package me.aheadlcx.health;

import android.app.Application;

import me.aheadlcx.health.di.components.ApplicationComponent;
import me.aheadlcx.health.di.components.DaggerApplicationComponent;
import me.aheadlcx.health.di.modules.ApplicationModule;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午10:20
 */

public class MyApplication extends Application {
    private ApplicationComponent applicationComponent;
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
         applicationComponent= DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
                .build();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}

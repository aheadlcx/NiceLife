package me.aheadlcx.health;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.aheadlcx.health.di.components.ApplicationComponent;
import me.aheadlcx.health.di.components.DaggerApplicationComponent;
import me.aheadlcx.health.di.components.TestComponent;
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

        initDb();
    }

    private void initDb() {
        Realm.init(this);
        RealmConfiguration configuration =new RealmConfiguration.Builder().build();
        Realm.deleteRealm(configuration);
        Realm.setDefaultConfiguration(configuration);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }
}

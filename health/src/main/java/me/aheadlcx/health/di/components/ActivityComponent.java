package me.aheadlcx.health.di.components;

import android.app.Activity;

import dagger.Component;
import dagger.Subcomponent;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.di.modules.ActivityModule;
import me.aheadlcx.health.di.modules.HealthNewsModule;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午10:46
 */
//@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
//    Activity activity();
    HealthNewsComponents plus(HealthNewsModule HealthNewsModule);
}

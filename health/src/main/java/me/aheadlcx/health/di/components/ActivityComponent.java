package me.aheadlcx.health.di.components;

import android.app.Activity;

import dagger.Component;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.di.modules.ActivityModule;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午10:46
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}

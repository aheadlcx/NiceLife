package me.aheadlcx.health.di.components;

import dagger.Component;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.di.modules.ActivityModule;
import me.aheadlcx.health.di.modules.ApplicationModule;
import me.aheadlcx.health.di.modules.HealthNewsModule;
import me.aheadlcx.health.ui.health.HealthNewsListFragment;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午11:03
 */
@PerActivity
@Component( dependencies = {ApplicationComponent.class}, modules = {ActivityModule
        .class, HealthNewsModule.class})
public interface HealthNewsComponents extends ActivityComponent{
    void inject(HealthNewsListFragment healthNewsListFragment);

}

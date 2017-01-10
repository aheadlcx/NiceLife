package me.aheadlcx.health.di.components;

import dagger.Component;
import dagger.Subcomponent;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.di.modules.ActivityModule;
import me.aheadlcx.health.di.modules.ApplicationModule;
import me.aheadlcx.health.di.modules.HealthNewsDetailModule;
import me.aheadlcx.health.di.modules.HealthNewsModule;
import me.aheadlcx.health.di.modules.HealthNewslistModule;
import me.aheadlcx.health.di.modules.TestModule;
import me.aheadlcx.health.ui.health.HealthNewsListFragment;
import me.aheadlcx.health.ui.health.detail.HealthNewsDetailFragment;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午11:03
 */
//@Component(dependencies = {ApplicationComponent.class}, modules =
//        {ActivityModule.class, HealthNewsModule.class})
@PerActivity
@Subcomponent(modules =
        {HealthNewsModule.class})
public interface HealthNewsComponents{
//    HealthNewsListFragment inject(HealthNewsListFragment healthNewsListFragment);

//    HealthNewsDetailFragment inject(HealthNewsDetailFragment healthNewsDetailFragment);

    HealthNewslistComponents plus (HealthNewslistModule HealthNewslistModule);

    HealthNewsDetailComponents plus(HealthNewsDetailModule HealthNewsDetailModule);
}

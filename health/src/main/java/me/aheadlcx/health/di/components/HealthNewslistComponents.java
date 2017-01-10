package me.aheadlcx.health.di.components;

import dagger.Subcomponent;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.di.modules.HealthNewslistModule;
import me.aheadlcx.health.ui.health.HealthNewsListFragment;

/**
 * Description:
 * author: aheadlcx
 * Date:2017/1/10 上午10:14
 */
@PerActivity
@Subcomponent(modules = HealthNewslistModule.class)
public interface HealthNewslistComponents {
    HealthNewsListFragment inject(HealthNewsListFragment healthNewsListFragment);

}

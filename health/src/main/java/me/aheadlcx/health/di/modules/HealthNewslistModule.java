package me.aheadlcx.health.di.modules;

import dagger.Module;
import dagger.Provides;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.di.Type;
import me.aheadlcx.health.domain.interactor.Case;
import me.aheadlcx.health.domain.interactor.HealthNewsListCase;
import me.aheadlcx.health.ui.health.HealthNewsListPresent;
import me.aheadlcx.health.ui.health.HealthNewslistContract;

/**
 * Description:
 * author: aheadlcx
 * Date:2017/1/10 上午10:28
 */
@Module
public class HealthNewslistModule {
    @Type("list")
    @Provides
    @PerActivity
    public Case provideHealthNewsList(HealthNewsListCase healthNewsListCase){
        return healthNewsListCase;
    }

    @PerActivity
    @Provides
    public HealthNewslistContract.Present providePrensent(HealthNewsListPresent present){
        return present;
    }
}

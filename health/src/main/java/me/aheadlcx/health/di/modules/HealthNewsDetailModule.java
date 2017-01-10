package me.aheadlcx.health.di.modules;

import dagger.Module;
import dagger.Provides;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.di.Type;
import me.aheadlcx.health.domain.interactor.Case;
import me.aheadlcx.health.domain.interactor.HealthNewsDetailCase;

/**
 * Description:
 * author: aheadlcx
 * Date:2017/1/10 上午10:27
 */
@Module()
public class HealthNewsDetailModule {

    @Type("detail")
    @Provides
    @PerActivity
    public Case provideHealthNewsDetailCase(HealthNewsDetailCase healthNewsDetailCase){
        return healthNewsDetailCase;
    }
}

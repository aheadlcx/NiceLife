package me.aheadlcx.health.di.modules;

import dagger.Module;
import dagger.Provides;
import me.aheadlcx.health.data.repository.HealthNewsDataRepository;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.domain.interactor.Case;
import me.aheadlcx.health.domain.interactor.HealthNewsListCase;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;
import me.aheadlcx.health.ui.health.HealthNewsListPresent;
import me.aheadlcx.health.ui.health.HealthNewslistContract;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午11:04
 */
@Module
public class HealthNewsModule {

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

    @Provides
    @PerActivity
    public HealthNewsRepository provideHealthNewsRepo(HealthNewsDataRepository repository){
        return repository;
    }
}

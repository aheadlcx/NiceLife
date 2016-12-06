package me.aheadlcx.health.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.aheadlcx.health.data.repository.HealthNewsDataRepository;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/12/6 上午10:21
 */
@Module
public class HealthNewsApiModule {
    @Provides
    @Singleton
    public HealthNewsRepository provideHealthNewsRepo(HealthNewsDataRepository repository){
        return repository;
    }
}

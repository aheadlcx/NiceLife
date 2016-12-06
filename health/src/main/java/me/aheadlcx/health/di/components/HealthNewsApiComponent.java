package me.aheadlcx.health.di.components;

import javax.inject.Singleton;

import dagger.Component;
import me.aheadlcx.health.di.modules.HealthNewsApiModule;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/12/6 上午10:20
 */
@Singleton
@Component(modules = HealthNewsApiModule.class)
public interface HealthNewsApiComponent {
    HealthNewsRepository provideHealthNewsRepo();

}

package me.aheadlcx.health.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.aheadlcx.health.base.BaseActivity;
import me.aheadlcx.health.di.modules.ActivityModule;
import me.aheadlcx.health.di.modules.ApplicationModule;
import me.aheadlcx.health.di.modules.HealthNewsModule;
import me.aheadlcx.health.domain.executor.PostExecutionThread;
import me.aheadlcx.health.domain.executor.ThreadExecutor;
import me.aheadlcx.health.domain.repository.HealthNewsRepository;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 上午10:24
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
//    void inject(BaseActivity baseActivity);

//    Context getContext();

//    ThreadExecutor threadExecutor();

//    PostExecutionThread postExecutionThread();

//    HealthNewsRepository provideHealthNewsRepo();

    ActivityComponent plus(ActivityModule module);

}

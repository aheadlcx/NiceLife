package me.aheadlcx.health.di.components;

import javax.inject.Singleton;

import dagger.Component;
import me.aheadlcx.health.di.PerActivity;
import me.aheadlcx.health.di.modules.TestModule;
import me.aheadlcx.health.di.test.TestDi;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 下午2:42
 */
@Singleton
@Component
public interface TestComponent {
    TestDi getTestDi();
//modules = TestModule.class
}

package me.aheadlcx.health.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.aheadlcx.health.di.test.TestDi;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/28 下午2:43
 */

//@Module
public class TestModule {

//    @Provides
//    @Singleton
    public TestDi getTestDi(TestDi testDi){
        return testDi;
    }
}

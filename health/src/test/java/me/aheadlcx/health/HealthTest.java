package me.aheadlcx.health;


import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import me.aheadlcx.health.api.ApiUtils;
import me.aheadlcx.health.api.HealthNewsListService;
import me.aheadlcx.health.model.HealthNewsItem;
import me.aheadlcx.health.model.HealthNewsListResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.assertTrue;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/11/27 下午6:44
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class HealthTest {
    private static final String TAG = "HealthTest";

    @Before
    public void setUp() throws URISyntaxException {
        //输出日志
        ShadowLog.stream = System.out;
    }

    //    @Test
    public void testHealthNewsList() throws IOException {
        Retrofit retrofit = ApiUtils.getRetrofit();
        HealthNewsListService healthNewsListService = retrofit.create(HealthNewsListService.class);
        Call<HealthNewsListResponse> healthNewsListSync = healthNewsListService.getHealthNewsListSync();
        Response<HealthNewsListResponse> execute = healthNewsListSync.execute();
        HealthNewsListResponse body = execute.body();
        List<HealthNewsItem> newslist = body.getTngou();
        assertTrue(newslist != null && newslist.size() > 0);

        Gson gson = new Gson();
        String json = gson.toJson(body);
        Log.i(TAG, "testHealthNewsList: " + json);
    }

//    @Test
    public void testInt() {
        int a = 0;
        int b = 1;
        int c = 3;

        boolean b1 = (c & 2) != 0;
        Log.i(TAG, "testInt: b1 = " + b1);
        assertTrue(b1);
        int d = 1 << 2;
        Log.i(TAG, "testInt: d =" + d);

        int aa = 0x0002;
        int ab = 0x0004;
        boolean b2 = (aa & ab) != 0;
        Log.i(TAG, "testInt: b2 = " +  b2);

    }

    @Test
    public void testA(){
        ArrayMap<Double, Double> map = new ArrayMap<>();
        map.put(1d, 2d);
        Double aDouble = map.get(2d);
        assertTrue(aDouble == null);


    }
}

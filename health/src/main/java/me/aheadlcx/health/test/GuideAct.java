package me.aheadlcx.health.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import me.aheadlcx.health.R;
import me.aheadlcx.health.base.BaseActivity;
import me.aheadlcx.health.constant.HealthType;
import me.aheadlcx.health.constant.IntentKey;
import me.aheadlcx.health.ui.health.HealthNewsListAct;

/**
 * Description:
 * Creator: aheadlcx
 * Date:2016/12/3 下午11:01
 */

public class GuideAct extends BaseActivity {

    private android.widget.TextView txtMain;
    private android.widget.TextView txtTest;
    private TextView txtTestSync;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_act_guide);
        this.txtTestSync = (TextView) findViewById(R.id.txtTestSync);
        this.txtTest = (TextView) findViewById(R.id.txtTest);
        this.txtMain = (TextView) findViewById(R.id.txtMain);

        txtTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideAct.this, TestListAct.class);
                startActivity(intent);
            }
        });

        txtMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideAct.this, HealthNewsListAct.class);
                intent.putExtra(IntentKey.HEALTH_TYPE, HealthType.TYPE_INFO);
                startActivity(intent);
            }
        });

        txtTestSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideAct.this, TestListSyncAct.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.txtKnowLore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideAct.this, HealthNewsListAct.class);
                intent.putExtra(IntentKey.HEALTH_TYPE, HealthType.TYPE_LORE);
                startActivity(intent);
            }
        });
    }
}

package com.android.gasproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.BuildConfig;
import com.android.gasproject.R;
import com.android.gasproject.utils.CacheDataManager;
import com.android.gasproject.utils.SPUtils;
import com.android.gasproject.view.SettingBar;


/**
 * desc   : 设置界面
 */
public final class SettingActivity extends BaseActivity {

    SettingBar mCleanCacheView;
    SettingBar mVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }


    protected void initView() {
        mCleanCacheView = findViewById(R.id.sb_setting_cache);
        mVersion = findViewById(R.id.sb_setting_version);

        mVersion.setRightText(getVersionName());
        // 获取应用缓存大小
        mCleanCacheView.setRightText(CacheDataManager.getTotalCacheSize(this));
        findViewById(R.id.sb_setting_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //本地登陆状态设置为未登录
                SPUtils.put(SettingActivity.this, "login", false);
                //跳转到登陆页面
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                //结束当前页面
                finish();
            }
        });
    }

    /**
     * 获取当前应用的版本名
     */
    public static String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }


}
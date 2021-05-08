package com.android.gasproject.activity;

import android.os.Bundle;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;


/**
 * 关于我们
 */
public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        bindActivity(this);
    }
}
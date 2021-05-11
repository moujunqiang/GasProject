package com.android.gasproject.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.http.response.NotifyBean;

import java.io.Serializable;

/**
 * 通知详情
 */
public class NotifyDetailActivity extends BaseActivity {

    private TextView tvNotifyTitle;
    private TextView tvNotifyTime;
    private TextView tvNotifyContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_detail);
        bindActivity(this);
        initView();
    }

    private void initView() {
        NotifyBean notify = (NotifyBean) getIntent().getSerializableExtra("notify");
        tvNotifyTitle = (TextView) findViewById(R.id.tv_notify_title);
        tvNotifyTime = (TextView) findViewById(R.id.tv_notify_time);
        tvNotifyContent = (TextView) findViewById(R.id.tv_notify_content);
        if (notify!=null){
            tvNotifyContent.setText(notify.getNcontent());
            tvNotifyTitle.setText(notify.getNtitle());
            tvNotifyTime.setText(notify.getCtime());
        }
    }
}
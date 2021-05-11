package com.android.gasproject.activity.admin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.request.ChangeConfigApi;
import com.android.gasproject.http.request.GetConfigApi;
import com.android.gasproject.http.response.ConfigBean;
import com.android.gasproject.http.response.NotifyBean;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;

import java.util.List;

/**
 * 規則管理
 */
public class ConfigActivity extends BaseActivity {

    private EditText etJifei;
    private Button btnCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        bindActivity(this);
        initView();
    }

    private void initView() {
        etJifei = (EditText) findViewById(R.id.et_jifei);
        btnCommit = (Button) findViewById(R.id.btn_commit);
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etJifei.getText().toString())) {
                    Toast.makeText(ConfigActivity.this, "请输入积分规则", Toast.LENGTH_SHORT).show();
                }
                changeConfig();

            }
        });
        getConfig();
    }

    public void changeConfig() {
        EasyHttp.post(this)
                .api(new ChangeConfigApi()
                        .setRules(etJifei.getText().toString())
                    ).request(new OnHttpListener<HttpData<ConfigBean>>() {
            @Override
            public void onSucceed(HttpData<ConfigBean> result) {
                Toast.makeText(ConfigActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
             
            }

            @Override
            public void onFail(Exception e) {
            }
        });
    }

    public void getConfig() {
        EasyHttp.post(this)
                .api(new GetConfigApi()

                ).request(new OnHttpListener<HttpData<ConfigBean>>() {
            @Override
            public void onSucceed(HttpData<ConfigBean> result) {

                etJifei.setText(result.getData().getRules());
            }

            @Override
            public void onFail(Exception e) {
            }
        });
    }
}
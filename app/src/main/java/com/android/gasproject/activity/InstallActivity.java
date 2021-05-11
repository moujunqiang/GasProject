package com.android.gasproject.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.http.request.AddInstallAPi;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.listener.OnHttpListener;

public class InstallActivity extends BaseActivity {

    private EditText etAddress;
    private EditText tvInstallName;
    private EditText tvInstallPhone;
    private EditText tvInstallInfo;
    private Button btnCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install);
        bindActivity(this);
        initView();
    }

    private void initView() {
        etAddress = (EditText) findViewById(R.id.et_install_address);
        tvInstallName = (EditText) findViewById(R.id.tv_install_name);
        tvInstallPhone = (EditText) findViewById(R.id.tv_install_phone);
        tvInstallInfo = (EditText) findViewById(R.id.tv_install_info);
        btnCommit = (Button) findViewById(R.id.btn_commit);
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etAddress.getText().toString())) {
                    Toast.makeText(InstallActivity.this, "请输入地址", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tvInstallName.getText().toString())) {
                    Toast.makeText(InstallActivity.this, "请输入名字", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tvInstallPhone.getText().toString())) {
                    Toast.makeText(InstallActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                install();

            }
        });
    }

    public void install() {
        EasyHttp.post(this)
                .api(new AddInstallAPi()
                        .setUname(tvInstallName.getText().toString())
                        .setInfo(tvInstallInfo.getText().toString())
                        .setUaddress(etAddress.getText().toString())
                        .setUphone(tvInstallPhone.getText().toString())
                ).request(new OnHttpListener<HttpCallback<Object>>() {
            @Override
            public void onSucceed(HttpCallback<Object> result) {
                Toast.makeText(InstallActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFail(Exception e) {

            }
        });
    }
}
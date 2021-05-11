package com.android.gasproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.request.ChangePwdApi;
import com.android.gasproject.http.request.LoginAPi;
import com.android.gasproject.http.response.UserBean;
import com.android.gasproject.utils.SPUtils;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;

/**
 * 修改密码
 */
public class ChangePwdActivity extends BaseActivity {

    private ImageView ivBack;
    private TextView tvTitle;
    private EditText etOldPassword;
    private EditText etNewPwd;
    private EditText etPwdAgain;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        bindActivity(this);
        initView();
    }

    private void initView() {

        etOldPassword = (EditText) findViewById(R.id.et_old_password);
        etNewPwd = (EditText) findViewById(R.id.et_new_pwd);
        etPwdAgain = (EditText) findViewById(R.id.et_pwd_again);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePwd();
            }
        });
    }

    public void changePwd() {
        if (TextUtils.isEmpty(etOldPassword.getText().toString())) {
            Toast.makeText(this, "请输入老密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etNewPwd.getText().toString())) {
            Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!etNewPwd.getText().toString().equals(etPwdAgain.getText().toString())) {
            Toast.makeText(this, "两次新密码输入不一样", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!etOldPassword.getText().toString().equals(getUserBean().getPwd())) {
            Toast.makeText(this, "原密码输入错误", Toast.LENGTH_SHORT).show();
            return;
        }
        EasyHttp.post(this)
                .api(new ChangePwdApi()
                        .setUid(getUserBean().getId() + "")
                        .setPwd(etNewPwd.getText().toString())
                ).request(new OnHttpListener<HttpData<UserBean>>() {
            @Override
            public void onSucceed(HttpData<UserBean> result) {
                Toast.makeText(ChangePwdActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFail(Exception e) {
            }
        });

    }
}
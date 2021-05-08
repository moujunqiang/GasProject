package com.android.gasproject.activity;

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

        tvTitle = (TextView) findViewById(R.id.tv_title);
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


    }
}
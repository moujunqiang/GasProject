package com.android.gasproject.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mIvTopLogo;
    /**
     * 请输入账号/手机号
     */
    private EditText mUname;
    private ImageView mIgvClear;
    /**
     * @={data.psd}
     */
    private EditText mPword;
    private CheckBox mToggleShowPwd;
    /**
     * @={data.areaInfoEvent}
     */
    /**
     * 登录
     */
    private Button mSubmit;
    /**
     * 注册
     */
    private TextView mTvRegister;
    int yourChoice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mIvTopLogo = (ImageView) findViewById(R.id.iv_top_logo);
        mUname = (EditText) findViewById(R.id.uname);
        mIgvClear = (ImageView) findViewById(R.id.igvClear);
        mPword = (EditText) findViewById(R.id.pword);
        mToggleShowPwd = (CheckBox) findViewById(R.id.toggle_show_pwd);
        mSubmit = (Button) findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mTvRegister.setOnClickListener(this);
        mToggleShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    mPword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                mPword.setSelection(mPword.getText().toString().length());
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.submit:


                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                break;
        }
    }


}
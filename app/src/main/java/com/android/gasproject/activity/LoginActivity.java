package com.android.gasproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.activity.admin.AdminActivity;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.request.LoginAPi;
import com.android.gasproject.http.response.UserBean;
import com.android.gasproject.utils.SPUtils;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;


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
        boolean login = (boolean) SPUtils.get(this, "login", false);
        if (login) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            setContentView(R.layout.activity_login);
            initView();
        }

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
                if (TextUtils.isEmpty(mUname.getText().toString())) {
                    Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mPword.getText().toString())) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                login();
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

                break;
        }
    }

    /**
     * 登录
     */
    public void login() {
        if (mUname.getText().toString().equals("admin") && mPword.getText().toString().equals("123456")) {
            startActivity(new Intent(LoginActivity.this, AdminActivity.class));
            return;
        }
        EasyHttp.post(this)
                .api(new LoginAPi()
                        .setAccount(mUname.getText().toString())
                        .setPwd(mPword.getText().toString())
                ).request(new OnHttpListener<HttpData<UserBean>>() {
            @Override
            public void onSucceed(HttpData<UserBean> result) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                SPUtils.put(LoginActivity.this, "login", true);
                SPUtils.setObject("user", result.getData(), LoginActivity.this);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }

            @Override
            public void onFail(Exception e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
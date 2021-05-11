package com.android.gasproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.request.InvestApi;
import com.android.gasproject.http.request.LoginAPi;
import com.android.gasproject.http.response.UserBean;
import com.android.gasproject.utils.SPUtils;
import com.android.gasproject.view.SettingBar;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;

/**
 * 充值
 */
public class InvestActivity extends BaseActivity {

    private TextView tvMoney;
    private SettingBar sbName;
    private RadioButton rb50;
    private RadioButton rb100;
    private RadioButton rb150;
    private Button btnInvest;
    private EditText etMoney;
    private String money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest);
        bindActivity(this);
        getUserInfo();
        initView();
    }

    private void initView() {
        tvMoney = (TextView) findViewById(R.id.tv_money);
        sbName = (SettingBar) findViewById(R.id.sb_name);
        rb50 = (RadioButton) findViewById(R.id.rb_50);
        rb100 = (RadioButton) findViewById(R.id.rb_100);
        rb150 = (RadioButton) findViewById(R.id.rb_150);
        btnInvest = (Button) findViewById(R.id.btn_invest);
        etMoney = (EditText) findViewById(R.id.et_money);
        UserBean userBean = getUserBean();
        sbName.setRightText(userBean.getAccount());
        btnInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(money)) {
                    Toast.makeText(InvestActivity.this, "请选择或输入金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                invest();

            }
        });
        rb50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    money = 50 + "";
                }
            }
        });
        rb100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                money = 100 + "";

            }
        });
        rb150.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                money = 150 + "";

            }
        });
        etMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                rb50.setChecked(false);
                rb100.setChecked(false);
                rb150.setChecked(false);
                money = s.toString();
            }
        });
    }

    /**
     * 充值
     */
    public void invest() {
        UserBean user = SPUtils.getObject("user", UserBean.class, this);
        EasyHttp.post(this)
                .api(new InvestApi()
                        .setAccount(user.getAccount())
                        .setMoney(money)
                ).request(new OnHttpListener<HttpData<Object>>() {
            @Override
            public void onSucceed(HttpData<Object> result) {
                Toast.makeText(InvestActivity.this, "充值成功", Toast.LENGTH_SHORT).show();
                getUserInfo();
                finish();

            }

            @Override
            public void onFail(Exception e) {
            }
        });
    }

    public void getUserInfo() {
        UserBean user = SPUtils.getObject("user", UserBean.class, this);

        EasyHttp.post(this)
                .api(new LoginAPi()
                        .setAccount(user.getAccount())
                        .setPwd(user.getPwd())
                ).request(new OnHttpListener<HttpData<UserBean>>() {
            @Override
            public void onSucceed(HttpData<UserBean> result) {
                UserBean data = result.getData();
                SPUtils.setObject("user", result.getData(), InvestActivity.this);
                tvMoney.setText(data.getMoney());
            }

            @Override
            public void onFail(Exception e) {
            }
        });
    }

}
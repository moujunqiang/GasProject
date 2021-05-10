package com.android.gasproject.activity;

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

import com.android.gasproject.R;
import com.android.gasproject.view.SettingBar;

public class InvestActivity extends AppCompatActivity {

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

        btnInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(money)){
                    Toast.makeText(InvestActivity.this, "请选择或输入金额", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rb50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    money=50+"";
                }
            }
        });
        rb100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                money=100+"";

            }
        });
        rb150.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                money=150+"";

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
                money=s.toString();
            }
        });
    }
}
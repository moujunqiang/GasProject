package com.android.gasproject.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.request.ChangePwdApi;
import com.android.gasproject.http.request.ChangeUserStatusApi;
import com.android.gasproject.http.response.UserBean;
import com.android.gasproject.view.SettingBar;
import com.hjq.bar.TitleBar;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;

import java.io.Serializable;

/**
 * 用户详情
 */
public class UserDetailActivity extends BaseActivity {

    private TitleBar tvTitle;
    private SettingBar sbName;
    private SettingBar sbYue;
    private SettingBar sbStatus;
    private UserBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        initView();
    }

    int yourChoice = 0;

    private void initView() {
        tvTitle = (TitleBar) findViewById(R.id.tv_title);
        sbName = (SettingBar) findViewById(R.id.sb_name);
        sbYue = (SettingBar) findViewById(R.id.sb_yue);
        sbStatus = (SettingBar) findViewById(R.id.sb_status);
        user = (UserBean) getIntent().getSerializableExtra("user");
        if (user != null) {
            sbName.setRightText(user.getAccount());
            sbYue.setRightText(user.getMoney());
            sbStatus.setRightText(user.getGasstatus().equals("1") ? "正常" : "已断气");

        }

        sbStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = {"断气", "正常"};
                AlertDialog.Builder singleChoiceDialog =
                        new AlertDialog.Builder(UserDetailActivity.this);
                singleChoiceDialog.setTitle("选择角色类型");
                // 第二个参数是默认选项，此处设置为0
                singleChoiceDialog.setSingleChoiceItems(items, 0,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                yourChoice = which;
                            }
                        });
                singleChoiceDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                changeUsreStaus(yourChoice + "");

                            }
                        });
                singleChoiceDialog.show();
            }
        });
    }

    /**
     * 更改用户状态
     */
    public void changeUsreStaus(String status) {
        EasyHttp.post(this)
                .api(new ChangeUserStatusApi()
                        .setUid(user.getId() + "")
                        .setStatus(status)
                ).request(new OnHttpListener<HttpData<Object>>() {
            @Override
            public void onSucceed(HttpData<Object> result) {
                Toast.makeText(UserDetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFail(Exception e) {
            }
        });
    }
}
package com.android.gasproject.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.gasproject.R;
import com.android.gasproject.activity.InstallAdminActivity;

public class AdminActivity extends AppCompatActivity {

    private Button btnAdminMovie;
    private Button btnAdminUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initView();
    }

    private void initView() {
        btnAdminMovie = (Button) findViewById(R.id.btn_admin_movie);
        btnAdminMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, ConfigActivity.class));
            }
        });
        btnAdminUser = (Button) findViewById(R.id.btn_admin_user);
        btnAdminUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, UserAdminActivity.class));
            }
        });
        findViewById(R.id.btn_admin_notify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, AdminNotifyActivity.class));
            }
        });
        findViewById(R.id.btn_admin_install).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, InstallAdminActivity.class));
            }
        });
    }
}
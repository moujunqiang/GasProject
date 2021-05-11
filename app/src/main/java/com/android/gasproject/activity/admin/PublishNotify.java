package com.android.gasproject.activity.admin;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.gasproject.R;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.request.PublishNotifyApi;
import com.android.gasproject.http.response.NotifyBean;
import com.hjq.bar.TitleBar;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;

import java.util.List;

public class PublishNotify extends AppCompatActivity {

    private TitleBar tvTitle;
    private EditText etNotifyTitle;
    private EditText etMovieDesc;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_notify);
        initView();
    }

    private void initView() {
        tvTitle = (TitleBar) findViewById(R.id.tv_title);
        etNotifyTitle = (EditText) findViewById(R.id.et_notify_title);
        etMovieDesc = (EditText) findViewById(R.id.et_movie_desc);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publish();
            }
        });
    }

    /**
     * 发布通知
     */
    public void publish() {
        if (TextUtils.isEmpty(etNotifyTitle.getText().toString())) {
            Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etMovieDesc.getText().toString())) {
            Toast.makeText(this, "请输入通知内容", Toast.LENGTH_SHORT).show();
            return;
        }
        EasyHttp.post(this)
                .api(new PublishNotifyApi().setNtitle(etNotifyTitle.getText().toString())
                        .setNcontent(etMovieDesc.getText().toString())

                ).request(new OnHttpListener<HttpData<List<NotifyBean>>>() {
            @Override
            public void onSucceed(HttpData<List<NotifyBean>> result) {
                Toast.makeText(PublishNotify.this, "发布成功", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFail(Exception e) {
            }
        });
    }
}
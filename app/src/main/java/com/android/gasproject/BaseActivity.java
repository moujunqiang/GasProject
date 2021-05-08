package com.android.gasproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.hjq.http.listener.OnHttpListener;
import com.wega.library.loadingDialog.LoadingDialog;

import okhttp3.Call;


/**
 * 基础类 处理一些公共方法
 */
public class BaseActivity extends AppCompatActivity implements OnHttpListener {
    private LoadingDialog loadingDialog;

    /**
     * 显示加载框
     */
    public void showLodinDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);

        }
        loadingDialog.loading();
    }
    public void bindActivity(final Activity mActivity) {
        TitleBar mTvTitle = (TitleBar) mActivity.findViewById(R.id.tv_title);
        //设置标题栏点击事件
        mTvTitle.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 隐藏加载框
     */
    public void hideLodingDialog() {
        loadingDialog.cancel();
    }



    @Override
    public void onSucceed(Object result) {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onStart(Call call) {
        showLodinDialog();
    }

    @Override
    public void onEnd(Call call) {
        hideLodingDialog();
    }
}

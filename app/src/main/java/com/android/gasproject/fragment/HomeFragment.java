package com.android.gasproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.gasproject.BaseFragment;
import com.android.gasproject.R;
import com.android.gasproject.activity.InstallActivity;
import com.android.gasproject.activity.InvestActivity;
import com.android.gasproject.activity.InvestHistoryActivity;
import com.android.gasproject.activity.NotifyActivity;
import com.android.gasproject.adapter.ImageAdapter;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements View.OnClickListener {


    private View inflate;
    private Banner banner;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView();

        return inflate;
    }

    private void initView() {
        banner = inflate.findViewById(R.id.banner);
        inflate.findViewById(R.id.ll_trim).setOnClickListener(this);
        inflate.findViewById(R.id.ll_invest).setOnClickListener(this);
        inflate.findViewById(R.id.ll_trim).setOnClickListener(this);
        inflate.findViewById(R.id.ll_notify).setOnClickListener(this);
        inflate.findViewById(R.id.ll_history).setOnClickListener(this);
        inflate.findViewById(R.id.ll_yue).setOnClickListener(this);

        initBanner();
    }

    /**
     * 加载轮播图
     */
    public void initBanner() {
        List<String> list = new ArrayList<>();
        list.add("http://kang.cn:12345/2019/06/12/824830e9003b4c92bf3a0b62c58446f6.jpg");
        list.add("http://kang.cn:12345/2019/06/06/3057a25f6d164cc59a56a87b524092d7.jpg");
        list.add("http://kang.cn:12345/2019/05/10/4c2ed32b80734070beb910bb912cb9c8.jpg");
        list.add("http://kang.cn:12345/2019/05/17/cb8158bb70b247a69babc5ef4e9db625.jpg");
        banner.addBannerLifecycleObserver(this)//添加生命周期观察者
                .setAdapter(new ImageAdapter(list))
                .setIndicator(new CircleIndicator(getContext()))
                .start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_invest:
                startActivity(new Intent(getContext(), InvestActivity.class));
                break;
            case R.id.ll_history:
                startActivity(new Intent(getContext(), InvestHistoryActivity.class));
                break;
            case R.id.ll_trim:
                startActivity(new Intent(getContext(), InstallActivity.class));
                break;
            case R.id.ll_notify:
                startActivity(new Intent(getContext(), NotifyActivity.class));
                break;
            case R.id.ll_yue:
                startActivity(new Intent(getContext(), InvestActivity.class));
                break;

        }
    }


}
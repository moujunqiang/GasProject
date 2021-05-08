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
import com.android.gasproject.adapter.ImageAdapter;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements View.OnClickListener {


    private View inflate;
    private TextView tvTem, tvHum,tvNongdu;
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

        inflate.findViewById(R.id.ll_home_notice).setOnClickListener(this);
        inflate.findViewById(R.id.ll_home_repair).setOnClickListener(this);
        tvTem = inflate.findViewById(R.id.tv_current_tem);
        tvHum = inflate.findViewById(R.id.tv_current_hum);
        tvNongdu = inflate.findViewById(R.id.tv_current_nongdu);

        getRoomTemAndHum();
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
            case R.id.ll_home_repair:
                startActivity(new Intent(getContext(), RepairActivity.class));
                break;
            case R.id.ll_home_notice:
                startActivity(new Intent(getContext(), StayOutLateActivity.class));
                break;
        }
    }

    /**
     * 获取宿舍温湿度
     */
    public void getRoomTemAndHum() {
        StudentInfoBean studentInfo = getStudentInfo();
        EasyHttp.get(this)
                .api(new RegisterStudentApi()
                        .setPath("dormitory_getdata")
                        .setData("{\"dorm_id\":\"" + studentInfo.getFields().getStu_dormitory() + "\"}")
                        .setType("1"))
                .request(new HttpCallback<HttpData<List<DormitoryRoomBean>>>(this) {


                    @Override
                    public void onSucceed(HttpData<List<DormitoryRoomBean>> data) {
                        if (data.getData() != null && data.getData().size() > 0) {
                            DormitoryRoomBean dormitoryRoomBean = data.getData().get(0);
                            if (dormitoryRoomBean != null) {
                                tvTem.setText(dormitoryRoomBean.getFields().getDorm_tmp() + "℃");
                                tvHum.setText(dormitoryRoomBean.getFields().getDorm_hum() * 100 + "%");
                                tvNongdu.setText(dormitoryRoomBean.getFields().getDorm_smog() + "");

                            }
                        }

                    }

                    @Override
                    public void onFail(Exception e) {
                        super.onFail(e);
                    }
                });
    }
}
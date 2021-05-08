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


/**
 * desc   : 个人中心
 */
public final class MineFragment extends BaseFragment {


    private View inflate;
    private TextView tvName, tvPhone;

    public static MineFragment newInstance() {
        return new MineFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_mine, container, false);
        initView();
        return inflate;
    }

    protected void initView() {
        tvName = inflate.findViewById(R.id.me_tv_name);
        tvPhone = inflate.findViewById(R.id.me_tv_phone);


        //  跳转到设置页面
        inflate.findViewById(R.id.sb_mine_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        inflate.findViewById(R.id.sb_person_repair).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        inflate.findViewById(R.id.sb_person_web).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
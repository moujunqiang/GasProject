package com.android.gasproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.gasproject.BaseFragment;
import com.android.gasproject.R;
import com.android.gasproject.activity.AboutActivity;
import com.android.gasproject.activity.ChangePwdActivity;
import com.android.gasproject.activity.LoginActivity;
import com.android.gasproject.http.response.UserBean;
import com.android.gasproject.utils.SPUtils;


/**
 * desc   : 个人中心
 */
public final class MineFragment extends BaseFragment {


    private View inflate;
    private TextView tvName;
    private TextView tvStatus;

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
        UserBean userBean = SPUtils.getObject("user", UserBean.class, getContext());
        tvName = inflate.findViewById(R.id.tv_me_name);
        tvStatus = inflate.findViewById(R.id.tv_status);
        if (userBean != null) {
            tvName.setText(TextUtils.isEmpty(userBean.getNickname()) ? userBean.getAccount() : userBean.getNickname());
            tvStatus.setText(userBean.getGasstatus().equals("1")?"正常":"已断气");
        }

        //  跳转到设置页面
        inflate.findViewById(R.id.rl_me_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AboutActivity.class));
            }
        });

        inflate.findViewById(R.id.rl_me_quit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.put(getContext(), "login", false);
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
            }
        });

        inflate.findViewById(R.id.rl_me_change_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangePwdActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
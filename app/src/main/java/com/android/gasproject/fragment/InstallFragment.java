package com.android.gasproject.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.gasproject.BaseFragment;
import com.android.gasproject.R;
import com.android.gasproject.activity.AboutActivity;
import com.android.gasproject.activity.ChangePwdActivity;
import com.android.gasproject.activity.InvestActivity;
import com.android.gasproject.activity.LoginActivity;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.request.ChangeInstallStatusApi;
import com.android.gasproject.http.request.GetInstallApi;
import com.android.gasproject.http.response.InstallBean;
import com.android.gasproject.http.response.UserBean;
import com.android.gasproject.utils.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * desc   : 个人中心
 */
public final class InstallFragment extends BaseFragment {


    private BaseQuickAdapter<InstallBean, BaseViewHolder> adapter;

    public static InstallFragment newInstance(String type) {
        InstallFragment installFragment = new InstallFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        installFragment.setArguments(args);
        return installFragment;
    }

    private View inflate;
    private RecyclerView rvInstall;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_install, container, false);
        initView();
        return inflate;
    }

    protected void initView() {
        rvInstall = inflate.findViewById(R.id.rv_install);
        rvInstall.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BaseQuickAdapter<InstallBean, BaseViewHolder>(R.layout.item_install) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, InstallBean installBean) {
                baseViewHolder.setText(R.id.tv_install_address, installBean.getUaddress());
                baseViewHolder.setText(R.id.tv_install_name, installBean.getUname());
                baseViewHolder.setText(R.id.tv_install_phone, installBean.getUphone());
                baseViewHolder.setText(R.id.tv_install_info, installBean.getInfo());
                baseViewHolder.setText(R.id.tv_install_time, installBean.getCtime());

            }


        };
        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                InstallBean o = (InstallBean) adapter.getData().get(position);
                if ("0".equals(getArguments().getString("type"))) {
                    final AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(getContext());
                    normalDialog.setMessage("是否标记为已处理?");
                    normalDialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    changeInstallStatus(o.getId() + "");
                                }
                            });
                    normalDialog.setNegativeButton("关闭",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //...To-do
                                }
                            });
                    // 显示
                    normalDialog.show();
                }

                return false;
            }
        });
        rvInstall.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();

    }

    /**
     * 改变报修状态
     */
    public void changeInstallStatus(String id) {
        EasyHttp.post(this)
                .api(new ChangeInstallStatusApi()
                        .setId(id)
                        .setStatus("1")
                ).request(new OnHttpListener<HttpData<Object>>() {
            @Override
            public void onSucceed(HttpData<Object> result) {
                getData();
            }

            @Override
            public void onFail(Exception e) {
            }
        });
    }

    public void getData() {
        EasyHttp.post(this)
                .api(new GetInstallApi()
                        .setStatus(getArguments().getString("type"))
                ).request(new OnHttpListener<HttpData<List<InstallBean>>>() {
            @Override
            public void onSucceed(HttpData<List<InstallBean>> result) {
                adapter.setNewInstance(result.getData());
            }

            @Override
            public void onFail(Exception e) {
            }
        });
    }

}
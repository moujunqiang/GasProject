package com.android.gasproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.request.InvestHistoryApi;
import com.android.gasproject.http.request.LoginAPi;
import com.android.gasproject.http.response.History;
import com.android.gasproject.http.response.UserBean;
import com.android.gasproject.utils.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 充值记录
 */
public class InvestHistoryActivity extends BaseActivity {

    private RecyclerView rvHistory;
    private BaseQuickAdapter<History, BaseViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_history);
        bindActivity(this);
        initView();
    }

    private void initView() {
        rvHistory = (RecyclerView) findViewById(R.id.rv_history);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseQuickAdapter<History, BaseViewHolder>(R.layout.item_history) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, History history) {
                baseViewHolder.setText(R.id.tv_name, history.getUser().getAccount());
                baseViewHolder.setText(R.id.tv_money, history.getMoney());
                baseViewHolder.setText(R.id.tv_time, history.getCtime());
            }


        };
        rvHistory.setAdapter(adapter);
        getData();
    }

    /**
     * 获取历史记录数据
     */
    public void getData() {

        EasyHttp.post(this)
                .api(new InvestHistoryApi()
                        .setUid(getUserBean().getAccount() + "")
                ).request(new OnHttpListener<HttpData<List<History>>>() {
            @Override
            public void onSucceed(HttpData<List<History>> result) {
                adapter.setNewInstance(result.getData());
            }

            @Override
            public void onFail(Exception e) {
            }
        });
    }

}
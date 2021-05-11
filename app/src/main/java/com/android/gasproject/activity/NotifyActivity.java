package com.android.gasproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.request.InvestHistoryApi;
import com.android.gasproject.http.request.NotifyApi;
import com.android.gasproject.http.response.History;
import com.android.gasproject.http.response.NotifyBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.OnHttpListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 通知公告
 */
public class NotifyActivity extends BaseActivity {

    private RecyclerView rvNotify;
    private BaseQuickAdapter<NotifyBean, BaseViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        bindActivity(this);
        initView();
    }

    private void initView() {
        rvNotify = (RecyclerView) findViewById(R.id.rv_notify);
        rvNotify.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BaseQuickAdapter<NotifyBean, BaseViewHolder>(R.layout.item_notify) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, NotifyBean notifyBean) {
                baseViewHolder.setText(R.id.tv_notify_content, notifyBean.getNcontent());
                baseViewHolder.setText(R.id.tv_notify_time, notifyBean.getCtime());
                baseViewHolder.setText(R.id.tv_notify_title, notifyBean.getNtitle());
            }


        };
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(NotifyActivity.this, NotifyDetailActivity.class);
                NotifyBean o = (NotifyBean) adapter.getData().get(position);
                intent.putExtra("notify", o);
                startActivity(intent);
            }
        });
        rvNotify.setAdapter(adapter);
        getData();
    }

    /**
     * 获取历史记录数据
     */
    public void getData() {

        EasyHttp.post(this)
                .api(new NotifyApi()

                ).request(new OnHttpListener<HttpData<List<NotifyBean>>>() {
            @Override
            public void onSucceed(HttpData<List<NotifyBean>> result) {
                adapter.setNewInstance(result.getData());
            }

            @Override
            public void onFail(Exception e) {
            }
        });
    }

}
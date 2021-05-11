package com.android.gasproject.activity.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.activity.NotifyDetailActivity;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.request.DeleteNotifyApi;
import com.android.gasproject.http.request.DeleteUserApi;
import com.android.gasproject.http.request.NotifyApi;
import com.android.gasproject.http.response.NotifyBean;
import com.android.gasproject.http.response.UserBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.listener.OnHttpListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 通知公告
 */
public class AdminNotifyActivity extends BaseActivity {

    private RecyclerView rvNotify;
    private BaseQuickAdapter<NotifyBean, BaseViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notify);
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
                Intent intent = new Intent(AdminNotifyActivity.this, NotifyDetailActivity.class);
                NotifyBean o = (NotifyBean) adapter.getData().get(position);
                intent.putExtra("notify", o);
                startActivity(intent);
            }
        });
        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {

                final AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(AdminNotifyActivity.this);
                normalDialog.setTitle("删除");
                normalDialog.setMessage("是否要删除此这条通知?");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NotifyBean notifyBean = (NotifyBean) adapter.getData().get(position);
                                deleteNotify(notifyBean.getId() + "");
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
                return false;
            }
        });
        rvNotify.setAdapter(adapter);
        findViewById(R.id.fb_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminNotifyActivity.this, PublishNotify.class));
            }
        });
    }

    /**
     * 删除通知
     *
     * @param notifyId
     */
    public void deleteNotify(String notifyId) {
        EasyHttp.post(this)
                .api(new DeleteNotifyApi().setId(notifyId))
                .request(new HttpCallback<HttpData<List<UserBean>>>(this) {

                    @Override
                    public void onSucceed(HttpData<List<UserBean>> data) {
                        getData();
                        Toast.makeText(AdminNotifyActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getData();

    }

    /**
     * 获取通知数据
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
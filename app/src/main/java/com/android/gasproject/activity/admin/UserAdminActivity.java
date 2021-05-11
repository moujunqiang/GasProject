package com.android.gasproject.activity.admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;

import com.android.gasproject.activity.RegisterActivity;
import com.android.gasproject.activity.UserDetailActivity;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.request.DeleteUserApi;
import com.android.gasproject.http.request.GetUsersByKeyApi;
import com.android.gasproject.http.request.UsersApi;
import com.android.gasproject.http.response.UserBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 管理员页面
 */
public class UserAdminActivity extends BaseActivity implements OnRefreshListener {
    RelativeLayout rlSearch;
    RecyclerView rvHome;
    SmartRefreshLayout smart;
    private BaseQuickAdapter adapter;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin);
        initView();
    }


    protected void initView() {
        smart = findViewById(R.id.smart);
        rvHome = findViewById(R.id.rv_home);
        etSearch = findViewById(R.id.et_search);
        findViewById(R.id.fb_forum).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserAdminActivity.this, RegisterActivity.class);
                intent.putExtra("type", "add");
                startActivity(intent);
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 当按了搜索之后关闭软键盘
                    //如果搜索框文字为空 就显示所有
                    if (!TextUtils.isEmpty(etSearch.getText().toString())) {
                        getUserBy(etSearch.getText().toString());
                    } else {
                        Toast.makeText(UserAdminActivity.this, "请输入搜索文字", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }

                return false;
            }
        });
        smart.setOnRefreshListener(this);
        rvHome.setLayoutManager(new LinearLayoutManager(UserAdminActivity.this));
        adapter = new BaseQuickAdapter<UserBean, BaseViewHolder>(R.layout.item_user) {
            @Override
            protected void convert(@NotNull BaseViewHolder baseViewHolder, UserBean loginBean) {
                String nickname = loginBean.getNickname();
                String account = loginBean.getAccount();
                baseViewHolder.setText(R.id.tv_user_name, TextUtils.isEmpty(nickname) ? account : nickname);
            }

        };
        rvHome.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Intent intent = new Intent(UserAdminActivity.this, UserDetailActivity.class);
                UserBean o = (UserBean) adapter.getData().get(position);
                intent.putExtra("user", o);
                startActivity(intent);
            }
        });
        adapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                /* @setIcon 设置对话框图标
                 * @setTitle 设置对话框标题
                 * @setMessage 设置对话框消息提示
                 * setXXX方法返回Dialog对象，因此可以链式设置属性
                 */
                final AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(UserAdminActivity.this);
                normalDialog.setTitle("删除");
                normalDialog.setMessage("是否要删除此用户?");
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UserBean loginBean = (UserBean) adapter.getData().get(position);
                                deleteUser(loginBean.getId() + "");
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


    }

    @Override
    protected void onResume() {
        super.onResume();
        getUsers();

    }

    /**
     * 删除用户
     *
     * @param userId
     */
    public void deleteUser(String userId) {
        EasyHttp.post(this)
                .api(new DeleteUserApi().setUserId(userId))
                .request(new HttpCallback<HttpData<List<UserBean>>>(this) {

                    @Override
                    public void onSucceed(HttpData<List<UserBean>> data) {
                        getUsers();
                        Toast.makeText(UserAdminActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    /**
     * 获取所有得用户列表
     */
    public void getUsers() {
        EasyHttp.post(this)
                .api(new UsersApi())
                .request(new HttpCallback<HttpData<List<UserBean>>>(this) {

                    @Override
                    public void onSucceed(HttpData<List<UserBean>> data) {
                        smart.finishRefresh();
                        adapter.setNewData(data.getData());
                    }
                });
    }

    /**
     * 通过关键字查询用户列表
     *
     * @param key
     */
    public void getUserBy(String key) {
        EasyHttp.post(this)
                .api(new GetUsersByKeyApi().setKey(key))
                .request(new HttpCallback<HttpData<List<UserBean>>>(this) {

                    @Override
                    public void onSucceed(HttpData<List<UserBean>> data) {
                        smart.finishRefresh();
                        adapter.setNewData(data.getData());
                    }
                });
    }

    //下拉刷新
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        getUsers();
    }
}
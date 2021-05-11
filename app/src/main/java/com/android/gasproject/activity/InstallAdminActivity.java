package com.android.gasproject.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.adapter.BaseFragmentAdapter;
import com.android.gasproject.fragment.InstallFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * 报装管理页面
 */
public class InstallAdminActivity extends BaseActivity {
    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install_admin);
        bindActivity(this);
        initView();
    }


    protected void initView() {

        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        tablayout.setupWithViewPager(viewpager);
        BaseFragmentAdapter<Fragment> adapter = new BaseFragmentAdapter<Fragment>(this);
        adapter.addFragment(InstallFragment.newInstance("0"), "未处理");
        adapter.addFragment(InstallFragment.newInstance("1"), "已处理");
        viewpager.setAdapter(adapter);

    }

}
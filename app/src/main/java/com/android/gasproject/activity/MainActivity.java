package com.android.gasproject.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.gasproject.R;
import com.android.gasproject.adapter.BaseFragmentAdapter;
import com.android.gasproject.fragment.HomeFragment;
import com.android.gasproject.fragment.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * 学生主页
 */
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;

    private BaseFragmentAdapter<Fragment> mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.vp_home_pager);
        mBottomNavigationView = findViewById(R.id.bv_home_navigation);

        // 不使用图标默认变色
        mBottomNavigationView.setItemIconTintList(null);
        //设置底部按钮点击事件
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        initData();

    }


    protected void initData() {
        //给viewpager 添加fragment,对应主页的三个页面
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(HomeFragment.newInstance());
        mPagerAdapter.addFragment(MineFragment.newInstance());

        // 设置成懒加载模式
        mPagerAdapter.setLazyMode(true);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    mBottomNavigationView.setSelectedItemId(R.id.menu_home);
                }else {
                    mBottomNavigationView.setSelectedItemId(R.id.menu_mine);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.menu_mine:
                mViewPager.setCurrentItem(1);
                return true;

            default:
                break;
        }
        return false;
    }



    @Override
    protected void onDestroy() {
        mViewPager.setAdapter(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }

}
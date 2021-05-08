package com.android.gasproject;

import androidx.fragment.app.Fragment;

import com.android.gasproject.utils.SPUtils;
import com.hjq.http.listener.OnHttpListener;
import com.wega.library.loadingDialog.LoadingDialog;

/**
 * 基础类 处理一些公共方法
 */
public class BaseFragment extends Fragment implements OnHttpListener {
    private LoadingDialog loadingDialog;

    /**
     * 显示加载框
     */
    public void showLodinDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(getContext());

        }
        loadingDialog.loading();
    }



    /**
     * 隐藏加载框
     */
    public void hideLodingDialog() {
        loadingDialog.cancel();
    }


    @Override
    public void onSucceed(Object result) {

    }

    @Override
    public void onFail(Exception e) {

    }
}

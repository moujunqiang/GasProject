package com.android.gasproject.activity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.android.gasproject.BaseActivity;
import com.android.gasproject.R;
import com.android.gasproject.http.glide.GlideEngine;
import com.android.gasproject.http.model.HttpData;
import com.android.gasproject.http.model.UrlContant;
import com.android.gasproject.http.request.ChangeInfoApi;
import com.android.gasproject.http.request.UploadImage;
import com.android.gasproject.http.response.Image;
import com.android.gasproject.http.response.UserBean;
import com.android.gasproject.utils.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallback;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.util.List;


/**
 * desc   : 个人资料
 */
public final class PersonalDataActivity extends BaseActivity {
    private RelativeLayout mFlPersonDataHead;
    private ImageView mIvMeIcon;
    private TextView mTvMeName;
    private String name, usericon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        bindActivity(this);
        initView();
    }

    private void initView() {
        UserBean userInfo = getUserBean();
        name = userInfo.getNickname();
        usericon = userInfo.getUsericon();
        mFlPersonDataHead = (RelativeLayout) findViewById(R.id.fl_person_data_head);
        mIvMeIcon = (ImageView) findViewById(R.id.iv_me_icon);
        mTvMeName = (TextView) findViewById(R.id.tv_me_name);
        mTvMeName.setText(TextUtils.isEmpty(userInfo.getNickname()) ? "" : userInfo.getNickname());
        if (!TextUtils.isEmpty(userInfo.getUsericon())) {
            RequestOptions options = new RequestOptions();
            options.circleCrop();
            Glide.with(this).load(userInfo.getUsericon()).apply(options).into(mIvMeIcon);
        }
        //更改名字
        mTvMeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(PersonalDataActivity.this);
                AlertDialog.Builder inputDialog =
                        new AlertDialog.Builder(PersonalDataActivity.this);
                inputDialog.setTitle("请输入昵称").setView(editText);
                inputDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name = editText.getText().toString();
                                mTvMeName.setText(name);
                            }
                        }).show();
            }
        });
        //更换头像
        mFlPersonDataHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectClick();
            }
        });
    }

    @Override
    public void onRightClick(View v) {
        super.onRightClick(v);

        updatePersonalData(name, usericon);
    }

    /**
     * 更新用户信息
     */
    public void updatePersonalData(String username, String icon) {

        UserBean userInfo = getUserBean();
        EasyHttp.post(this)
                .api(new ChangeInfoApi()
                        .setUserId(userInfo.getId() + "")
                        .setName(username)
                        .setUsericon(icon))
                .request(new HttpCallback<HttpData<UserBean>>(this) {

                    @Override
                    public void onSucceed(HttpData<UserBean> data) {
                        //成功跳转到主页
                        Toast.makeText(PersonalDataActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        //更新本地缓存的用户信息
                        UserBean userInfo1 = getUserBean();
                        userInfo1.setNickname(username);
                        userInfo1.setUsericon(usericon);
                        SPUtils.setObject("user", userInfo1, PersonalDataActivity.this);
                        finish();
                    }

                });

    }

    private void selectClick() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofAll())
                .imageEngine(GlideEngine.createGlideEngine())
                .maxSelectNum(1)
                .forResult(new OnResultCallbackListener<LocalMedia>() {
                    @Override
                    public void onResult(List<LocalMedia> result) {
                        // onResult Callback
                        LocalMedia media = result.get(0);
                        String path;
                        // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
                        boolean compressPath = media.isCompressed() || (media.isCut() && media.isCompressed());
                        // 裁剪过
                        boolean isCutPath = media.isCut() && !media.isCompressed();

                        if (isCutPath) {
                            path = media.getCutPath();
                        } else if (compressPath) {
                            path = media.getCompressPath();
                        } else if (!TextUtils.isEmpty(media.getAndroidQToPath())) {
                            // AndroidQ特有path
                            path = media.getAndroidQToPath();
                        } else if (!TextUtils.isEmpty(media.getRealPath())) {
                            // 原图
                            path = media.getRealPath();
                        } else {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                                path = PictureFileUtils.getPath(PersonalDataActivity.this, Uri.parse(media.getPath()));
                            } else {
                                path = media.getPath();
                            }
                        }

                        upLoadImage(path);

                    }

                    @Override
                    public void onCancel() {
                        // onCancel Callback
                    }
                });
    }

    /**
     * 上传图片
     *
     * @param path 图片路径
     */
    public void upLoadImage(String path) {
        EasyHttp.post(this)
                .api(new UploadImage().setPicFile(new File(path)))
                .request(new HttpCallback<Image>(this) {
                    @Override
                    public void onSucceed(com.android.gasproject.http.response.Image result) {
                        super.onSucceed(result);
                        usericon = result.getPath();
                        Glide.with(PersonalDataActivity.this).load(UrlContant.BASE_IMAGE_URL + usericon).into(mIvMeIcon);
                    }

                    @Override
                    public void onFail(Exception e) {
                        super.onFail(e);
                        Toast.makeText(PersonalDataActivity.this, "图书上传失败，请重试", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
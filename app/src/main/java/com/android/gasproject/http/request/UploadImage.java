 package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;
import com.hjq.http.config.IRequestHost;
import com.hjq.http.config.IRequestServer;

import java.io.File;

 /**

  * desc   : 上传图片
  */
 public class UploadImage  implements IRequestApi{




     @Override
     public String getApi() {
         return "user/upload";
     }

     /** 本地图片 */
     private File picFile;

     public UploadImage setPicFile(File picFile) {
         this.picFile = picFile;
         return this;

     }


 }
package com.tianzhuan.net_mvc.bean;

import android.graphics.Bitmap;

public class ImageBean {
    //网络图片地址
    private String requestPath;
    //结果返回bitmap对象
    private Bitmap mBitmap;

    public String getRequestPath() {
        return requestPath == null ? "" : requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }
}

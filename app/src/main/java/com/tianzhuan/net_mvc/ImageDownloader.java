package com.tianzhuan.net_mvc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tianzhuan.net_mvc.bean.ImageBean;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownloader {
    //成功
    static final int SUCESS=200;
    //失败
    static final int ERROR=404;

    public void down(Callback callback, ImageBean imageBean) {
        new Thread(new Downloader(callback,imageBean)).start();
    }

    private class Downloader implements Runnable {
        private final Callback callback;
        private final ImageBean imageBean;
        public Downloader(Callback callback, ImageBean imageBean) {
            this.callback=callback;
            this.imageBean=imageBean;
        }

        @Override
        public void run() {
            try {
                URL url=new URL(imageBean.getRequestPath());
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("GET");
                if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    showUi(SUCESS,bitmap);
                }else {
                    showUi(ERROR,null);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showUi(ERROR,null);
            }
        }

        private void showUi(int resultCode, Bitmap bitmap) {
            if(callback!=null){
                imageBean.setBitmap(bitmap);
                callback.callback(resultCode,imageBean);
            }
        }
    }
}

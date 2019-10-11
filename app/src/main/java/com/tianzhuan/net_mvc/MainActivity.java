package com.tianzhuan.net_mvc;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tianzhuan.net_mvc.bean.ImageBean;

public class MainActivity extends AppCompatActivity implements Callback{
    private ImageView mImageView;
    private final static String PATH = "http://qiniu.bzmaster.cn/pbox/bzmegate/photo/20191011115910455-1730000518.jpg";
    private Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case ImageDownloader.SUCESS://成功
                    mImageView.setImageBitmap((Bitmap) msg.obj);
                    break;
                case ImageDownloader.ERROR://失败
                    Toast.makeText(MainActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView=findViewById(R.id.iv_image);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5000);
            }
        }).start();

    }

    //点击事件
    public void getImage(View view) {
        ImageBean imageBean=new ImageBean();
        imageBean.setRequestPath(PATH);
        new ImageDownloader().down(this,imageBean);
    }

    @Override
    public void callback(int resultCode, ImageBean imageBean) {
        Message message=mHandler.obtainMessage(resultCode);
        message.obj=imageBean.getBitmap();
        mHandler.sendMessageDelayed(message,500);
    }
}

package com.tianzhuan.net_mvc;

import com.tianzhuan.net_mvc.bean.ImageBean;

public interface Callback {
    /**
     * @param resultCode 请求结果返回标识码
     * @param imageBean model层数据中bitmap对象（用于C层刷新V）
     */
    void callback(int resultCode, ImageBean imageBean);
}

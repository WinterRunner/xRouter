package com.winterrunner.router.interfaces;


import com.winterrunner.router.bean.RouterResponseBean;

/**
 * Created by L.K.X on 2017/5/15.
 */

public interface OnResponseListener{

    /**
     * @param routerResponseBean
     * @return 返回boolean值,若是需要继续接收回调,返回true,在最后一次数据返回后,要返回false,否则会导致内存泄漏
     */
    boolean onSuccess(RouterResponseBean routerResponseBean);
    void onError();
}

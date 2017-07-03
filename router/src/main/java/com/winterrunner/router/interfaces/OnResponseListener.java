package com.winterrunner.router.interfaces;


import com.winterrunner.router.bean.RouterResponseBean;

/**
 * Created by L.K.X on 2017/5/15.
 */

public interface OnResponseListener{
    void onSuccess(RouterResponseBean routerResponseBean);
    void onError();
}

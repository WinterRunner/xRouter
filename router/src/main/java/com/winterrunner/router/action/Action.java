package com.winterrunner.router.action;

import android.content.Context;

import com.winterrunner.router.bean.RouterRequestBean;
import com.winterrunner.router.bean.RouterResponseBean;
import com.winterrunner.router.interfaces.OnResponseListener;

/**
 * author: L.K.X
 * created on: 2017/7/3 下午5:26
 * description:
 */

public abstract class Action {
    private OnResponseListener onResponseListener;

    public abstract RouterResponseBean invoke(Context context, RouterRequestBean routerRequest);

    public abstract void invoke(Context context, RouterRequestBean routerRequest, OnResponseListener onResponseListener);

    public void request(Context context, RouterRequestBean routerRequest, OnResponseListener onResponseListener) {
        this.onResponseListener = onResponseListener;
        invoke(context, routerRequest, onResponseListener);
    }
    public void post(RouterResponseBean routerResponseBean){
        if(onResponseListener!=null){
            onResponseListener.onSuccess(routerResponseBean);
        }
    }
}

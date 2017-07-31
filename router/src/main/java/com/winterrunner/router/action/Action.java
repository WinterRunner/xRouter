package com.winterrunner.router.action;

import android.content.Context;

import com.winterrunner.router.bean.RouterRequestBean;
import com.winterrunner.router.bean.RouterResponseBean;
import com.winterrunner.router.interfaces.OnResponseListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author: L.K.X
 * created on: 2017/7/3 下午5:26
 * description:
 */

public abstract class Action {
    private List<OnResponseListener> list_response_listener = new ArrayList<>();

    public abstract RouterResponseBean invoke(Context context, RouterRequestBean routerRequest);

    public abstract void invoke(Context context, RouterRequestBean routerRequest, OnResponseListener onResponseListener);

    public void request(Context context, RouterRequestBean routerRequest, OnResponseListener onResponseListener) {
        if(!list_response_listener.contains(onResponseListener)){
            list_response_listener.add(onResponseListener);
        }
        invoke(context, routerRequest, onResponseListener);
    }
    public void post(RouterResponseBean routerResponseBean){
        for (int i = 0; i < list_response_listener.size(); i++) {
            list_response_listener.get(i).onSuccess(this,routerResponseBean);
        }
    }
    public void release(OnResponseListener onResponseListener){
        list_response_listener.remove(onResponseListener);
    }
}

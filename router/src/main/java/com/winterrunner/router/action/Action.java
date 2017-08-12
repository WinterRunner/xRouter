package com.winterrunner.router.action;

import android.content.Context;

import com.winterrunner.router.bean.RouterRequestBean;
import com.winterrunner.router.bean.RouterResponseBean;
import com.winterrunner.router.controller.ActionManager;
import com.winterrunner.router.interfaces.OnResponseListener;

import java.util.ArrayList;

/**
 * author: L.K.X
 * created on: 2017/7/3 下午5:26
 * description:
 */

public abstract class Action {
    private ArrayList<OnResponseListener> responseListeners = new ArrayList<>();

    public abstract RouterResponseBean invoke(Context context, RouterRequestBean routerRequest);

    public abstract void invoke(Context context, RouterRequestBean routerRequest, OnResponseListener onResponseListener);

    public Action request(Context context, RouterRequestBean routerRequest, OnResponseListener onResponseListener) {
        if (!responseListeners.contains(onResponseListener)) {
            responseListeners.add(onResponseListener);
        }
        invoke(context, routerRequest, onResponseListener);
        return this;
    }

    public synchronized void post(RouterResponseBean routerResponseBean) {
        for (int i = responseListeners.size() - 1; i >= 0; i--) {
            responseListeners.get(i).onSuccess(this, routerResponseBean);
        }
    }

    public synchronized void releaseListener(OnResponseListener onResponseListener) {
        if (onResponseListener != null && responseListeners.contains(onResponseListener))
            responseListeners.remove(onResponseListener);
    }

    public synchronized void releaseSelf() {
        ActionManager.getInstance().release(getClass().getName());
    }
}

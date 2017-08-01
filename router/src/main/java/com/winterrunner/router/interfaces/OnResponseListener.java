package com.winterrunner.router.interfaces;


import com.winterrunner.router.action.Action;
import com.winterrunner.router.bean.RouterResponseBean;

/**
 * Created by L.K.X on 2017/5/15.
 */

public interface OnResponseListener{

    /**
     * @param action 当不需要再接收回调的时候，需要调用aciton.release(...)来释放回调
     * @param routerResponseBean 回调bean
     */
    void onSuccess(Action action, RouterResponseBean routerResponseBean);
    void onError();
}

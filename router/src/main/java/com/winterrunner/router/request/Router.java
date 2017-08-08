package com.winterrunner.router.request;

import android.content.Context;

import com.winterrunner.router.action.Action;
import com.winterrunner.router.bean.RouterRequestBean;
import com.winterrunner.router.bean.RouterResponseBean;
import com.winterrunner.router.controller.ActionManager;
import com.winterrunner.router.interfaces.OnResponseListener;
import com.winterrunner.router.utils.Log;


/**
 * Created by L.K.X on 2017/5/15.
 */

public class Router {


    private static Router instance;

    private Router() {
    }

    public static Router getDefault() {
        if (instance == null) {
            synchronized (Router.class) {
                if (instance == null) {
                    instance = new Router();
                }
            }
        }
        return instance;
    }


    public RouterResponseBean request(Context context, RouterRequestBean routerRequestBean) {
        RouterResponseBean routerResponseBean = null;
        try {
            routerResponseBean = ActionManager.getInstance()
                    .getAction(routerRequestBean.getActionName())
                    .invoke(context, routerRequestBean);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("请求发生异常："+e.toString());
            routerResponseBean = new RouterResponseBean();
            routerResponseBean.status(RouterResponseBean.ERROR);
        }
        return routerResponseBean;
    }

    public Action request(Context context, RouterRequestBean routerRequestBean, OnResponseListener onResponseListener) {
        Action action = null;
        try {
            action = ActionManager.getInstance()
                    .getAction(routerRequestBean.getActionName())
                    .request(context, routerRequestBean, onResponseListener);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("请求发生异常："+e.toString());
            onResponseListener.onError();
        }
        return action;
    }

    public <T extends Action> void post(Class<T> actionClazz, RouterResponseBean routerResponseBean) {
        try {
            ActionManager.getInstance()
                    .getAction(actionClazz.getName())
                    .post(routerResponseBean);
            Log.e("成功发出异步回调");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("异步回调发生异常："+e.toString());
        }
    }


    public void releaseAll() {
        ActionManager.getInstance().releaseAll();
    }

    public void release(String actionName) {
        ActionManager.getInstance().release(actionName);
    }


    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    private boolean isDebug = false;

}

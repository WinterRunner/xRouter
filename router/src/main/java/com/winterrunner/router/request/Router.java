package com.winterrunner.router.request;

import android.content.Context;

import com.winterrunner.router.action.Action;
import com.winterrunner.router.bean.RouterRequestBean;
import com.winterrunner.router.bean.RouterResponseBean;
import com.winterrunner.router.controller.ProviderManager;
import com.winterrunner.router.interfaces.OnResponseListener;
import com.winterrunner.router.provider.Provider;


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
            routerResponseBean = ProviderManager.getInstance()
                    .getProvider(routerRequestBean.getProviderName())
                    .getAction(routerRequestBean.getActionName())
                    .invoke(context, routerRequestBean);
        } catch (Exception e) {
            e.printStackTrace();
            routerResponseBean = new RouterResponseBean();
            routerResponseBean.status(RouterResponseBean.ERROR);
        }
        return routerResponseBean;
    }

    public void request(Context context, RouterRequestBean routerRequestBean, OnResponseListener onResponseListener) {
        try {
            ProviderManager.getInstance()
                    .getProvider(routerRequestBean.getProviderName())
                    .getAction(routerRequestBean.getActionName())
                    .invoke(context, routerRequestBean, onResponseListener);
        } catch (Exception e) {
            e.printStackTrace();
            onResponseListener.onError();
        }
    }


//register  unregister   post


    private Action action;

    public <T extends Provider, E extends Action> void register(Class<T> providerClazz, Class<E> actionClazz) {
        try {
            action = ProviderManager.getInstance()
                    .getProvider(providerClazz.getName())
                    .getAction(actionClazz.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T extends Action> void unregister(Class<T> actionClazz) {
        action = null;
    }

    public void post(){

    }

}

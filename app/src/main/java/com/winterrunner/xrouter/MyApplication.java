package com.winterrunner.xrouter;

import android.app.Application;

import com.winterrunner.router.controller.RouterManager;

/**
 * author: L.K.X
 * created on: 2017/7/10 下午12:24
 * description:
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initRouter();
    }

    private void initRouter() {
        RouterManager.getInstance().registerProvider("com.winterrunner.xrouter.provider.MainProvider");
        RouterManager.getInstance().registerProvider("com.winterrunner.ordermanage.provider.OrderProvider");
        RouterManager.getInstance().registerProvider("com.winterrunner.goodmanage.provider.GoodProvider");
    }
}

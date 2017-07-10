package com.winterrunner.ordermanage.provider;

import com.winterrunner.ordermanage.action.OpenOrderPageAction;
import com.winterrunner.router.provider.Provider;

/**
 * author: L.K.X
 * created on: 2017/7/10 下午12:25
 * description:
 */

public class OrderProvider extends Provider{
    @Override
    public void registerAction() {
        addAction(new OpenOrderPageAction());
    }
}

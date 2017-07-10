package com.winterrunner.ordermanage.action;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.winterrunner.ordermanage.OrderActivity;
import com.winterrunner.router.action.Action;
import com.winterrunner.router.bean.RouterRequestBean;
import com.winterrunner.router.bean.RouterResponseBean;
import com.winterrunner.router.interfaces.OnResponseListener;

/**
 * author: L.K.X
 * created on: 2017/7/10 下午12:26
 * description:
 */

public class OpenOrderPageAction extends Action{
    @Override
    public RouterResponseBean invoke(Context context, RouterRequestBean routerRequest) {
        Intent intent = new Intent(context, OrderActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("content",routerRequest.getStringValue("content"));
        context.startActivity(intent);






        //return null;//如果不需要返回值，直接返回null即可
        RouterResponseBean routerResponseBean = new RouterResponseBean()
                                                .status(RouterResponseBean.SUCCESS)
                                                .put("result","11111111111111111，哈哈哈");
        return routerResponseBean;
    }

    @Override
    public void invoke(Context context, RouterRequestBean routerRequest, OnResponseListener onResponseListener) {
        Intent intent = new Intent(context, OrderActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.putExtra("content",routerRequest.getStringValue("content"));
        context.startActivity(intent);


        //1.要是需要在这里处理数据，可能有耗时操作，开启线程异步操作等，等处理完毕，异步返回结果，如下
//        if(onResponseListener!=null){
//            try {
//                RouterResponseBean routerResponseBean = new RouterResponseBean()
//                        .status(RouterResponseBean.SUCCESS)
//                        .put("result","成功同步开启了订单模块，哈哈哈");
//                onResponseListener.onSuccess(routerResponseBean);
//            }catch (Exception e){
//                onResponseListener.onError();
//            }
//       }

        //2.如果要在开启的OrderActivity中去返回数据，请看OrderActivity中的使用方式，可以异步返回数据


    }
}

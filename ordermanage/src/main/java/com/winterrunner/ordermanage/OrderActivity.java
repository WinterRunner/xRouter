package com.winterrunner.ordermanage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.winterrunner.basecommon.base.BaseActivity;
import com.winterrunner.ordermanage.action.OpenOrderPageAction;
import com.winterrunner.ordermanage.provider.OrderProvider;
import com.winterrunner.router.bean.RouterResponseBean;
import com.winterrunner.router.request.Router;

/**
 * author: L.K.X
 * created on: 2017/7/10 下午12:17
 * description:
 */

public class OrderActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_om);

        //异步返回结果，首先要注册Router
        Router.getDefault().register(OrderProvider.class, OpenOrderPageAction.class);


        initView();
    }

    private void initView() {
        findViewById(R.id.iv_back_om).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView tv_show = (TextView) findViewById(R.id.tv_show_om);
        tv_show.setText(getIntent().getStringExtra("content"));
    }

    public void start1(View view) {

    }

    public void start2(View view) {

    }

    //异步返回结果
    public void startresult(View view) {
        RouterResponseBean responseBean = new RouterResponseBean().status(RouterResponseBean.SUCCESS)
                .put("result", "666666666666666");
        Router.getDefault().post(responseBean);
    }


    @Override
    protected void onDestroy() {
        //异步返回结果，页面关闭的时候释放router
        Router.getDefault().unregister(OpenOrderPageAction.class);
        super.onDestroy();
    }
}

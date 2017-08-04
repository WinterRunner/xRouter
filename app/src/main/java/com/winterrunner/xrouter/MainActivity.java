package com.winterrunner.xrouter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.winterrunner.basecommon.base.BaseActivity;
import com.winterrunner.router.action.Action;
import com.winterrunner.router.bean.RouterRequestBean;
import com.winterrunner.router.bean.RouterResponseBean;
import com.winterrunner.router.interfaces.OnResponseListener;
import com.winterrunner.router.request.Router;

public class MainActivity extends BaseActivity {

    private Action action222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start1(View view) {
        //同步返回结果
        RouterResponseBean responseBean = Router.getDefault().request(this, new RouterRequestBean()
                .provider("com.winterrunner.ordermanage.provider.OrderProvider")
                .action("com.winterrunner.ordermanage.action.OpenOrderPageAction")
                .from("main")
                .put("content", "我是首页跳转而来,同步开启的"));

        Toast.makeText(this, "同步返回结果为："+responseBean.getStringValue("result"), Toast.LENGTH_SHORT).show();
    }


    //11异步回调，匿名内部类内部直接释放资源
    public void start11(View view) {

        Router.getDefault().request(this, new RouterRequestBean()
                .provider("com.winterrunner.ordermanage.provider.OrderProvider")
                .action("com.winterrunner.ordermanage.action.OpenOrderPageAction")
                .from("main")
                .put("content", "我是首页跳转而来，异步开启的，可以异步返回结果给主页"), new OnResponseListener() {

            @Override
            public void onSuccess(Action action,RouterResponseBean routerResponseBean) {
                //异步返回结果
                Toast.makeText(MainActivity.this, "主页收到异步返回的结果："+routerResponseBean.getStringValue("result"), Toast.LENGTH_SHORT).show();

                action.release(this);//在不需要再接收回调的时候，需要手动释放
            }

            @Override
            public void onError() {

            }
        });



    }


    //22异步回调，拿到action,在需要的地方进行释放
    public void start22(View view) {
        action222 = Router.getDefault().request(this, new RouterRequestBean()
                .provider("com.winterrunner.ordermanage.provider.OrderProvider")
                .action("com.winterrunner.ordermanage.action.OpenOrderPageAction")
                .from("main")
                .put("content", "我是首页跳转而来，异步开启的，可以异步返回结果给主页"),onResponseListener222);
    }
    //释放
    @Override
    protected void onDestroy() {
        if(action222!=null){
            action222.release(onResponseListener222);
        }
        super.onDestroy();
    }
    OnResponseListener onResponseListener222 = new OnResponseListener() {

        @Override
        public void onSuccess(Action action,RouterResponseBean routerResponseBean) {
            //异步返回结果
            Toast.makeText(MainActivity.this, "主页收到异步返回的结果："+routerResponseBean.getStringValue("result"), Toast.LENGTH_SHORT).show();

            action.release(this);//在不需要再接收回调的时候，需要手动释放
        }

        @Override
        public void onError() {

        }
    };

}

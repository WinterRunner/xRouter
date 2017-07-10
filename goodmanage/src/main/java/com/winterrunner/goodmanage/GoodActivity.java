package com.winterrunner.goodmanage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.winterrunner.basecommon.base.BaseActivity;

/**
 * author: L.K.X
 * created on: 2017/7/10 下午12:08
 * description:
 */

public class GoodActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_gm);
        initView();
    }

    private void initView() {
        TextView tv_show = (TextView) findViewById(R.id.tv_show);
    }

    public void start1(View view) {

    }

    public void start2(View view) {

    }
}

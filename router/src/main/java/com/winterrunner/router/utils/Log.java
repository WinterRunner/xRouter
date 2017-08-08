package com.winterrunner.router.utils;

import android.text.TextUtils;

import com.winterrunner.router.request.Router;

/**
 * author: L.K.X
 * created on: 2017/8/8 上午11:17
 * description:
 */

public class Log {

    private static String TAG = "router";

    public static void setTAG(String _tag) {
        if (!TextUtils.isEmpty(_tag)) {
            TAG = _tag;
        }
    }

    public static void e(String content) {
        if (Router.getDefault().isDebug() && !TextUtils.isEmpty(content)) {
            android.util.Log.e(TAG, content);
        }
    }

    public static void i(String content) {
        if (Router.getDefault().isDebug() && !TextUtils.isEmpty(content)) {
            android.util.Log.i(TAG, content);
        }
    }
}

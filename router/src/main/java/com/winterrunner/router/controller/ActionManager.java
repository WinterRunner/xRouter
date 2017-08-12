package com.winterrunner.router.controller;

import com.winterrunner.router.action.Action;
import com.winterrunner.router.utils.Log;

import java.util.HashMap;

/**
 * author: L.K.X
 * created on: 2017/8/8 上午11:32
 * description:
 */

public class ActionManager {


    private static ActionManager instance;

    private ActionManager() {
    }


    public static ActionManager getInstance() {
        if (instance == null) {
            synchronized (ActionManager.class) {
                if (instance == null) {
                    instance = new ActionManager();
                }
            }
        }
        return instance;
    }

    private HashMap<String, Action> actions = new HashMap<>();

    public Action getAction(String actionName) throws Exception {
        Action iAction = actions.get(actionName);
        if (iAction == null) {
            addAction(actionName);
        }
        Log.i("成功返回action:" + actionName);
        return actions.get(actionName);
    }

    private void addAction(String actionName) throws Exception {
        Class<?> aClass = Class.forName(actionName);
        if (actions.get(actionName) == null) {
            actions.put(actionName, (Action) aClass.newInstance());
        }
    }

    public void releaseAll() {
        actions.clear();
        Log.i("释放所有action成功");
    }

    public void release(String actionName) {
        if (actionName != null && actions.containsKey(actionName)) {
            actions.remove(actionName);
            Log.i("释放指定action成功:"+actionName);
        }
    }
}

package com.winterrunner.router.provider;


import com.winterrunner.router.action.Action;

import java.util.HashMap;

/**
 * Created by L.K.X on 2017/5/15.
 */

public abstract class Provider {

    private HashMap<String, Action> actions = new HashMap<>();

    public Action getAction(String actionName) {
        Action iAction = actions.get(actionName);
        if (iAction == null) {
            addAction(actionName);
        }
        return actions.get(actionName);
    }

    private void addAction(String actionName) {
        try {
            Class<?> aClass = Class.forName(actionName);
            if (actions.get(actionName) == null) {
                actions.put(actionName, (Action) aClass.newInstance());
            }
        } catch (Exception e) {
            //反射找不到action
            e.printStackTrace();
        }
    }

    public void releaseAll() {
        actions.clear();
    }

    public void release(String actionName) {
        if (actionName != null && actions.containsKey(actionName)) {
            actions.remove(actionName);
        }
    }
}

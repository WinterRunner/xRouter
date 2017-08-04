package com.winterrunner.router.provider;


import com.winterrunner.router.action.Action;

import java.util.HashMap;

/**
 * Created by L.K.X on 2017/5/15.
 */

public abstract class Provider {

    private HashMap<String, Action> map_actions = new HashMap<>();

    public Action getAction(String actionName) {
        Action iAction = map_actions.get(actionName);
        if (iAction == null) {
            addAction(actionName);
        }
        return map_actions.get(actionName);
    }

    private void addAction(String actionName) {
        try {
            Class<?> aClass = Class.forName(actionName);
            if (map_actions.get(actionName) == null) {
                map_actions.put(actionName, (Action) aClass.newInstance());
            }
        } catch (Exception e) {
            //反射找不到action
            e.printStackTrace();
        }
    }

    public void releaseAll() {
        map_actions.clear();
    }

    public void release(String actionName) {
        if (actionName != null && map_actions.containsKey(actionName)) {
            map_actions.remove(actionName);
        }
    }
}

package com.winterrunner.router.provider;


import android.util.Log;

import com.winterrunner.router.action.Action;

import java.util.HashMap;

/**
 * Created by L.K.X on 2017/5/15.
 */

public abstract class Provider {

    public Provider(){
        registerAction();
    }


    private HashMap<String, Action> map_actions = new HashMap<>();


    public void addAction(Action iAction) {
        if (map_actions.get(iAction.getClass().getName()) == null) {
            map_actions.put(iAction.getClass().getName(), iAction);
        }
    }

    public Action getAction(String actionName) {
        Action iAction = map_actions.get(actionName);
        if (iAction == null) {
            Log.e("router","没有匹配到IAction");
        } else {
            //Log.e("router","匹配到IAction: "+actionName);
        }
        return iAction;
    }

    public abstract void registerAction();

}

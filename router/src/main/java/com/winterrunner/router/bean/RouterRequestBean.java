package com.winterrunner.router.bean;

import java.util.HashMap;

/**
 * Created by L.K.X on 2017/5/15.
 */

public class RouterRequestBean{

    private String from;
    private String actionName;
    private String providerName;



    private HashMap<String,Object> map_request = new HashMap<>();

    public RouterRequestBean provider(String providerName) {
        this.providerName = providerName;
        return this;
    }

    public RouterRequestBean action(String actionName) {
        this.actionName = actionName;
        return this;
    }

    public RouterRequestBean from(String from) {
        this.from = from;
        return this;
    }

    public RouterRequestBean put(String key,Object value){
        map_request.put(key,value);
        return this;
    }

    public String getFrom() {
        return from;
    }

    public String getActionName() {
        return actionName;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getStringValue(String key){
        try {
            return (String) map_request.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public <T> T  getObject(Class<T> clazz,String key){
        try {
            return (T) map_request.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

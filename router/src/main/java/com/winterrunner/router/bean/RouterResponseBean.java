package com.winterrunner.router.bean;

import java.util.HashMap;

/**
 * Created by L.K.X on 2017/5/15.
 */

public class RouterResponseBean{

    public static final int SUCCESS = 1;
    public static final int ERROR = 2;

    private int status;
    private String from;



    private HashMap<String,Object> map_response = new HashMap<>();

    public RouterResponseBean from(String from) {
        this.from = from;
        return this;
    }

    public RouterResponseBean status(int status) {
        this.status = status;
        return this;
    }


    public int getStatus() {
        return status;
    }

    public String getFrom() {
        return from;
    }

    public RouterResponseBean put(String key,Object value){
        map_response.put(key,value);
        return this;
    }

    public String getStringValue(String key){
        try {
            return (String) map_response.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;}
    public <T> T  getObjectValue(Class<T> clazz,String key){
        try {
            return (T) map_response.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

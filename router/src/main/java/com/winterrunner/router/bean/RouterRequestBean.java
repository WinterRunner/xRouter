package com.winterrunner.router.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by L.K.X on 2017/5/15.
 */

public class RouterRequestBean implements Parcelable{

    private String from;
    private String actionName;
    private String providerName;
    private HashMap<String,Object> map_request = new HashMap<>();

    public RouterRequestBean(){}

    protected RouterRequestBean(Parcel in) {
        from = in.readString();
        actionName = in.readString();
        providerName = in.readString();
        map_request = in.readHashMap(HashMap.class.getClassLoader());
    }

    public static final Creator<RouterRequestBean> CREATOR = new Creator<RouterRequestBean>() {
        @Override
        public RouterRequestBean createFromParcel(Parcel in) {
            return new RouterRequestBean(in);
        }

        @Override
        public RouterRequestBean[] newArray(int size) {
            return new RouterRequestBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(from);
        dest.writeString(actionName);
        dest.writeString(providerName);
        dest.writeMap(map_request);
    }




    ///////////////

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

    public RouterRequestBean put(String key, Object value){
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
    public <T> T  getObjectValue(Class<T> clazz,String key){
        try {
            return (T) map_request.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

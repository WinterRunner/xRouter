package com.winterrunner.router.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by L.K.X on 2017/5/15.
 */

public class RouterResponseBean implements Parcelable {

    public static final int SUCCESS = 1;
    public static final int ERROR = 2;

    private int status;
    private String from;


    private HashMap<String, Object> responseParams = new HashMap<>();

    public RouterResponseBean(){}

    protected RouterResponseBean(Parcel in) {
        status = in.readInt();
        from = in.readString();
        responseParams = in.readHashMap(HashMap.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeString(from);
        dest.writeMap(responseParams);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RouterResponseBean> CREATOR = new Creator<RouterResponseBean>() {
        @Override
        public RouterResponseBean createFromParcel(Parcel in) {
            return new RouterResponseBean(in);
        }

        @Override
        public RouterResponseBean[] newArray(int size) {
            return new RouterResponseBean[size];
        }
    };


    //////////
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

    public RouterResponseBean put(String key, Object value) {
        responseParams.put(key, value);
        return this;
    }

    public String getStringValue(String key) {
        try {
            return (String) responseParams.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T getObjectValue(Class<T> clazz, String key) {
        try {
            return (T) responseParams.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

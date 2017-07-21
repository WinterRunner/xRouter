package com.winterrunner.router.controller;


import com.winterrunner.router.provider.Provider;

import java.util.HashMap;

/**
 * Created by L.K.X on 2017/5/12.
 */

public class RouterManager {

    private HashMap<String,Provider> map_providers = new HashMap<>();


    private static RouterManager instance;

    private RouterManager(){}


    public static RouterManager getInstance(){
        if(instance==null){
            synchronized (RouterManager.class){
                if(instance==null){
                    instance = new RouterManager();
                }
            }
        }
        return instance;
    }

    public Provider getProvider(String providerName){
        Provider iProvider = map_providers.get(providerName);
        if (iProvider == null) {
            registerProvider(providerName);
        }
        return map_providers.get(providerName);
    }

    private void registerProvider(String providerName){
        try {
            Class<?> aClass = Class.forName(providerName);
            if(map_providers.get(providerName)==null){
                map_providers.put(providerName, (Provider) aClass.newInstance());
            }
        } catch (Exception e) {
            //不存在指定的provider
            e.printStackTrace();
        }
    }
}

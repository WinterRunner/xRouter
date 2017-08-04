package com.winterrunner.router.controller;


import com.winterrunner.router.provider.Provider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by L.K.X on 2017/5/12.
 */

public class RouterManager {

    private HashMap<String, Provider> map_providers = new HashMap<>();


    private static RouterManager instance;

    private RouterManager() {
    }


    public static RouterManager getInstance() {
        if (instance == null) {
            synchronized (RouterManager.class) {
                if (instance == null) {
                    instance = new RouterManager();
                }
            }
        }
        return instance;
    }

    public Provider getProvider(String providerName) {
        Provider iProvider = map_providers.get(providerName);
        if (iProvider == null) {
            registerProvider(providerName);
        }
        return map_providers.get(providerName);
    }

    private synchronized void registerProvider(String providerName) {
        try {
            Class<?> aClass = Class.forName(providerName);
            if (map_providers.get(providerName) == null) {
                map_providers.put(providerName, (Provider) aClass.newInstance());
            }
        } catch (Exception e) {
            //不存在指定的provider
            e.printStackTrace();
        }
    }


    public synchronized void releaseAll() {
        Iterator iterator = map_providers.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            //Object key = entry.getKey();
            ((Provider) entry.getValue()).releaseAll();
        }
        map_providers.clear();
    }

    public synchronized void release(String providerName, String actionName) {
        if(providerName!=null){
            Iterator iterator = map_providers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                //Object key = entry.getKey();
                Provider provider = (Provider) entry.getValue();
                if (provider.getClass().getName().equals(providerName)) {
                    provider.release(actionName);
                    break;
                }
            }
            if(map_providers.containsKey(providerName)){
                map_providers.remove(providerName);
            }
        }

    }
}

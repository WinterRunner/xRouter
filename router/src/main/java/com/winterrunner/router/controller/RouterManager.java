package com.winterrunner.router.controller;


import com.winterrunner.router.provider.Provider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by L.K.X on 2017/5/12.
 */

public class RouterManager {

    private HashMap<String, Provider> providers = new HashMap<>();


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
        Provider iProvider = providers.get(providerName);
        if (iProvider == null) {
            registerProvider(providerName);
        }
        return providers.get(providerName);
    }

    private synchronized void registerProvider(String providerName) {
        try {
            Class<?> aClass = Class.forName(providerName);
            if (providers.get(providerName) == null) {
                providers.put(providerName, (Provider) aClass.newInstance());
            }
        } catch (Exception e) {
            //不存在指定的provider
            e.printStackTrace();
        }
    }


    public synchronized void releaseAll() {
        Iterator iterator = providers.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            //Object key = entry.getKey();
            ((Provider) entry.getValue()).releaseAll();
        }
        providers.clear();
    }

    public synchronized void release(String providerName, String actionName) {
        if(providerName!=null){
            Iterator iterator = providers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();
                //Object key = entry.getKey();
                Provider provider = (Provider) entry.getValue();
                if (provider.getClass().getName().equals(providerName)) {
                    provider.release(actionName);
                    break;
                }
            }
            if(providers.containsKey(providerName)){
                providers.remove(providerName);
            }
        }

    }
}

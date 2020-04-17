package com.bkw.study.livedata;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用此事件总线：订阅者会接收到订阅之前发出的消息
 */
public class LiveDataBus {

    private Map<String, MutableLiveData<Object>> bus;

    private static LiveDataBus liveDataBus = new LiveDataBus();

    private LiveDataBus() {
        bus = new HashMap<>();
    }

    public static LiveDataBus getInstance() {
        return liveDataBus;
    }

    /**
     * 注册订阅者
     *
     * @param key  消息名
     * @param type 消息实体
     * @param <T>
     * @return
     */
    public synchronized <T> MutableLiveData<T> with(String key, Class<T> type) {
        if (!bus.containsKey(key)) {
            bus.put(key, new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>) bus.get(key);
    }

}

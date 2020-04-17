package com.bkw.study.livedata;


import java.util.HashMap;

/**
 * 使用此事件总线:订阅者不会接收订阅之前的事件
 */
public class LiveDataBusX {

    private static LiveDataBusX busX = new LiveDataBusX();

    private HashMap<String, UnPeekMutableLiveData<Object>> mapBus = new HashMap<>();

    public static LiveDataBusX getInstance() {
        return busX;
    }

    public synchronized <T> UnPeekMutableLiveData<T> with(String key, Class<T> tClass) {
        if (!mapBus.containsKey(key)) {
            mapBus.put(key, new UnPeekMutableLiveData<Object>());
        }
        return (UnPeekMutableLiveData<T>) mapBus.get(key);
    }

}

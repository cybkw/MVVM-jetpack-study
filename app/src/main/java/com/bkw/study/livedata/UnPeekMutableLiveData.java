package com.bkw.study.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 让订阅者不接收在订阅之前发送的消息
 * @param <T>
 */
public class UnPeekMutableLiveData<T> extends MutableLiveData {


    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer observer) {
        super.observe(owner, observer);
        hook(observer);
    }

    /**
     * hook
     * 让订阅者不接收在订阅之前发送的消息
     *
     * @param observer 观察者
     */
    private void hook(Observer<? super T> observer) {
        try {
            Class<LiveData> classLiveData = LiveData.class;
            //反射得到mObservers map对象
            Field fieldObservers = classLiveData.getDeclaredField("mObservers");
            fieldObservers.setAccessible(true);

            Object objectObservers = fieldObservers.get(this);
            Class<?> classObservers = objectObservers.getClass();

            //执行map的get方法取得 Map.Entry 存储的节点
            Method methodGet = classObservers.getDeclaredMethod("get", Object.class);
            methodGet.setAccessible(true);

            Object objectWrapperEntry = methodGet.invoke(objectObservers, observer);
            Object objectWrapper = null;

            if (objectWrapperEntry != null && objectWrapperEntry instanceof Map.Entry) {
                //得到obserWrapper
                objectWrapper = ((Map.Entry) objectWrapperEntry).getValue();
            }

            if (objectWrapper != null) {
                //得到（ObserverWrapper）ObjectWrapper的类对象，泛型编译擦除问题
                Class<?> classObserverWrapper = objectWrapper.getClass().getSuperclass();
                Field fieldLastVersion = null;

                if (classObserverWrapper != null) {
                    //得到mLastVersion变量
                    fieldLastVersion = classObserverWrapper.getDeclaredField("mLastVersion");
                    fieldLastVersion.setAccessible(true);

                    //得到mVersion变量
                    Field fieldVersion = classLiveData.getDeclaredField("mVersion");
                    fieldVersion.setAccessible(true);

                    Object objectVersion = fieldVersion.get(this);
                    //将mLastVersion的值与mVersion的值设为一致
                    fieldLastVersion.set(objectWrapper, objectVersion);
                }
            } else {
                throw new NullPointerException("objectWrapper is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package com.bkw.network.converter;


import androidx.lifecycle.LiveData;


import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * @author Administrator
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
    private static final String TAG = "LiveDataCallAdapterFact";

    public static LiveDataCallAdapterFactory create() {
        return new LiveDataCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalArgumentException("返回值需为参数化类型");
        }

        Class<?> rawType = getRawType(returnType);

        if (rawType != LiveData.class) {
            throw new IllegalArgumentException("返回值不是LiveData类型");
        }

        //先解释一下getParameterUpperBound
        //官方例子
        //For example, index 1 of {@code Map<String, ? extends Runnable>} returns {@code Runnable}.
        //获取的是Map<String,? extends Runnable>参数列表中index序列号的参数类型,即0为String,1为Runnable
        //这里的0就是LiveData<?>中?的序列号,因为只有一个参数
        //其实这个就是我们请求返回的实体

//        if (rawType != ApiResponse.class) {
//            throw new IllegalArgumentException("type must be ApiResponse");
//        }


//        if (!(observableType instanceof ParameterizedType)) {
//            throw new IllegalArgumentException("resource must be parameterized");
//        }
        Type type = getParameterUpperBound(0, (ParameterizedType) returnType);

        return new LiveDataCallAdapter<>(type);
    }


}

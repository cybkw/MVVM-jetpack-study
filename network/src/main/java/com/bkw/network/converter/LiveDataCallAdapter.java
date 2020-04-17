package com.bkw.network.converter;

import android.os.Build;
import android.util.Log;

import androidx.lifecycle.LiveData;


import com.bkw.network.ApiResponse;

import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Administrator
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<R>> {
    private static final String TAG = "LiveDataCallAdapter";

    private final Type responseType;

    public LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<R> adapt(Call<R> call) {

        return new LiveData<R>() {
            //flag作用是业务在多线程中,业务处理的线程安全问题,确保单一线程作业
            AtomicBoolean flag = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (flag.compareAndSet(false, true)) {
                    call.enqueue(new Callback<R>() {
                        @Override
                        public void onResponse(Call<R> call, Response<R> response) {
                            postValue(response.body());
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable t) {
                            Log.e(TAG, "onFailure: 请求失败");
                            postValue((R) t);
                        }
                    });
                }
            }
        };
    }


}

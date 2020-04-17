package com.bkw.study.net;

import com.bkw.network.ApiResponse;
import com.bkw.network.base.NetworkApi;
import com.bkw.network.errorhandler.ExceptionHandle;

import java.io.IOException;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 网络请求
 */
public class WANetApi extends NetworkApi {


    private static WANetApi instance;

    public static WANetApi getInstance() {
        if (null == instance) {
            synchronized (WANetApi.class) {
                if (null == instance) {
                    instance = new WANetApi();
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> tClass) {
        return getRetrofit(tClass).create(tClass);
    }



    @Override
    protected Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response;
            }
        };
    }

    @Override
    protected <T> Function<T, T> getAppErrorHandler() {
        return new Function<T, T>() {
            @Override
            public T apply(T response) throws Exception {
                //response中code码不会0 出现错误
                if (response instanceof ApiResponse && ((ApiResponse) response).errorCode != 0) {
                    ExceptionHandle.ServerException exception = new ExceptionHandle.ServerException();
                    exception.code = ((ApiResponse) response).errorCode;
                    exception.message = ((ApiResponse) response).errorMsg != null ? ((ApiResponse) response).errorMsg : "";
                    throw exception;
                }
                return response;
            }
        };
    }

    @Override
    public String getFormal() {
        return "https://www.wanandroid.com/";
    }

    @Override
    public String getTest() {
        return "https://www.wanandroid.com/";
    }
}

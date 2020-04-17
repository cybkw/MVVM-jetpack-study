package com.bkw.study;

import android.app.Application;

import com.bkw.network.base.INetworkRequiredInfo;
import com.bkw.network.base.NetworkApi;

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        NetworkApi.init(new INetworkRequiredInfo() {
            @Override
            public String getAppVersionName() {
                return BuildConfig.VERSION_NAME;
            }

            @Override
            public int getAppVersionCode() {
                return BuildConfig.VERSION_CODE;
            }

            @Override
            public boolean isDebug() {
                return BuildConfig.DEBUG;
            }

            @Override
            public Application getApplicationContext() {
                return BaseApplication.this;
            }
        });
    }
}

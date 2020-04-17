package com.bkw.study.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bkw.network.ApiResponse;
import com.bkw.study.data.home.BannerVO;
import com.bkw.study.net.HomeApi;
import com.bkw.study.net.WANetApi;

import java.util.List;

public class MainRepository {
    private static final String TAG = "MainRepository";

//    public LiveData<List<BannerVO>> getBanner(MutableLiveData<List<BannerVO>> banners) {
//
//      return   WANetApi.getInstance().create(HomeApi.class).banner();
//    }

    public LiveData<ApiResponse<List<BannerVO>>> getBanner(MutableLiveData<Boolean> isBanner) {
        Log.e(TAG, "getBanner: " + isBanner);
        return WANetApi.getInstance().create(HomeApi.class).banner();
    }
}

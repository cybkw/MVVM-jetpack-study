package com.bkw.study.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.bkw.network.ApiResponse;
import com.bkw.study.data.home.BannerVO;
import com.github.leonardoxh.livedatacalladapter.Resource;

import java.util.List;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Boolean> isBanner = new MutableLiveData<>();

    private MutableLiveData<List<BannerVO>> banners;
    private MainRepository repository;
    // TODO: Implement the ViewModel

    public MainViewModel() {
        banners = new MutableLiveData<>();
        repository = new MainRepository();
    }

//    public MutableLiveData<List<BannerVO>> getBanners() {
//        return banners;
//    }
//
//    public void requestBanner() {
//        repository.getBanner(requestBanner, banners);
//    }


    LiveData<ApiResponse<List<BannerVO>>> getBanner() {
        return Transformations.switchMap(isBanner, banners ->
                repository.getBanner(isBanner));
    }

//    MutableLiveData<String> nameQueryLiveData = ...
//
//               LiveData<List<String>> getUsersWithNameLiveData() {
//           return Transformations.switchMap(
//                       nameQueryLiveData,
//                    name -> myDataSource.getUsersWithNameLiveData(name));
//        }
//
//                void setNameQuery(String name) {
//            this.nameQueryLiveData.setValue(name);
//         }

    public void setRequestBanner(boolean request) {

        this.isBanner.setValue(request);
    }
}

package com.bkw.study.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bkw.network.ApiResponse;
import com.bkw.study.R;
import com.bkw.study.data.home.BannerVO;
import com.bkw.study.databinding.MainFragmentBinding;
import com.bkw.study.ui.base.BaseFragment;
import com.bumptech.glide.Glide;
import com.github.leonardoxh.livedatacalladapter.Resource;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class MainFragment extends BaseFragment {

    private MainViewModel mViewModel;

    private MainFragmentBinding binding;

    private static final String TAG = "MainFragment";
    MainViewModel viewModel;

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = getFragmentViewModelProvider(this).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        binding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel

        BGABanner.Adapter<ImageView, BannerVO> bannerVOAdapter = new BGABanner.Adapter<ImageView, BannerVO>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable BannerVO model, int position) {
                Glide.with(getContext())
                        .load(model.getImagePath())
                        .into(itemView);

            }
        };
        binding.banner.setAdapter(bannerVOAdapter);

        //请求数据
        viewModel.setRequestBanner(true);

        //数据监听
        viewModel.getBanner().observe(getViewLifecycleOwner(), new Observer<ApiResponse<List<BannerVO>>>() {
            @Override
            public void onChanged(ApiResponse<List<BannerVO>> response) {
                List<BannerVO> bannerVOS = response.data;
                binding.banner.setData(bannerVOS, null);
            }
        });
    }

    @Override
    public String getFragmentTag() {
        return "主页";
    }
}

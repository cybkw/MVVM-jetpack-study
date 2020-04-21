package com.bkw.study.ui.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bkw.base.loadsir.EmptyCallback;
import com.bkw.base.loadsir.ErrorCallback;
import com.bkw.base.loadsir.LoadingCallback;
import com.bkw.base.loadsir.ViewStatus;
import com.bkw.study.R;
import com.bkw.study.utils.ToastUtil;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

public abstract class BaseFragment<V extends ViewDataBinding, VM extends ViewModel> extends Fragment {
    private static final String TAG = "BaseFragment";


    LoadService mLoadService;
    protected V viewDataBinding;
    protected VM viewModel;
    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;

    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(activity);
        }
        return mActivityProvider;
    }

    protected ViewModelProvider getFragmentViewModelProvider(Fragment fragment) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(fragment);
        }
        return mFragmentProvider;
    }

    /**
     * @return 页面标签
     */
    public abstract String getFragmentTag();

    /**
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * Model或ViewModel初始化：子类按需实现
     *
     * @return
     */
    protected abstract VM getViewModel();

    /**
     * 页面初始化：相关数据容器适配器
     */
    public abstract void init();

    /**
     * 页面的监听事件方法：子类按需实现
     */
    public abstract void setupListener();

    /**
     * 重试按钮
     */
    protected abstract void onRetryBtnClick();

    public void setLoadSir(View view) {
        // You can change the callback on sub thread directly.
        mLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onRetryBtnClick();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + getFragmentTag());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: " + getFragmentTag());
        View root = inflater.inflate(getLayoutId(), container, false);
        viewDataBinding = DataBindingUtil.bind(root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: " + getFragmentTag());
        viewModel = getViewModel();
        init();
        setupListener();
    }

    public void loadingState(Object status) {
        if (mLoadService == null) {
            throw new NullPointerException("页面状态 mLoadService 没有初始化");
        }
        switch ((ViewStatus) status) {
            case LOADING:
                mLoadService.showCallback(LoadingCallback.class);
                break;
            case EMPTY:
                mLoadService.showCallback(EmptyCallback.class);
                break;
            case SHOW_CONTENT:
                mLoadService.showSuccess();
                break;
            case NO_MORE_DATA:
                ToastUtil.show("没有更多数据了");
                break;
            case REFRESH_ERROR:
//                if (((ObservableArrayList)viewModel.dataList.getValue()).size() == 0) {
//                    mLoadService.showCallback(ErrorCallback.class);
//                } else {
////                    ToastUtil.show(viewModel.errorMessage.getValue().toString());
//                }
                mLoadService.showCallback(ErrorCallback.class);
                break;
            case LOAD_MORE_FAILED:
                ToastUtil.show("加载错误");
                break;
            default:
                break;
        }
    }
}

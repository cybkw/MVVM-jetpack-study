package com.bkw.study.ui.base;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bkw.base.loadsir.EmptyCallback;
import com.bkw.base.loadsir.ErrorCallback;
import com.bkw.base.loadsir.LoadingCallback;
import com.bkw.base.loadsir.ViewStatus;
import com.bkw.study.R;
import com.bkw.study.utils.ToastUtil;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

public abstract class BaseFragment2 extends Fragment {
    private static final String TAG = "BaseFragment2";
    LoadService mLoadService;

    /**
     * @return 页面标签
     */
    public abstract String getFragmentTag();

    /**
     * @return 布局id
     */
    protected abstract int getLayoutId();

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

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: " + getFragmentTag());
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

package com.bkw.study.ui.my;

import android.os.Bundle;

import com.bkw.study.ui.base.BaseFragment;

public class MineFragment extends BaseFragment {
    @Override
    public String getFragmentTag() {
        return "我的";
    }

    public static MineFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

package com.bkw.study.ui.official;

import android.os.Bundle;

import com.bkw.study.ui.base.BaseFragment;

public class OffcialFragment extends BaseFragment {
    @Override
    public String getFragmentTag() {
        return "公众号";
    }

    public static OffcialFragment newInstance() {
        
        Bundle args = new Bundle();
        
        OffcialFragment fragment = new OffcialFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

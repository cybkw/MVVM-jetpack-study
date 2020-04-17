package com.bkw.study.ui.project;

import android.os.Bundle;

import com.bkw.study.ui.base.BaseFragment;

public class ProjectFragment extends BaseFragment {
    @Override
    public String getFragmentTag() {
        return "Project";
    }

    public static ProjectFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

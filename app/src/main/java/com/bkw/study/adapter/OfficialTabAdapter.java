package com.bkw.study.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bkw.study.data.official.OfficalVO;
import com.bkw.study.ui.official.OffcialChildFragment;

import java.util.List;

public class OfficialTabAdapter2 extends FragmentStateAdapter {
    private List<OfficalVO> data;

    public OfficialTabAdapter2(List<OfficalVO> data, Fragment fragment) {
        this(fragment);
        this.data = data;
    }

    public OfficialTabAdapter2(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return OffcialChildFragment.newInstance(data.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

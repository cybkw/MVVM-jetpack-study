package com.bkw.study.ui.official;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bkw.base.loadsir.ViewStatus;
import com.bkw.network.ApiResponse;
import com.bkw.study.R;
import com.bkw.study.adapter.ArticleItemAdapter;
import com.bkw.study.data.Cons;
import com.bkw.study.data.home.ArticleVO;
import com.bkw.study.data.home.PageVO;
import com.bkw.study.ui.base.BaseFragment;

public class OffcialChildFragment2 extends Fragment {
    private static final String TAG = "OffcialChildFragment2";
    private int mId;
    private ArticleItemAdapter itemAdapter;
    private View rootView;
    private OffcialChildViewModel viewModel;

    public static OffcialChildFragment2 newInstance(int id) {

        Bundle args = new Bundle();
        args.putInt(Cons.ID, id);
        OffcialChildFragment2 fragment = new OffcialChildFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            mId = arguments.getInt(Cons.ID);
        }
        viewModel = new ViewModelProvider(this).get(OffcialChildViewModel.class);
        
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_list, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        itemAdapter = new ArticleItemAdapter(R.layout.item_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(itemAdapter);

        
        viewModel.initList();
        viewModel.getWXArticles(mId).observe(getViewLifecycleOwner(), new Observer<ApiResponse<PageVO<ArticleVO>>>() {
            @Override
            public void onChanged(ApiResponse<PageVO<ArticleVO>> pageVOApiResponse) {
                Log.e(TAG, "onChanged: " + pageVOApiResponse.data.getCurPage());
                if (pageVOApiResponse.errorCode == 0) {
                    if (pageVOApiResponse.data.getCurPage() == 1) {
                        itemAdapter.setList(pageVOApiResponse.data.getDatas());
                    } else {
                        itemAdapter.addData(pageVOApiResponse.data.getDatas());
                    }
                } else {
                }
            }
        });
    }
}

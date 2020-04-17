package com.bkw.study;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.bkw.study.databinding.MainActivityBinding;
import com.bkw.study.ui.base.BaseActivity;
import com.bkw.study.ui.main.MainFragment;
import com.bkw.study.ui.my.MineFragment;
import com.bkw.study.ui.official.OffcialFragment;
import com.bkw.study.ui.project.ProjectFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {

    MainActivityBinding binding;
    private ViewModelProvider mActivityProvider;

    private Fragment mCurrFragment = new Fragment();

    private final MainFragment mainFragment = MainFragment.newInstance();
    private final ProjectFragment projectFragment = ProjectFragment.newInstance();
    private final OffcialFragment offcialFragment = OffcialFragment.newInstance();
    private final MineFragment mineFragment = MineFragment.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);

        setupBottomNavigation();
        //默认显示首页
        switchFragment(mainFragment);
    }

    private void setupBottomNavigation() {

        binding.bottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.main:
                        switchFragment(mainFragment);
                        break;
                    case R.id.official:
                        switchFragment(offcialFragment);
                        break;
                    case R.id.project:
                        switchFragment(projectFragment);
                        break;
                    case R.id.mine:
                        switchFragment(mineFragment);
                        break;
                    default:
                        break;

                }
                return false;
            }
        });
    }

    /**
     * 切换Fragment
     *
     * @param targetFragment
     */
    private void switchFragment(Fragment targetFragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //如果Fragment还没被添加，就隐藏当前页面并添加到显示。
            transaction.hide(mCurrFragment)
                    .add(R.id.container, targetFragment, targetFragment.getClass().getSimpleName())
                    .commit();
        } else {
            //如果已经添加过
            transaction.hide(mCurrFragment) //隐藏当前页面
                    .show(targetFragment) //显示目标页面
                    .commit();
        }
        //更新当前页面
        mCurrFragment = targetFragment;
    }


    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(activity);
        }
        return mActivityProvider;
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            showToast("再按一次退出程序");
        }
    }
}

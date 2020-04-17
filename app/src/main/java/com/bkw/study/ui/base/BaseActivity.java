package com.bkw.study.ui.base;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bkw.study.utils.BarUtils;
import com.bkw.study.utils.DisplayUtils;
import com.bkw.study.utils.ToastUtil;

public class BaseActivity extends AppCompatActivity {

    /**
     * 再点一次退出程序时间设置
     */
    protected static final long WAIT_TIME = 2000L;
    protected long TOUCH_TIME = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置透明状态栏
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        //设置沉浸式,第二个参数是状态栏字体是否为黑色。
        BarUtils.setStatusBarLightMode(this, true);

        DisplayUtils.setCustomDensity(this, getApplication());
    }

    protected void showToast(String msg) {
        ToastUtil.show(msg);
    }


}

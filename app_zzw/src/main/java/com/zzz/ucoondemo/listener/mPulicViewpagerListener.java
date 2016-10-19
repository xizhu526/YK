package com.zzz.ucoondemo.listener;

import android.content.Context;
import android.support.v4.view.ViewPager;

import com.zzz.ucoondemo.view.ViewPageIndicator;

/**
 * “我发布的”的viewpager的监听
 * Created by 请叫我张懂 on 2016/9/27.
 */

public class mPulicViewpagerListener implements ViewPager.OnPageChangeListener {
    private ViewPageIndicator mViewPageINdicator;
    private Context mContext;

    public mPulicViewpagerListener(Context context, ViewPageIndicator mViewPageINdicator) {
        this.mViewPageINdicator = mViewPageINdicator;
        this.mContext = context;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mViewPageINdicator.scroll(position,positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        mViewPageINdicator.setHighLight(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

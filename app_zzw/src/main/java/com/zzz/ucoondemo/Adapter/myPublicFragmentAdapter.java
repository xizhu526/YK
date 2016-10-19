package com.zzz.ucoondemo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zzz.ucoondemo.Fragment.ViewPageFragment;

import java.util.ArrayList;

/**
 * ViewPager中Fragment的数据适配器
 * Created by 请叫我张懂 on 2016/9/27.
 */

public class myPublicFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<ViewPageFragment> mContent = new ArrayList<ViewPageFragment>();

    public myPublicFragmentAdapter(FragmentManager fm, ArrayList<ViewPageFragment> mContent) {
        super(fm);
        this.mContent = mContent;
    }

    @Override
    public Fragment getItem(int position) {
        return mContent.get(position);
    }

    @Override
    public int getCount() {
        return mContent.size();
    }
}

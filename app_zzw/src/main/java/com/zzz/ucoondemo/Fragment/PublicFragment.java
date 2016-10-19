package com.zzz.ucoondemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzz.ucoondemo.Adapter.myPublicFragmentAdapter;
import com.zzz.ucoondemo.Model.PublicInfo;
import com.zzz.ucoondemo.R;
import com.zzz.ucoondemo.view.ViewPageIndicator;

import java.util.ArrayList;

/**
 * Created by 请叫我张懂 on 2016/9/28.
 */

public class PublicFragment extends Fragment {
    private ViewPager mViewPager;
    private ViewPageIndicator mViewPageIndicator;
    private ArrayList<String> IndicatorTitles;
    private ArrayList<ViewPageFragment> mViewPagerContents;
    private myPublicFragmentAdapter mAdapter;
    private FragmentManager fm;

    public PublicFragment(FragmentManager fm) {
        this.fm = fm;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypubliced, container, false);
        intiView(view);
        initData();
        return view;
    }

    private void intiView(View view) {
        mViewPageIndicator = (ViewPageIndicator) view.findViewById(R.id.id_viewPageIndicator);
        mViewPager = (ViewPager) view.findViewById(R.id.id_viewPager);
    }

    private void initData() {
        ArrayList<PublicInfo> alPublicInfos = new ArrayList<PublicInfo>();
        alPublicInfos = createData();
        mViewPagerContents = new ArrayList<ViewPageFragment>();
        IndicatorTitles = new ArrayList<String>();
        IndicatorTitles.add(" 全部 ");
        IndicatorTitles.add("已上架");
        IndicatorTitles.add("已下架");
        IndicatorTitles.add("待支付");
        mViewPageIndicator.setItemByTitles(IndicatorTitles);
        mViewPageIndicator.setViewPageChangeListener(mViewPager);
        for (String title : IndicatorTitles) {
            if (title != null) {
                ViewPageFragment fragment = ViewPageFragment.newMyLoadFragment(title, alPublicInfos);
                mViewPagerContents.add(fragment);
            }
        }
        mAdapter = new myPublicFragmentAdapter(fm, mViewPagerContents);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewPageIndicator.removeViewPageChangeListener();
    }

    /**
     * 生成测试数据
     *
     * @return
     */
    private ArrayList<PublicInfo> createData() {
        ArrayList<PublicInfo> al = new ArrayList<PublicInfo>();
        for (int i = 0; i < 3; i++) {
            PublicInfo mPublicInfoInfo = new PublicInfo();
            mPublicInfoInfo.setContent("求代课" + i);
            mPublicInfoInfo.setLooked("200");
            mPublicInfoInfo.setState("已上架");
            mPublicInfoInfo.setPublicDate("4月2" + i + "日");
            al.add(mPublicInfoInfo);
        }
        for (int i = 3; i < 6; i++) {
            PublicInfo mPublicInfoInfo = new PublicInfo();
            mPublicInfoInfo.setContent("求代课" + i);
            mPublicInfoInfo.setLooked("200");
            mPublicInfoInfo.setState("已下架");
            mPublicInfoInfo.setPublicDate("4月2" + i + "日");
            al.add(mPublicInfoInfo);
        }
        for (int i = 6; i < 9; i++) {
            PublicInfo mPublicInfoInfo = new PublicInfo();
            mPublicInfoInfo.setContent("求代课" + i);
            mPublicInfoInfo.setLooked("200");
            mPublicInfoInfo.setState("待支付");
            mPublicInfoInfo.setPublicDate("4月2" + i + "日");
            al.add(mPublicInfoInfo);
        }
        return al;
    }
}

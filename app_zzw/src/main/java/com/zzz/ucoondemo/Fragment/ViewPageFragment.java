package com.zzz.ucoondemo.Fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zzz.ucoondemo.Adapter.myPublicRecycleAdapter;
import com.zzz.ucoondemo.Model.PublicInfo;
import com.zzz.ucoondemo.R;
import com.zzz.ucoondemo.listener.MyPublicItemListener;

import java.util.ArrayList;

/**
 * Created by 请叫我张懂 on 2016/9/26.
 */

public class ViewPageFragment extends Fragment {
    private ArrayList<PublicInfo> alPublicInfos;
    private final static String BUNDLE_TITLE = "title";
    private String mTitle;
    private RecyclerView mRecycleView;
    private myPublicRecycleAdapter mAdapter;
    private MyPublicItemListener mPublicItemListener;

    public ViewPageFragment(ArrayList<PublicInfo> alPublicInfos) {
        this.alPublicInfos = alPublicInfos;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            mTitle = bundle.getString(BUNDLE_TITLE);
        }
        View view = inflater.inflate(R.layout.mypublic_recycleview, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecycleView = (RecyclerView) view.findViewById(R.id.idRVPublic);
    }

    private void initData() {
        Log.i("zzz", "alPublicInfos.size(): " + alPublicInfos.size());
        mAdapter = new myPublicRecycleAdapter(alPublicInfos, getContext(), mTitle);
        mPublicItemListener = new MyPublicItemListener(getContext());
        mAdapter.setmPublicItemListener(mPublicItemListener);
        mRecycleView.setAdapter(mAdapter);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(lm);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 用来生成Fragment实例
     *
     * @param title
     * @return
     */
    public static ViewPageFragment newMyLoadFragment(String title, ArrayList<PublicInfo> alPublicInfos) {
        Bundle bundle = new Bundle();
        ViewPageFragment myLoadFragment = new ViewPageFragment(alPublicInfos);
        bundle.putString(BUNDLE_TITLE, title);
        myLoadFragment.setArguments(bundle);
        return myLoadFragment;
    }


}

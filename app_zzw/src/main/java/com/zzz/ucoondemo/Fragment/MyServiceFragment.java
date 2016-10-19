package com.zzz.ucoondemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzz.ucoondemo.Adapter.myServiceRecycleAdapter;
import com.zzz.ucoondemo.Interface.ServiceItemListener;
import com.zzz.ucoondemo.Model.ServiceInfo;
import com.zzz.ucoondemo.R;
import com.zzz.ucoondemo.listener.MyServiceItemListener;

import java.util.ArrayList;

/**
 * Created by 请叫我张懂 on 2016/9/28.
 */

public class MyServiceFragment extends Fragment {
    private RecyclerView mRecycleView;
    private ArrayList<ServiceInfo> alServiceInfos;
    private myServiceRecycleAdapter mAdapter;
    private MyServiceItemListener mServiceItemListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myservice_recycleview, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRecycleView = (RecyclerView) view.findViewById(R.id.idRVService);
    }

    private void initData() {
        alServiceInfos = new ArrayList<ServiceInfo>();
        for (int i = 0; i < 10; i++) {
            ServiceInfo mserviceInfo = new ServiceInfo();
            mserviceInfo.setName("张懂" + i);
            mserviceInfo.setContent("求代课" + i);
            mserviceInfo.setMoney("50");
            mserviceInfo.setState("正在服务");
            mserviceInfo.setPublicDate("4月2" + i + "日");
            alServiceInfos.add(mserviceInfo);
        }
        mAdapter = new myServiceRecycleAdapter(alServiceInfos, getContext());
        mServiceItemListener = new MyServiceItemListener(getContext());
        mAdapter.setmServiceItemListener(mServiceItemListener);
        mRecycleView.setAdapter(mAdapter);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(lm);
        mRecycleView.setItemAnimator(new DefaultItemAnimator());
    }
}

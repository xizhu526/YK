package com.zzz.ucoondemo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.zzz.ucoondemo.Fragment.ConnectTaFragment;
import com.zzz.ucoondemo.Fragment.ToolbarFragment;
import com.zzz.ucoondemo.Model.ServiceInfo;
import com.zzz.ucoondemo.R;

public class ChatActivity extends AppCompatActivity {
    private Intent mIntent;
    private ServiceInfo serviceInfo;
    private FragmentManager mFragmentManager;
    private ToolbarFragment mToolbarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_contain);
        initData();
    }

    private void initData() {
        mIntent = this.getIntent();
        serviceInfo = (ServiceInfo) mIntent.getSerializableExtra("serviceInfo");
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mToolbarFragment = ToolbarFragment.newInstantFragment(serviceInfo.getName());
        ConnectTaFragment connectTaFragment = new ConnectTaFragment(serviceInfo, this);
        mFragmentTransaction.replace(R.id.idFLContent, connectTaFragment);
        mFragmentTransaction.replace(R.id.idFLToolBarTitle, mToolbarFragment);
        mFragmentTransaction.commit();
    }
}

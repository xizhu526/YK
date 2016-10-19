package com.zzz.ucoondemo.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zzz.ucoondemo.Fragment.MyInfoFragment;
import com.zzz.ucoondemo.Fragment.MyServiceFragment;
import com.zzz.ucoondemo.Fragment.PublicFragment;
import com.zzz.ucoondemo.Fragment.ToolbarFragment;
import com.zzz.ucoondemo.R;

public class FragmentContainActivity extends AppCompatActivity {
    private Intent mIntent;
    private String titleType;
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
        titleType = mIntent.getStringExtra(MenuActivity.TOOLBARTITLE);
        setBundleForTitleFragment(titleType);
    }

    private void setBundleForTitleFragment(String titleType) {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        switch (titleType) {
            case MenuActivity.MYINFO:
                mToolbarFragment = ToolbarFragment.newInstantFragment("个人详情");
                MyInfoFragment myInfoFragment = new MyInfoFragment();
                mFragmentTransaction.replace(R.id.idFLContent, myInfoFragment);
                break;
            case MenuActivity.MYPUBLIC:
                mToolbarFragment = ToolbarFragment.newInstantFragment("我发布的");
                PublicFragment mPublicFragment = new PublicFragment(getSupportFragmentManager());
                mFragmentTransaction.replace(R.id.idFLContent, mPublicFragment);
                break;
            case MenuActivity.MYSERVICE:
                mToolbarFragment = ToolbarFragment.newInstantFragment("我服务的");
                MyServiceFragment myServiceFragment = new MyServiceFragment();
                mFragmentTransaction.replace(R.id.idFLContent, myServiceFragment);
                break;

        }
        mFragmentTransaction.replace(R.id.idFLToolBarTitle, mToolbarFragment);
        mFragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}

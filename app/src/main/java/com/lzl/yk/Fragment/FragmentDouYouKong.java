package com.lzl.yk.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lzl.yk.R;

public class FragmentDouYouKong extends Fragment   {

	//搜索框
	private EditText mEtSearch = null;// 输入搜索内容
	private Button mBtnClearSearchText = null;// 清空搜索信息的按钮
	private LinearLayout mLayoutClearSearchText = null;

	private FragmentWoYouKong_Sreach mSreach;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment_douyoukong, container, false);
			SetSearchFragment();
		return view;
	}
	private void SetSearchFragment(){
		FragmentManager fm=getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		mSreach = new FragmentWoYouKong_Sreach();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("myHorizontalScrollView",myHorizontalScrollView);
//        mSreach.setArguments(bundle);
		transaction.replace(R.id.fragment_douyoukong_serach,mSreach);
		transaction.commit();

	}

}

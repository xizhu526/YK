package com.lzl.yk.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzl.yk.R;

public class FragmentShuiYouKong extends Fragment   {
	FragmentDouYouKong_toolbar mtoolbar;
	FragmentShuiYouKong_content mcontent;
	FragmentShuiYouKong_list_content mlistcontent;
	FragmentShuiYouKong_biaoti mbiaoti;

	@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_shuiyoukong, container, false);
		SettoolbarFragment();
	return view;
}
	private void SettoolbarFragment(){
		FragmentManager fm=getFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		mtoolbar = new FragmentDouYouKong_toolbar();
		mbiaoti = new FragmentShuiYouKong_biaoti();
		mcontent = new FragmentShuiYouKong_content();
		mlistcontent = new FragmentShuiYouKong_list_content();
		transaction.replace(R.id.fragment_shuiyoukong_toolbar,mtoolbar);
		transaction.replace(R.id.fragment_shuiyoukong_biaoti,mbiaoti);
		transaction.replace(R.id.fragment_shuiyoukong_content,mcontent);
		transaction.replace(R.id.fragment_shuiyoukong_list_content,mlistcontent);
		transaction.commit();
	}
}

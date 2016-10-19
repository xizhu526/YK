package com.lzl.yk.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lzl.yk.Activity.MainActivity;
import com.lzl.yk.R;


public class FragmentWoYouKong extends Fragment {

   // MyHorizontalScrollView myHorizontalScrollView;//用于从actiyity中获取到MyHorizontalScrollView的实例用于控制菜单
    //搜索框
    private EditText mEtSearch = null;// 输入搜索内容
    private Button mBtnClearSearchText = null;// 清空搜索信息的按钮
    private LinearLayout mLayoutClearSearchText = null;

    private FragmentWoYouKong_Sreach mSreach;
    private FragamentWoYouKong_guide mGuide;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_woyoukong, container, false);
//            Bundle bundle = getArguments();
//            myHorizontalScrollView = (MyHorizontalScrollView)bundle.getSerializable("myHorizontalScrollView");
            SetFragment();
            ((MainActivity)this.getActivity()).registerMyTouchListener(mTouchListener);
        return view;
	}
    private MainActivity.MyTouchListener mTouchListener = new MainActivity.MyTouchListener() {
        @Override
        public void onTouchEvent(MotionEvent event) {

            int action=event.getAction();

            switch (action) {
                case MotionEvent.ACTION_UP:
                    Log.d("WoYouKong.ACTION_UP", "1");
                    break;
                case  MotionEvent.ACTION_DOWN:
                    Log.d("WoYouKong.ACTION_DOWN", "2");
                    break;
                case  MotionEvent.ACTION_MOVE:
                    Log.d("WoYouKong.ACTION_MOVE", "3");
                    break;
            }

        }
    };

    private void SetFragment(){
        FragmentManager fm=getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mSreach = new FragmentWoYouKong_Sreach();
        mGuide = new FragamentWoYouKong_guide();
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("myHorizontalScrollView",myHorizontalScrollView);
//        mSreach.setArguments(bundle);
        transaction.replace(R.id.fragment_woyoukong_serach,mSreach);
        transaction.replace(R.id.fragment_woyoukong_guide,mGuide);
        transaction.commit();

    }


}

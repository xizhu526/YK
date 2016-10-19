package com.lzl.yk.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzl.yk.View.MyHorizontalScrollView;
import com.lzl.yk.R;

/**
 * Created by 西猪 on 2016/9/29.
 */

public class FragmentWoYouKong_Sreach extends Fragment  {

    //搜索框
    private EditText mEtSearch = null;// 输入搜索内容
    private Button mBtnClearSearchText = null;// 清空搜索信息的按钮
    private LinearLayout mLayoutClearSearchText = null;
    private ImageButton toggle_menu = null; //用于控制菜单
    MyHorizontalScrollView myHorizontalScrollView;//用于从actiyity中获取到MyHorizontalScrollView的实例用于控制菜单
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_woyoukong_sreach, container, false);
        Sreach(view);
        Bundle bundle = getArguments();
        //myHorizontalScrollView = (MyHorizontalScrollView)bundle.getSerializable("myHorizontalScrollView");

        return view;
    }
    private void Sreach(View view){
        mEtSearch = (EditText) view.findViewById(R.id.et_search);
        mBtnClearSearchText = (Button) view.findViewById(R.id.btn_clear_search_text);
        mLayoutClearSearchText = (LinearLayout) view.findViewById(R.id.layout_clear_search_text);
        toggle_menu =(ImageButton) view.findViewById(R.id.toggle_menu);
        myHorizontalScrollView= (MyHorizontalScrollView)getActivity().findViewById(R.id.MyHorizontalScrollView);
        toggle_menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myHorizontalScrollView.toggle();
            }
        });

        mEtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int textLength = mEtSearch.getText().length();
                if (textLength > 0) {
                    mLayoutClearSearchText.setVisibility(View.VISIBLE);
                } else {
                    mLayoutClearSearchText.setVisibility(View.GONE);
                }
            }
        });

        mBtnClearSearchText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtSearch.setText("");
                mLayoutClearSearchText.setVisibility(View.GONE);
            }
        });

        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    // 先隐藏键盘
                    ((InputMethodManager) mEtSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);

                    return true;
                }
                return false;
            }
        });
    }
}

package com.lzl.yk.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.lzl.yk.View.MyHorizontalScrollView;
import com.lzl.yk.R;

/**
 * Created by 西猪 on 2016/10/7.
 */
public class FragmentDouYouKong_toolbar extends Fragment {

    ImageButton SYK_toggle_menu;
    MyHorizontalScrollView myHorizontalScrollView;//用于从actiyity中获取到MyHorizontalScrollView的实例用于控制菜单
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shuiyoukong_toolbar, container, false);
        initview(view);
        return view;
    }
    private  void  initview(View view){
        myHorizontalScrollView= (MyHorizontalScrollView)getActivity().findViewById(R.id.MyHorizontalScrollView);
        SYK_toggle_menu = (ImageButton)view.findViewById(R.id.SYK_toggle_menu);
        SYK_toggle_menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myHorizontalScrollView.toggle();
            }
        });
    }

}

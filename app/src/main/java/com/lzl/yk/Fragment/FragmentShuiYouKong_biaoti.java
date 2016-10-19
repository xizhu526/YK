package com.lzl.yk.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzl.yk.R;

/**
 * Created by xizu on 2016/10/18.
 */

public class FragmentShuiYouKong_biaoti extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shuiyoukong_biaoti,container,false);
        return view;
    }
}

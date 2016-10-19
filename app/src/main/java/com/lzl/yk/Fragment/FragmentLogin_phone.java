package com.lzl.yk.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.common.collect.Lists;
import com.lzl.yk.R;
import com.lzl.yk.View.DragGridView;

import java.util.List;

/**
 * Created by 西猪 on 2016/10/14.
 */

public class FragmentLogin_phone extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_phone,container,false);
        return view;
    }
}

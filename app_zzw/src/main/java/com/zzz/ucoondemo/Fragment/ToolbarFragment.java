package com.zzz.ucoondemo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zzz.ucoondemo.Activity.MenuActivity;
import com.zzz.ucoondemo.R;
/**
 * Created by 请叫我张懂 on 2016/9/28.
 */

public class ToolbarFragment extends Fragment {
    private TextView tvToolbarTitle;
    private ImageButton ibToolbarBack;
    private String title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle mBundle = getArguments();
        title = mBundle.getString(MenuActivity.TOOLBARTITLE);
        View view = inflater.inflate(R.layout.toolbar, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvToolbarTitle = (TextView) view.findViewById(R.id.idTvToolbarTitle);
        if (title != null) {
            tvToolbarTitle.setText(title);
        }
        ibToolbarBack = (ImageButton) view.findViewById(R.id.idIBToolbarBack);
        ibToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction( "com.lzl.yk.MainActivity");//设置intent的Action属性值
                intent.addCategory(Intent.CATEGORY_DEFAULT);//不加这行也行，因为这个值默认就是Intent.CATEGORY_DEFAULT
                startActivity(intent);
            }
        });
    }

    public static ToolbarFragment newInstantFragment(String ToolbarTitle) {
        String title = ToolbarTitle;
        Bundle bundle = new Bundle();
        ToolbarFragment myToolbarFragment = new ToolbarFragment();
        bundle.putString(MenuActivity.TOOLBARTITLE, ToolbarTitle);
        myToolbarFragment.setArguments(bundle);
        return myToolbarFragment;
    }
}

package com.zzz.ucoondemo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzz.ucoondemo.Model.ManInfo;
import com.zzz.ucoondemo.R;

/**
 * 个人信息界面
 * Created by 请叫我张懂 on 2016/9/28.
 */

public class MyInfoFragment extends Fragment {
    private TextView tvName, tvSize, tvPhoneNum, tvWecharNum;
    private ImageView ivIcon;//头像
    public static ManInfo manInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myinfo, container, false);
        initView(view);
        setInfo();
        return view;
    }

    private void initView(View view) {
        ivIcon = (ImageView) view.findViewById(R.id.idCIIcon);
        tvName = (TextView) view.findViewById(R.id.idTVName);
        tvSize = (TextView) view.findViewById(R.id.idTVSize);
        tvPhoneNum = (TextView) view.findViewById(R.id.idTVPhoneNumber);
        tvWecharNum = (TextView) view.findViewById(R.id.idTVWechatNumber);
    }

    private static void initData() {
        manInfo = new ManInfo();
        manInfo.setName("我");
        manInfo.setSize("这个年纪的我们");
        manInfo.setPhoneNumber("123580");
        manInfo.setWeCharNumber("wx123456");
        //缺少设置头像
    }

    private void setInfo() {
        initData();
        tvName.setText(manInfo.getName());
        tvSize.setText(manInfo.getSize());
        tvPhoneNum.setText(manInfo.getPhoneNumber());
        tvWecharNum.setText(manInfo.getWeCharNumber());
    }

}

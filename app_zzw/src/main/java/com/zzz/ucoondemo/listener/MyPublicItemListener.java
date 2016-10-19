package com.zzz.ucoondemo.listener;

import android.content.Context;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.zzz.ucoondemo.Interface.PublicItemListener;
import com.zzz.ucoondemo.view.DialogDown;

/**
 * 我发布的 各个item的监听器
 * Created by 请叫我张懂 on 2016/10/4.
 */

public class MyPublicItemListener implements PublicItemListener {
    private Context mContext;

    public MyPublicItemListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void ItemDowmClick() {
        DialogDown dd = new DialogDown(mContext);
        dd.show();
    }

    @Override
    public void ItemDeleteClick() {

    }

    @Override
    public void ItemLookMore() {

    }

    @Override
    public void ItemPayClick() {

    }
}

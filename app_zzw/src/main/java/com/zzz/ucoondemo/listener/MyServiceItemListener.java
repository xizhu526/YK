package com.zzz.ucoondemo.listener;

import android.content.Context;
import android.content.Intent;
import com.zzz.ucoondemo.Activity.ChatActivity;
import com.zzz.ucoondemo.Interface.ServiceItemListener;
import com.zzz.ucoondemo.Model.ServiceInfo;

/**
 * 我服务的 各个item的监听器
 * Created by 请叫我张懂 on 2016/10/4.
 */
public class MyServiceItemListener implements ServiceItemListener {
    private Context mContext;

    public MyServiceItemListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void ItemConnectTaClick(ServiceInfo serviceInfo) {
        Intent intent = new Intent();
        intent.putExtra("serviceInfo", serviceInfo);
        intent.setClass(mContext, ChatActivity.class);
        mContext.startActivity(intent);
    }

    @Override
    public void ItemLookMoreClick(ServiceInfo serviceInfo) {


    }
}

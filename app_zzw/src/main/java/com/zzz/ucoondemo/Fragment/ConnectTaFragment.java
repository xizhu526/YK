package com.zzz.ucoondemo.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zzz.ucoondemo.Adapter.MyListViewChatMsgAdapter;
import com.zzz.ucoondemo.Model.ChatMsgInfo;
import com.zzz.ucoondemo.Model.ServiceInfo;
import com.zzz.ucoondemo.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 请叫我张懂 on 2016/10/5.
 */

public class ConnectTaFragment extends Fragment {
    private ServiceInfo serviceInfo;
    private LayoutInflater mInflater;
    private MyListViewChatMsgAdapter mAdapter;
    private List<ChatMsgInfo> chatMsgInfos;//存放聊天记录的List
    private Context mContext;
    private ListView lvChatMsgs;

    public ConnectTaFragment(ServiceInfo serviceInfo, Context context) {
        this.serviceInfo = serviceInfo;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = mInflater.inflate(R.layout.avactivity_faragment_chat, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        lvChatMsgs = (ListView) view.findViewById(R.id.idLVChatMSg);
    }

    private void initData() {
        chatMsgInfos = new ArrayList<ChatMsgInfo>();
        ChatMsgInfo chatMsgInfo = new ChatMsgInfo();
        chatMsgInfo.setDate(new Date());
        chatMsgInfo.setMsg("你好！为你服务");
        chatMsgInfo.setType(ChatMsgInfo.Type.INCOMING);
        chatMsgInfo.setName(serviceInfo.getName());
        chatMsgInfos.add(chatMsgInfo);
        ChatMsgInfo chatMsgInfo2 = new ChatMsgInfo();
        chatMsgInfo2.setDate(new Date());
        chatMsgInfo2.setMsg("你好你好");
        chatMsgInfo2.setType(ChatMsgInfo.Type.OUTCOMING);
        chatMsgInfo2.setName("我");
        chatMsgInfos.add(chatMsgInfo2);
        mAdapter = new MyListViewChatMsgAdapter(chatMsgInfos, getContext());
        lvChatMsgs.setAdapter(mAdapter);
    }
}

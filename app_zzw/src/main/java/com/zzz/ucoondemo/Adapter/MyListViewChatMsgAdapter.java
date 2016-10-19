package com.zzz.ucoondemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzz.ucoondemo.Model.ChatMsgInfo;
import com.zzz.ucoondemo.R;
import com.zzz.ucoondemo.view.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by 请叫我张懂 on 2016/10/5.
 */

public class MyListViewChatMsgAdapter extends BaseAdapter {
    private List<ChatMsgInfo> chatMsgInfos;
    private Context mContext;
    private static final int INCOMING_FLAG = 0;
    private static final int OUTCOMING_FLAG = 1;
    private LayoutInflater mInflater = null;

    public MyListViewChatMsgAdapter(List<ChatMsgInfo> chatMsgInfos, Context mContext) {
        this.chatMsgInfos = chatMsgInfos;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return chatMsgInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return chatMsgInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMsgInfo chatMsgInfo = chatMsgInfos.get(position);
        if (chatMsgInfo.getType() == ChatMsgInfo.Type.INCOMING) {
            return INCOMING_FLAG;
        }
        return OUTCOMING_FLAG;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMsgInfo chatMsgInfo = chatMsgInfos.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            if (getItemViewType(position) == INCOMING_FLAG) {
                convertView = mInflater.inflate(R.layout.item_from_msg, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.tvData = (TextView) convertView.findViewById(R.id.idTVFromChatDate);
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.idTVFromName);
                viewHolder.tvChatMsg = (TextView) convertView.findViewById(R.id.idTVFromChatMsg);
                viewHolder.circleImageView = (CircleImageView) convertView.findViewById(R.id.idCIFromIcon);
            } else {
                convertView = mInflater.inflate(R.layout.item_to_msg, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.tvData = (TextView) convertView.findViewById(R.id.idTVToChatDate);
                viewHolder.tvName = (TextView) convertView.findViewById(R.id.idTVToName);
                viewHolder.tvChatMsg = (TextView) convertView.findViewById(R.id.idTVToChatMsg);
                viewHolder.circleImageView = (CircleImageView) convertView.findViewById(R.id.idCIToIcon);
            }
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        viewHolder.tvData.setText(df.format(chatMsgInfo.getDate()));
        viewHolder.tvName.setText(chatMsgInfo.getName());
        viewHolder.tvChatMsg.setText(chatMsgInfo.getMsg());
        return convertView;
    }

    private class ViewHolder {
        CircleImageView circleImageView;
        TextView tvData;
        TextView tvChatMsg;
        TextView tvName;
    }
}



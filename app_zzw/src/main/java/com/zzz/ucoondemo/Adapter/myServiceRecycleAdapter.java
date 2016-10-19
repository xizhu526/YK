package com.zzz.ucoondemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zzz.ucoondemo.Interface.ServiceItemListener;
import com.zzz.ucoondemo.Model.ServiceInfo;
import com.zzz.ucoondemo.R;

import java.util.ArrayList;

/**
 * Created by 请叫我张懂 on 2016/9/28.
 */

public class myServiceRecycleAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private ArrayList<ServiceInfo> alServiceInfos;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private MyViewHolder holder;
    private ServiceItemListener mServiceItemListener;
    private int layoutPostion;

    public void setmServiceItemListener(ServiceItemListener mServiceItemListener) {
        this.mServiceItemListener = mServiceItemListener;
    }

    public myServiceRecycleAdapter(ArrayList<ServiceInfo> alServiceInfos, Context mContext) {
        this.alServiceInfos = alServiceInfos;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.myservice_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ServiceInfo mServiceInfo = new ServiceInfo();
        mServiceInfo = alServiceInfos.get(position);
        holder.tvName.setText(mServiceInfo.getName());
        holder.tvContent.setText(mServiceInfo.getContent());
        holder.tvMoney.setText(mServiceInfo.getMoney());
        holder.tvServiceState.setText(mServiceInfo.getState());
        holder.tvSercieDate.setText(mServiceInfo.getPublicDate());
        this.holder = holder;
        setClick(holder);
    }

    @Override
    public int getItemCount() {
        return alServiceInfos.size();
    }

    private void setClick(final MyViewHolder viewHolder) {
        if (mServiceItemListener != null) {
            layoutPostion = viewHolder.getLayoutPosition();
            viewHolder.ibConnTa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    layoutPostion = viewHolder.getLayoutPosition();
                    Toast.makeText(mContext, layoutPostion + " item的联系ta", Toast.LENGTH_SHORT).show();
                    mServiceItemListener.ItemConnectTaClick(alServiceInfos.get(layoutPostion));
                }
            });
        }
        viewHolder.ibMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutPostion = viewHolder.getLayoutPosition();
                Toast.makeText(mContext, layoutPostion + " item的查看详情", Toast.LENGTH_SHORT).show();
                mServiceItemListener.ItemLookMoreClick(alServiceInfos.get(layoutPostion));
            }
        });
    }

}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tvName, tvContent, tvMoney, tvServiceState, tvSercieDate;
    ImageButton ibConnTa, ibMoreInfo;

    public MyViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.idTVName);
        tvContent = (TextView) itemView.findViewById(R.id.idTVSerceContent);
        tvMoney = (TextView) itemView.findViewById(R.id.idTVMoney);
        tvServiceState = (TextView) itemView.findViewById(R.id.idTVServiceState);
        tvSercieDate = (TextView) itemView.findViewById(R.id.idTVServiceDate);
        ibConnTa = (ImageButton) itemView.findViewById(R.id.idIBConnTa);
        ibMoreInfo = (ImageButton) itemView.findViewById(R.id.idIBMoreInfo);
    }

}

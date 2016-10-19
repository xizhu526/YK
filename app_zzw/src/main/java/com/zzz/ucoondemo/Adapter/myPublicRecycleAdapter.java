package com.zzz.ucoondemo.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zzz.ucoondemo.Interface.PublicItemListener;
import com.zzz.ucoondemo.Model.PublicInfo;
import com.zzz.ucoondemo.R;

import java.util.ArrayList;

/**
 * Created by 请叫我张懂 on 2016/9/28.
 */

public class myPublicRecycleAdapter extends RecyclerView.Adapter<MyPublicViewHolder> {
    private ArrayList<PublicInfo> alPublicInfos;
    private ArrayList<PublicInfo> alPublicInfos_YSJ;//以上架
    private ArrayList<PublicInfo> alPublicInfos_YXJ;//以下架
    private ArrayList<PublicInfo> alPublicInfos_DFK;//待付款
    private LayoutInflater mInflater;
    private String mTitle;
    private Context mContext;
    private int layoutPostion;
    private PublicItemListener mPublicItemListener;

    public void setmPublicItemListener(PublicItemListener mPublicItemListener) {
        this.mPublicItemListener = mPublicItemListener;
    }

    public myPublicRecycleAdapter(ArrayList<PublicInfo> alPublicInfos, Context mContext, String mTitle) {
        this.alPublicInfos = alPublicInfos;
        this.mContext = mContext;
        this.mTitle = mTitle;
        mInflater = LayoutInflater.from(mContext);
        sortInfo();
    }

    @Override
    public MyPublicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.mypublic_item, parent, false);
        MyPublicViewHolder holder = new MyPublicViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyPublicViewHolder holder, int position) {
        switch (mTitle) {
            case " 全部 ":
                PublicInfo pf = new PublicInfo();
                pf = alPublicInfos.get(position);
                if ("已上架".equals(pf.getState())) {
                    holder.ibDelete.setVisibility(View.GONE);
                    holder.ibPay.setVisibility(View.GONE);
                } else if ("已下架".equals(pf.getState())) {
                    holder.ibDown.setVisibility(View.GONE);
                    holder.ibPay.setVisibility(View.GONE);
                } else if ("待支付".equals(pf.getState())) {
                    holder.ibDelete.setVisibility(View.GONE);
                    holder.ibDown.setVisibility(View.GONE);
                    holder.ibLookDD.setVisibility(View.GONE);
                }
                initData(holder, position, alPublicInfos);
                break;
            case "已上架":
                holder.ibDelete.setVisibility(View.GONE);
                holder.ibPay.setVisibility(View.GONE);
                initData(holder, position, alPublicInfos_YSJ);
                break;
            case "已下架":
                holder.ibDown.setVisibility(View.GONE);
                holder.ibPay.setVisibility(View.GONE);
                initData(holder, position, alPublicInfos_YXJ);
                break;
            case "待支付":
                holder.ibDelete.setVisibility(View.GONE);
                holder.ibDown.setVisibility(View.GONE);
                holder.ibLookDD.setVisibility(View.GONE);
                initData(holder, position, alPublicInfos_DFK);
                break;
        }

        setClick(holder);
    }

    @Override
    public int getItemCount() {
        switch (mTitle) {
            case " 全部 ":
                return alPublicInfos.size();
            case "已上架":
                return alPublicInfos_YSJ.size();
            case "已下架":
                return alPublicInfos_YXJ.size();
            case "待支付":
                return alPublicInfos_DFK.size();
        }

        return 0;
    }

    /**
     * 分类
     */
    private void sortInfo() {
        alPublicInfos_YSJ = new ArrayList<PublicInfo>();
        alPublicInfos_YXJ = new ArrayList<PublicInfo>();
        alPublicInfos_DFK = new ArrayList<PublicInfo>();

        for (PublicInfo pf : alPublicInfos) {
            if ("已上架".equals(pf.getState())) {
                alPublicInfos_YSJ.add(pf);
            } else if ("已下架".equals(pf.getState())) {
                alPublicInfos_YXJ.add(pf);
            } else {
                alPublicInfos_DFK.add(pf);
            }

        }
    }

    private void initData(MyPublicViewHolder holder, int position, ArrayList<PublicInfo> alPublicInfos) {
        PublicInfo pi = new PublicInfo();
        pi = alPublicInfos.get(position);
        holder.tvContent.setText(pi.getContent());
        holder.tvLookedNum.setText(pi.getLooked());
        holder.tvState.setText(pi.getState());
        holder.tvDate.setText(pi.getPublicDate());
    }

    /**
     * 设置按钮监听
     *
     * @param holder
     */
    private void setClick(final MyPublicViewHolder holder) {
        layoutPostion = holder.getLayoutPosition();
        if (mPublicItemListener != null) {
            holder.ibDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, holder.getLayoutPosition() + " item的下架", Toast.LENGTH_SHORT).show();
                    mPublicItemListener.ItemDowmClick();
                }
            });
        }
        holder.ibDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, holder.getLayoutPosition() + " item的删除", Toast.LENGTH_SHORT).show();
                mPublicItemListener.ItemDeleteClick();
            }
        });
        holder.ibLookDD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, holder.getLayoutPosition() + " item的查看详情", Toast.LENGTH_SHORT).show();
                mPublicItemListener.ItemLookMore();
            }
        });
        holder.ibPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, holder.getLayoutPosition() + " item的支付", Toast.LENGTH_SHORT).show();
                mPublicItemListener.ItemPayClick();
            }
        });
    }

    /**
     * 将publicInfo添加到alPublicInfos中
     *
     * @param publicInfo
     * @param alPublicInfos
     */
    private void addData(PublicInfo publicInfo, ArrayList<PublicInfo> alPublicInfos) {
        alPublicInfos.add(publicInfo);
        notifyItemInserted(0);
    }

    /**
     * 删除数据
     *
     * @param alPublicInfos
     * @param layoutPostion
     * @return PublicInfo
     */
    private PublicInfo deleteData(ArrayList<PublicInfo> alPublicInfos, int layoutPostion) {
        PublicInfo pi = alPublicInfos.get(layoutPostion);
        alPublicInfos.remove(layoutPostion);
        notifyItemRemoved(layoutPostion);
        return pi;
    }
}

class MyPublicViewHolder extends RecyclerView.ViewHolder {
    TextView tvContent, tvLookedNum, tvState, tvDate;
    ImageButton ibDown, ibDelete, ibLookDD, ibPay;

    public MyPublicViewHolder(View itemView) {
        super(itemView);
        tvContent = (TextView) itemView.findViewById(R.id.idTVPublicContent);
        tvLookedNum = (TextView) itemView.findViewById(R.id.idTVLookeds);
        tvState = (TextView) itemView.findViewById(R.id.idTVPublicState);
        tvDate = (TextView) itemView.findViewById(R.id.idTVPublicDate);
        ibDown = (ImageButton) itemView.findViewById(R.id.idIBDown);
        ibDelete = (ImageButton) itemView.findViewById(R.id.idIBDelete);
        ibLookDD = (ImageButton) itemView.findViewById(R.id.idIBLookMore);
        ibPay = (ImageButton) itemView.findViewById(R.id.idIBPay);
    }
}

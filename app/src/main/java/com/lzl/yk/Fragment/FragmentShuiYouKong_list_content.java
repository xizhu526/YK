package com.lzl.yk.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

import com.lzl.yk.Activity.MainActivity;
import com.lzl.yk.R;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by xizu on 2016/10/17.
 */

public class FragmentShuiYouKong_list_content extends Fragment {

    LayoutInflater inflater;
    View layout;
    Context context;
    ListView mlistview;
    ArrayList<String> list_info;
    MAdapter adapter;
    MainActivity mainActivity;
    FragmentShuiYouKong_list_content mlistcontent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shuiyoukong_list_content,container,false);
        initData();
        initListview(view);
        return view;
    }

    private  void initData(){
        mainActivity = (MainActivity) getActivity();
        list_info = new ArrayList<String>();
        list_info.add("标题");
        list_info.add("活动售价");
        list_info.add("需求人数");
        list_info.add("活动地点");
        list_info.add("详细地点");
        list_info.add("开始时间");
        list_info.add("截止时间");
        list_info.add("联系电话");
        list_info.add("button");
    }

    private  void initListview(View view){
        context = getActivity();
        inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        layout = inflater.inflate(R.layout.fragment_shuiyoukong_list_content, (ViewGroup)view.findViewById(R.id.SYK_Llayout_ccontent_list));

        mlistview = (ListView) layout.findViewById(R.id.SYK_ccontent_list);
        adapter =new MAdapter(context,list_info);
        mlistview.setAdapter(adapter);
    }

    class MAdapter extends BaseAdapter {

        ArrayList<String> adapter_list_info;
        Context context;
        ViewHolder holder;
        private MAdapter(Context context, ArrayList<String> list_info){
            this.context = context;
            this.adapter_list_info = list_info;
        }


        @Override
        public int getCount() {
            return adapter_list_info.size();
        }

        @Override
        public Object getItem(int position) {
            return adapter_list_info.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(adapter_list_info.get(position).equals("button")){
                convertView = LayoutInflater.from(context).inflate(R.layout.fragment_shuiyoukong_list_item_btn, null);
                holder = new ViewHolder();
                holder.button = (Button) convertView.findViewById(R.id.SYK_list_item_btn);
                //holder.button.setWidth(500);
            }else{
                convertView = LayoutInflater.from(context).inflate(R.layout.fragment_shuiyoukong_list_item, null);
                holder = new ViewHolder();
                holder.textview = (TextView) convertView.findViewById(R.id.SYK_list_item_tv);
                holder.edittext = (EditText) convertView.findViewById(R.id.SYK_list_item_et);
                holder.textview.setText(adapter_list_info.get(position));
                if(adapter_list_info.get(position).equals("联系电话")&&!mainActivity.getPhone_num().equals("2")){
                    holder.edittext.setText(mainActivity.getPhone_num());
                }else{
                    holder.edittext.setHint(adapter_list_info.get(position));
                }
            }
            return convertView;
        }
        class ViewHolder{
            TextView textview;
            EditText edittext;
            Button button;
        }

    }


}

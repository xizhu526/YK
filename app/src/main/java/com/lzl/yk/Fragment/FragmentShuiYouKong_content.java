package com.lzl.yk.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.common.collect.Lists;
import com.lzl.yk.Activity.SelectPictureActivity;
import com.lzl.yk.R;
import com.lzl.yk.View.DragGridView;
import com.lzl.yk.utils.BitmapUtil;
import com.lzl.yk.utils.FinalNumInter;

import java.util.Collections;
import java.util.List;

/**
 * Created by 西猪 on 2016/10/7.
 */
public class FragmentShuiYouKong_content extends Fragment implements FinalNumInter, View.OnClickListener {



    DragGridView dragGridView;

    EditText ediText;
    private List<String> dataSourceList = Lists.newArrayList();
    private ImageAdaper adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shuiyoukong_photo, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        dragGridView = (DragGridView) view.findViewById(R.id.dragGridView);
        ediText = (EditText) view.findViewById(R.id.ediText);
        initData();
    }


    private void initData() {
        adapter = new ImageAdaper(getActivity(), dataSourceList);
        dragGridView.setAdapter(adapter);
        dragGridView.setOnChangeListener(new DragGridView.OnChanageListener() {
            @Override
            public void onChange(int form, int to) {
                String temp = (String) adapter.getItem(form);
                if (to < dataSourceList.size()) {
                    Collections.swap(dataSourceList, form, to);
                } else {
                    dataSourceList.add(dataSourceList.remove(form));
                }
                dataSourceList.set(to, temp);
                adapter.notifyDataSetChanged();
            }

            @Override
            public boolean onDown(int position) {
                if (position == adapter.getCount() - 1 || position == adapter.getCount() - 2) {
                    return false;
                } else {
//						HashMap<String, Object> map = (HashMap<String, Object>) adaper.getItem(position);
//						getbitmap(map,mImageView);
                    return true;
                }
            }
        });
        dragGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageAdaper adapter = (ImageAdaper) parent.getAdapter();
                if (adapter.objects.size() < 9 && position == parent.getCount() - 1) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), SelectPictureActivity.class);
                    intent.putExtra("intent_max_num", dataSourceList.size());
                    startActivityForResult(intent, PHOTOCODE);
                } else if (position >= adapter.objects.size()) {
                    adapter.setShowDel(!adapter.getShowDel());
                    adapter.notifyDataSetInvalidated();
                } else {
                    String map = (String) adapter.getItem(position);
                    if (adapter.getShowDel()) {
                        if (null == adapter.getItem(0)) {
//                            mImageView.setImageResource(ProjectClassificationHelper.getIncitaionEventsUriStyle(type));
                        } else {
//                            BitmapUtil.setImageViewByImagLoading((String) adaper.getItem(0), mImageView);
                            dataSourceList.remove(map);
                            adapter.update(dataSourceList);
                            if (dataSourceList.size() == 0) {
//                                mImageView.setImageResource(ProjectClassificationHelper.getIncitaionEventsUriStyle(type));
                            }
                        }
                    } else {
//                        BitmapUtil.setImageViewByImagLoading(map, mImageView);
                    }
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTOCODE && resultCode == PHOTORESULT) {
            List<String> phothpathList = data.getStringArrayListExtra("paths");
            for (String path : phothpathList) {
                dataSourceList.add(path);
            }
//            BitmapUtil.setImageViewByImagLoading(dataSourceList.get(0), mImageView);
            adapter.setShowDel(false);
            adapter.update(dataSourceList);
        }
    }

    @Override
    public void onClick(View view) {

    }

    public class ImageAdaper extends BaseAdapter {
        private boolean isShowDel = false;
        private Context ctx;
        private List<String> objects;


        public ImageAdaper(Context ctx, List<String> objects) {
            this.objects = objects;
            this.ctx = ctx;
        }

        @Override
        public int getCount() {
            if (objects.size() < 9 && objects.size() > 0) {
                return objects.size() + 2;
            } else {
                return objects.size() + 1;
            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public Object getItem(int position) {
            if (position >= objects.size()) {
                return null;
            } else {
                return objects.get(position);
            }
        }

        public boolean getShowDel() {
            return isShowDel;
        }

        public void setShowDel(boolean isShowDel) {
            this.isShowDel = isShowDel;
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            String map = (String) getItem(position);
            viewHolder = new ViewHolder();
            if (null == convertView) {
                convertView = LayoutInflater.from(ctx).inflate(R.layout.draggridview_items, null);
                viewHolder.dragGridView_image = (ImageView) convertView.findViewById(R.id.dragGridView_image);
                viewHolder.dragGridView_del = (ImageView) convertView.findViewById(R.id.dragGridView_del);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (getShowDel()) {
                viewHolder.dragGridView_del.setVisibility(View.VISIBLE);
            } else {
                viewHolder.dragGridView_del.setVisibility(View.INVISIBLE);
            }
            if (null != map) {
                BitmapUtil.setImageViewByImagLoading(ctx, map, viewHolder.dragGridView_image);
            } else {

                viewHolder.dragGridView_image.setImageResource(R.mipmap.ic_launcher);
                viewHolder.dragGridView_del.setVisibility(View.GONE);
                if (position == getCount() - 1) {
                    if (objects.size() >= 9) {
                        viewHolder.dragGridView_image.setImageDrawable(ctx.getResources().getDrawable(R.mipmap.reduce));
                    } else {
                        viewHolder.dragGridView_image.setImageDrawable(ctx.getResources().getDrawable(R.mipmap.raise));
                    }
                } else if (position == getCount() - 2) {
                    viewHolder.dragGridView_image.setImageDrawable(ctx.getResources().getDrawable(R.mipmap.reduce));
                }
            }
            return convertView;
        }

        public void update(List<String> dataSourceList) {
            this.objects = dataSourceList;
            notifyDataSetChanged();
        }

        final class ViewHolder {
            ImageView dragGridView_del;
            ImageView dragGridView_image;
        }
    }
}

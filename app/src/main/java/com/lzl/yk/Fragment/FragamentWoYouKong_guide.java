package com.lzl.yk.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.lzl.yk.Activity.MainActivity;
import com.lzl.yk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 西猪 on 2016/9/30.
 */

public class FragamentWoYouKong_guide extends Fragment  {
    // 到达最后一张
    private static final int TO_THE_END = 0;
    // 离开最后一张
    private static final int LEAVE_FROM_END = 1;

    // 只需在这里添加删除图片即可
    private int[] ids = { R.drawable.guide_1,
            R.drawable.guide_3, R.drawable.guide_5,
            R.drawable.guide_6,R.drawable.guide_7
    };

    private List<View> guides = new ArrayList<View>();
    private ViewPager pager;

    private ImageView curDot;
    private LinearLayout dotContain; // 存储点的容器
    private int offset;              // 位移量
    private int curPos = 0;          // 记录当前的位置
    boolean key;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_woyoukong_guide, container, false);
        init(view);
        //((MainActivity)this.getActivity()).registerMyTouchListener(mTouchListener);
        return view;
    }

    private MainActivity.MyTouchListener mTouchListener = new MainActivity.MyTouchListener() {
        @Override
        public void onTouchEvent(MotionEvent event) {

            int action=event.getAction();

            switch (action) {
                case MotionEvent.ACTION_UP:
                    Log.d("guide.ACTION_UP", "1");
                    break;
                case  MotionEvent.ACTION_DOWN:
                    key = true;
                    Log.d("guide.ACTION_DOWN", "2");
                    break;
                case  MotionEvent.ACTION_MOVE:
                    if (key==true){
                        if(curPos == ids.length-1 ){
                            curPos=0;
                        }else{
                            curPos++;
                        }
                        pager.setCurrentItem(curPos, true);
                        key=false;
                        Log.d("guide.ACTION_MOVE", "3");
                    }

                    break;
            }
        }
    };
    private ImageView buildImageView(int id)
    {
        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(id);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(params);
        iv.setScaleType(ScaleType.FIT_XY);
        return iv;
    }

    // 功能介绍界面的初始化
    private void init(View view)
    {
        this.getView(view);
        initDot();
        ImageView iv = null;
        guides.clear();
        for (int i = 0; i < ids.length; i++) {
            iv = buildImageView(ids[i]);
            guides.add(iv);
        }

        System.out.println("guild_size="+guides.size());

        // 当curDot的所在的树形层次将要被绘出时此方法被调用
        curDot.getViewTreeObserver().addOnPreDrawListener(
                new OnPreDrawListener() {
                    public boolean onPreDraw() {
                        // 获取ImageView的宽度也就是点图片的宽度
                        offset = curDot.getWidth();
                        return true;
                    }
                });

        final GuidePagerAdapter adapter = new GuidePagerAdapter(guides);
        // ViewPager设置数据适配器，这个类似于使用ListView时用的adapter
        pager.setAdapter(adapter);
        pager.clearAnimation();
        // 为Viewpager添加事件监听器 OnPageChangeListener
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position)
            {

                int pos = position % ids.length;

                moveCursorTo(pos);

//                if (pos == ids.length-1) {// 到最后一张了
//                    handler.sendEmptyMessageDelayed(TO_THE_END, 500);
//
//                } else if (curPos == ids.length - 1) {
//                    handler.sendEmptyMessageDelayed(LEAVE_FROM_END, 100);
//                }
                curPos = pos;
                super.onPageSelected(position);
            }
        });

    }

    /**
     *  在layout中实例化一些View
     */
    private void getView(View view)
    {
        dotContain = (LinearLayout) view.findViewById(R.id.dot_contain);
        pager = (ViewPager) view.findViewById(R.id.contentPager);
        curDot = (ImageView) view.findViewById(R.id.cur_dot);
//        start = (ImageView) view.findViewById(R.id.open);
//        start.setOnClickListener(new OnClickListener()
//        {
//
//            public void onClick(View v)
//            {
////                // 点击体验
////                Intent intent = new Intent();
////                intent.setClass(GuideActivity.this, MainActivity.class);
////                startActivity(intent);
//
//            }
//        });
    }

    /**
     * 初始化点 ImageVIew
     * @return 返回true说明初始化点成功，否则实例化失败
     */
    private boolean initDot()
    {

        if(ids.length > 0){
            ImageView dotView ;
            for(int i=0; i<ids.length; i++)
            {
                dotView = new ImageView(getActivity());
                dotView.setImageResource(R.drawable.dot1_w);
                dotView.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,1.0f));

                dotContain.addView(dotView);
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * 移动指针到相邻的位置 动画
     * @param position
     * 指针的索引值
     * */
    private void moveCursorTo(int position) {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation tAnim =
                new TranslateAnimation(offset*curPos, offset*position, 0, 0);
        animationSet.addAnimation(tAnim);
        animationSet.setDuration(300);
        animationSet.setFillAfter(true);
        curDot.startAnimation(animationSet);
    }

//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == TO_THE_END)
//                start.setVisibility(View.VISIBLE);
//            else if (msg.what == LEAVE_FROM_END)
//                start.setVisibility(View.GONE);
//        }
//    };

    // ViewPager 适配器
    class GuidePagerAdapter extends PagerAdapter {

        private List<View> views;

        public GuidePagerAdapter(List<View> views) {
            this.views = views;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1 % views.size()));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            // 注意这里一定要返回一个稍微大点值,不然滑到顶就滑不动了
            return views.size() * 20;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            Log.e("tag", "instantiateItem = " + arg1);
            ((ViewPager) arg0).addView(views.get(arg1 % views.size()), 0);
            return views.get(arg1 % views.size());
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }
    }
}

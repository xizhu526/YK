package com.zzz.ucoondemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzz.ucoondemo.listener.mPulicViewpagerListener;

import java.util.ArrayList;

/**
 * Created by 请叫我张懂 on 2016/9/26.
 */

public class ViewPageIndicator extends LinearLayout {
    private ArrayList<String> mTitiles;//指示器的Item
    private int mItemNum = 4;//Item的数量，默认4个
    private final static int COLOR_TEXT_NORMAL = 0xcc000000;
    private final static int COLOR_TEXT_SELECTED = 0xffc3d94f;
    private final static int COLOR_TEXT_SELECTED_LINE = 0xffc3d94f;
    private Paint mPaint;
    private Path mPath;
    private int mLineLong;//下划线的长度
    private int mTabWidth;//标题栏的长度
    private int mTranslationX = 0;//下划线滑动距离
    private ViewPager mViewPager;
    private mPulicViewpagerListener mListener;

    public ViewPageIndicator(Context context) {
        super(context, null);
    }

    public ViewPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(COLOR_TEXT_SELECTED_LINE);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);

    }

    /**
     * 设置ItmeNum,默认为4
     *
     * @param ItemNum
     */
    private void setItemNum(int ItemNum) {
        mItemNum = ItemNum;
    }

    /**
     * 根据传入的titles设置指示器的item
     *
     * @param titles
     */
    public void setItemByTitles(ArrayList<String> titles) {
        mTitiles = titles;
        if (titles.size() > 0) {
            this.removeAllViews();
            for (String title : mTitiles) {
                if (title != null) {
                    this.addView(crateViewByTitle(title));
                }
            }
        }
        setItemNum(mTitiles.size());
    }

    /**
     * 根据title生成TextView加入布局中
     *
     * @param title
     */
    private TextView crateViewByTitle(String title) {
        TextView tv = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / mItemNum;
        tv.setText(title);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        tv.setTextColor(COLOR_TEXT_NORMAL);
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLineLong = w / mItemNum;
        mTabWidth = w / mItemNum;
        initLine();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mTranslationX, getHeight());
        canvas.drawPath(mPath, mPaint);
        canvas.restore();
    }

    /**
     * 初始化下划线
     */
    private void initLine() {
        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mLineLong, 0);
    }

    /**
     * 设置选中文本的高亮
     *
     * @param position
     */
    public void setHighLight(int position) {
        resetTextView(position);
        View view = getChildAt(position);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(COLOR_TEXT_SELECTED);
        }
    }

    /**
     * 将之前设置的高亮还原
     *
     * @param position
     */
    private void resetTextView(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
            }
        }
    }

    /**
     * 为指定的ViewPager设置监听,结束调用之后请掉用remove
     *
     * @param viewPager
     */
    public void setViewPageChangeListener(ViewPager viewPager) {
        this.mViewPager = viewPager;
        this.mListener = new mPulicViewpagerListener(getContext(), this);
        if (mViewPager != null) {
            mViewPager.addOnPageChangeListener(mListener);
        }
        setTabItemClick();
    }

    /**
     * 移除ViewPageChangeListener
     */
    public void removeViewPageChangeListener() {
        mViewPager.removeOnPageChangeListener(mListener);
    }

    /**
     * 下划线滑动
     *
     * @param postion
     * @param postionOffset
     */
    public void scroll(int postion, float postionOffset) {
        mTranslationX = (int) (mTabWidth * postionOffset + postion * mTabWidth);
        invalidate();//重绘
    }

    private void setTabItemClick() {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            final int j = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });

        }


    }
}

package com.lzl.yk.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.lzl.yk.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import static com.lzl.yk.Activity.UserLogin.mTencent;

/**
 * @author manymore13
 */
public class GuideActivity extends Activity implements View.OnClickListener {

    public static final String APPID = "1105684469";
    static Tencent mTencent;
    private static boolean isServerSideLogin = false;
    private String scope; //获取信息的范围参数
    private UserInfo userInfo; //qq用户信息
    private IUiListener loginListener; //授权登录监听器
    private IUiListener userInfoListener; //获取用户信息监听器
    private Intent mIntent;
    private static final int REQUEST_LOGIN_QQ = 1;
    private static final int REQUEST_LOGIN_PHONE = 2;
    private static String APPKEY = "17e94399992e0";
    private static String APPSECRET = "0a1e3780f3d03aaad1e7a6715a78ff2c";

    // �������һ��
    private static final int TO_THE_END = 0;
    // �뿪���һ��
    private static final int LEAVE_FROM_END = 1;

    // ֻ�����������ɾ��ͼƬ����
    private int[] ids = {R.drawable.guide_1,
            R.drawable.guide_3, R.drawable.guide_5,
            R.drawable.guide_6, R.drawable.guide_7
    };

    private List<View> guides = new ArrayList<View>();
    private ViewPager pager;
    private ImageView curDot;
    private Button btn_login;
    private LinearLayout dotContain; // �洢�������
    private int offset;              // λ����
    private int curPos = 0;          // ��¼��ǰ��λ��

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        init();

    }

    private ImageView buildImageView(int id) {
        ImageView iv = new ImageView(this);
        iv.setImageResource(id);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT);
        iv.setLayoutParams(params);
        iv.setScaleType(ScaleType.FIT_XY);
        return iv;
    }


    private void init() {

        this.getView();
        initDot();
        ImageView iv = null;
        guides.clear();
        for (int i = 0; i < ids.length; i++) {
            iv = buildImageView(ids[i]);
            guides.add(iv);
        }

        System.out.println("guild_size=" + guides.size());

        // ��curDot�����ڵ����β�ν�Ҫ�����ʱ�˷���������
        curDot.getViewTreeObserver().addOnPreDrawListener(
                new OnPreDrawListener() {
                    public boolean onPreDraw() {
                        // ��ȡImageView�Ŀ��Ҳ���ǵ�ͼƬ�Ŀ��
                        offset = curDot.getWidth();
                        return true;
                    }
                });

        final GuidePagerAdapter adapter = new GuidePagerAdapter(guides);
        // ViewPager�������������������������ʹ��ListViewʱ�õ�adapter
        pager.setAdapter(adapter);
        pager.clearAnimation();
        // ΪViewpager����¼������� OnPageChangeListener
        pager.setOnPageChangeListener(new SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                int pos = position % ids.length;

                moveCursorTo(pos);

                if (pos == ids.length - 1) {// �����һ����
                    handler.sendEmptyMessageDelayed(TO_THE_END, 500);

                } else if (curPos == ids.length - 1) {
                    handler.sendEmptyMessageDelayed(LEAVE_FROM_END, 100);
                }
                curPos = pos;
                super.onPageSelected(position);
            }
        });

    }

    /**
     * ��layout��ʵ����һЩView
     */
    private void getView() {
        dotContain = (LinearLayout) this.findViewById(R.id.dot_contain);
        pager = (ViewPager) findViewById(R.id.contentPager);
        curDot = (ImageView) findViewById(R.id.cur_dot);
        btn_login = (Button) findViewById(R.id.ac_guide_login_btn);
        btn_login.setOnClickListener(this);
    }

    /**
     * ��ʼ���� ImageVIew
     *
     * @return ����true˵����ʼ����ɹ�������ʵ����ʧ��
     */
    private boolean initDot() {

        if (ids.length > 0) {
            ImageView dotView;
            for (int i = 0; i < ids.length; i++) {
                dotView = new ImageView(this);
                dotView.setImageResource(R.drawable.dot1_w);
                dotView.setLayoutParams(new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));

                dotContain.addView(dotView);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * �ƶ�ָ�뵽���ڵ�λ�� ����
     *
     * @param position ָ�������ֵ
     */
    private void moveCursorTo(int position) {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation tAnim =
                new TranslateAnimation(offset * curPos, offset * position, 0, 0);
        animationSet.addAnimation(tAnim);
        animationSet.setDuration(300);
        animationSet.setFillAfter(true);
        curDot.startAnimation(animationSet);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == TO_THE_END) {
                btn_login.setVisibility(View.VISIBLE);
            } else if (msg.what == LEAVE_FROM_END) {
                btn_login.setVisibility(View.GONE);
            }

        }
    };

    // ViewPager ������
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
            // ע������һ��Ҫ����һ����΢���ֵ,��Ȼ�������ͻ�������
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ac_guide_login_btn:
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }

}
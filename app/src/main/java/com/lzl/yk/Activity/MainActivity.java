package com.lzl.yk.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.lzl.yk.Fragment.FragmentDouYouKong;
import com.lzl.yk.Fragment.FragmentShuiYouKong;
import com.lzl.yk.Fragment.FragmentTabHost;
import com.lzl.yk.Fragment.FragmentWoYouKong;
import com.lzl.yk.R;
import com.zzz.ucoondemo.Activity.FragmentContainActivity;
import com.zzz.ucoondemo.view.CircleImageView;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//--------------------------FragmentTabHost---------------------------------
    // 定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    // 定义一个布局
    private LayoutInflater layoutInflater;

    // 定义数组来存放Fragment界面
    private Class fragmentArray[] = { FragmentWoYouKong.class,
            FragmentDouYouKong.class, FragmentShuiYouKong.class};

    // 定义数组来存放按钮图片
    private int mImageViewArray[] = { R.drawable.main_tab_item_woyoukong,
            R.drawable.main_tab_item_douyoukong, R.drawable.main_tab_item_shuiyoukong,
             };

    // Tab选项卡的文字
    private String mTextviewArray[] = { "我有空", "都有空", "谁有空" };
//--------------------------FragmentTabHost---------------------------------
//--------------------------------------------------------------------------
    TextView user_menu_xinxi;
    TextView user_menu_fabu;
    TextView user_menu_fuwu;
    TextView user_menu_bangzhu;
    TextView user_menu_guanyu;
    TextView user_menu_name;
    public String phone_num="1";
    String nickName = "1";
    CircleImageView use_menu_icon;

    public static final String TOOLBARTITLE = "ToolBarTitle";
    public static final String MYINFO = "MyInfo";
    public static final String MYSERVICE = "MyService";
    public static final String MYPUBLIC = "MyPublic";
    public static final String LOGIN = "Login";
    private static  final  int REQUEST_LOGIN_QQ=1;
    private static  final  int REQUEST_LOGIN_PHONE=2;
    private  static  final  int LOGIN_REQUEST = 3;
    int a;

    public static final int  CHANGE_USER_NAME =1;
    private boolean onClik_mark = true;
    public Intent mIntent;

    RatingBar mRatingBar;

    private static String APPKEY = "17e94399992e0";

    private static String APPSECRET = "0a1e3780f3d03aaad1e7a6715a78ff2c";

    private Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case REQUEST_LOGIN_PHONE:
                    //Log.d("phone_num", "phone_num: "+phone_num);
                    user_menu_name.setText("user"+phone_num);
                    break;
                case REQUEST_LOGIN_QQ:
                    user_menu_name.setText("user"+nickName);
                    break;
             default:
                 break;
            }
        };
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
       // getlogininfo();
        SMSSDK.initSDK(this, APPKEY, APPSECRET);
        initView();

    }

    private void  getlogininfo(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        int login_key=bundle.getInt("login_key");
        if(login_key == REQUEST_LOGIN_PHONE){
            phone_num = bundle.getString("phone_num");
            Message message_phone = new Message();
            message_phone.what = REQUEST_LOGIN_PHONE;
            handler.sendMessage(message_phone);
        }else if(login_key == REQUEST_LOGIN_QQ){
            nickName = bundle.getString("nickName");
            Message message_qq = new Message();
            message_qq.what = REQUEST_LOGIN_QQ;
            handler.sendMessage(message_qq);
        }
    }


    /**
     * 初始化组件
     */
    private void initView() {
        //菜单中的5个选项
        user_menu_xinxi = (TextView) findViewById(R.id.user_menu_xinxi);
        user_menu_fabu = (TextView) findViewById(R.id.user_menu_fabu);
        user_menu_fuwu = (TextView) findViewById(R.id.user_menu_fuwu);
        user_menu_bangzhu = (TextView) findViewById(R.id.user_menu_bangzhu);
        user_menu_guanyu = (TextView) findViewById(R.id.user_menu_guanyu);
        use_menu_icon = (CircleImageView) findViewById(R.id.user_icon);
        user_menu_name = (TextView) findViewById(R.id.user_name);
        user_menu_xinxi.setOnClickListener(this);
        user_menu_fabu.setOnClickListener(this);
        user_menu_fuwu.setOnClickListener(this);
        user_menu_bangzhu.setOnClickListener(this);
        user_menu_guanyu.setOnClickListener(this);
        use_menu_icon.setOnClickListener(this);

        mRatingBar =(RatingBar)findViewById(R.id.user_star);
        //滑块的星形数量
        mRatingBar.setNumStars(5);
        //设置最大值
        mRatingBar.setMax(100);
        //设置每次更改的最小长度
        mRatingBar.setStepSize((float) 0.5);
        mRatingBar.setRating(2);
        // 实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        // 实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        // 得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i])
                    .setIndicator(getTabItemView(i));

            //用于从activity中向fragment传递数据
            switch (i){
                case 0:
                    Bundle b1 = new Bundle();
                   // b1.putSerializable("myHorizontalScrollView",myHorizontalScrollView);
                    // 将Tab按钮添加进Tab选项卡中
                    mTabHost.addTab(tabSpec, FragmentWoYouKong.class, b1);
                    break;
                case 1:
                    Bundle b2 = new Bundle();
                    b2.putString("key", "I am tab2");
                    // 将Tab按钮添加进Tab选项卡中
                    mTabHost.addTab(tabSpec, FragmentDouYouKong.class, b2);
                    break;
                case 2:
                    Bundle b3 = new Bundle();
                    b3.putString("key", "I am tab3");
                    // 将Tab按钮添加进Tab选项卡中
                    mTabHost.addTab(tabSpec, FragmentShuiYouKong.class, b3);
                    break;
                default:
                    break;
            }
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundResource(R.drawable.main_tab_item_bg);


            //getSupportFragmentManager().findFragmentByTag(tag).setArguments(bundle);
        }
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tab_item_view, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);

        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextviewArray[index]);

        return view;
    }

    @Override
    public void onClick(View v) {
        mIntent = new Intent();
        switch (v.getId()){
            case R.id.user_menu_xinxi:
                mIntent.putExtra(TOOLBARTITLE, MYINFO);
                onClik_mark = true;
                break;
            case R.id.user_menu_fabu:
               mIntent.putExtra(TOOLBARTITLE, MYPUBLIC);
                onClik_mark = true;
                break;
            case R.id.user_menu_fuwu:
                mIntent.putExtra(TOOLBARTITLE, MYSERVICE);
                onClik_mark = true;
                break;
            case R.id.user_menu_bangzhu:
                mIntent.putExtra(TOOLBARTITLE, MYINFO);
                onClik_mark = true;
                break;
            case R.id.user_menu_guanyu:
                mIntent.putExtra(TOOLBARTITLE, MYINFO);
                onClik_mark = true;
                break;
            case  R.id.user_icon:
                Intent intent = new Intent();
                intent.setClass(this,UserLogin.class);
                startActivityForResult(intent,LOGIN_REQUEST);
                onClik_mark = false;
                break;
            default:
                break;
        }
        if(onClik_mark){
            mIntent.setClass(this, FragmentContainActivity.class);
            this.startActivity(mIntent);
        }

    }

    public String getPhone_num(){
        return phone_num;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LOGIN_REQUEST){
            if(resultCode == RESULT_OK){
                int login_key = data.getIntExtra("login_key",0);
                switch (login_key){
                    case 0:
                        break;
                    case REQUEST_LOGIN_PHONE:
                        phone_num = data.getStringExtra("phone_num");
                        Message message_phone = new Message();
                        message_phone.what = REQUEST_LOGIN_PHONE;
                        handler.sendMessage(message_phone);
                        break;
                    case REQUEST_LOGIN_QQ:
                        nickName = data.getStringExtra("nickName");
                        Message message_qq = new Message();
                        message_qq.what = REQUEST_LOGIN_QQ;
                        handler.sendMessage(message_qq);
                        break;

                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //-------让Fragamenth获取点击事件
    /**
     * 回调接口
     * @author zhaoxin5
     *
     */
    public interface MyTouchListener
    {
        public void onTouchEvent(MotionEvent event);
    }

    /*
     * 保存MyTouchListener接口的列表
     */
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MainActivity.MyTouchListener>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener)
    {
        myTouchListeners.add( listener );
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener)
    {
        myTouchListeners.remove( listener );
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            listener.onTouchEvent(ev);
        }


        return super.dispatchTouchEvent(ev);
    }
}

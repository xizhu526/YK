package com.lzl.yk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import com.lzl.yk.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


/**
 * Created by 西猪 on 2016/10/13.
 */

public class UserLogin extends AppCompatActivity implements View.OnClickListener {

    Button login_btn_phone;
    Button login_btn_qq;
    Button user_btn_get_qq_info;
    public static final String APPID = "1105684469";
    static Tencent mTencent;
    private static boolean isServerSideLogin = false;
    private String scope; //获取信息的范围参数
    private UserInfo userInfo; //qq用户信息
    private IUiListener loginListener; //授权登录监听器
    private IUiListener userInfoListener; //获取用户信息监听器
    private  Intent mIntent;
    private static  final  int REQUEST_LOGIN_QQ=1;
    private static  final  int REQUEST_LOGIN_PHONE=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_login);
        initView();
        initData();

    }

    private void initView() {
        login_btn_phone = (Button) findViewById(R.id.user_btn_login_phone);
        login_btn_qq = (Button) findViewById(R.id.user_btn_login_qq);
        user_btn_get_qq_info =(Button) findViewById(R.id.user_btn_get_qq_info);
        login_btn_phone.setOnClickListener(this);
        login_btn_qq.setOnClickListener(this);
        user_btn_get_qq_info.setOnClickListener(this);
    }

    private void initData() {
        //初始化qq主操作对象
        mTencent = Tencent.createInstance(APPID, UserLogin.this);
        //要所有权限，不然会再次申请增量权限，这里不要设置成get_user_info,add_t
        scope = "all";

        loginListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {

            }

            /**
             * 返回json数据样例
             *
             * {"ret":0,"pay_token":"D3D678728DC580FBCDE15722B72E7365",
             * "pf":"desktop_m_qq-10000144-android-2002-",
             * "query_authority_cost":448,
             * "authority_cost":-136792089,
             * "openid":"015A22DED93BD15E0E6B0DDB3E59DE2D",
             * "expires_in":7776000,
             * "pfkey":"6068ea1c4a716d4141bca0ddb3df1bb9",
             * "msg":"",
             * "access_token":"A2455F491478233529D0106D2CE6EB45",
             * "login_cost":499}
             */
            @Override
            public void onComplete(Object value) {
//                Toast.makeText(UserLogin.this, "有数据返回",
//                        Toast.LENGTH_SHORT).show();
                System.out.println("有数据返回..");
                Log.e("onComplete", "有数据返回");
                if (value == null) {
                    return;
                }

                try {
                    JSONObject jo = (JSONObject) value;

                    int ret = jo.getInt("ret");

                    System.out.println("json=" + String.valueOf(jo));

                    if (ret == 0) {
//                        Toast.makeText(UserLogin.this, "登录成功",
//                                Toast.LENGTH_SHORT).show();

                        String openID = jo.getString("openid");
                        String accessToken = jo.getString("access_token");
                        String expires = jo.getString("expires_in");
                        mTencent.setOpenId(openID);
                        mTencent.setAccessToken(accessToken, expires);
                        login_succ();
                    }

                } catch (Exception e) {
                }


            }

            @Override
            public void onCancel() {

            }
        };

        userInfoListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {

            }

            /**
             * 返回用户信息样例
             *
             * {"is_yellow_year_vip":"0","ret":0,
             * "figureurl_qq_1":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/40",
             * "figureurl_qq_2":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
             * "nickname":"攀爬←蜗牛","yellow_vip_level":"0","is_lost":0,"msg":"",
             * "city":"黄冈","
             * figureurl_1":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/50",
             * "vip":"0","level":"0",
             * "figureurl_2":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
             * "province":"湖北",
             * "is_yellow_vip":"0","gender":"男",
             * "figureurl":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/30"}
             */
            @Override
            public void onComplete(Object arg0) {
                if(arg0 == null){
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) arg0;
                    int ret = jo.getInt("ret");
                    System.out.println("json=" + String.valueOf(jo));
                    String nickName = jo.getString("nickname");
                    String gender = jo.getString("gender");

//                   Toast.makeText(UserLogin.this, "你好，" + nickName,
//                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    i.putExtra("login_key",REQUEST_LOGIN_QQ);
                    i.putExtra("nickName",nickName);
                    setResult(RESULT_OK,i);
                    finish();
                } catch (Exception e) {
                }


            }

            @Override
            public void onCancel() {

            }
        };
    }

    private  void login_succ(){
        userInfo = new UserInfo(UserLogin.this, mTencent.getQQToken());
        userInfo.getUserInfo(userInfoListener);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.user_btn_login_phone:
                // 打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        // 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            @SuppressWarnings("unchecked")
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country");
                            String phone_num = (String) phoneMap.get("phone");
                            // 提交用户信息
                            registerUser(country, phone_num);
                            Intent i = new Intent();
                            i.putExtra("login_key",REQUEST_LOGIN_PHONE);
                            i.putExtra("phone_num",phone_num);
                            setResult(RESULT_OK,i);
                            finish();
                        }
                    }
                });
                registerPage.show(this);

                break;
            case R.id.user_btn_login_qq:
               login();
                break;
            case R.id.user_btn_get_qq_info:
                if(mTencent.getQQToken() == null){
                    Toast.makeText(UserLogin.this, "获取失败",
                            Toast.LENGTH_SHORT).show();
                }
                userInfo = new UserInfo(UserLogin.this, mTencent.getQQToken());
                userInfo.getUserInfo(userInfoListener);
                break;
            default:
                break;

        }
    }

    // 手机号登陆提交用户信息
    private void registerUser(String country, String phone) {
        Random rnd = new Random();
        int id = Math.abs(rnd.nextInt());
        String uid = String.valueOf(id);
        String nickName = "SmsSDK_User_" + uid;
        String avatar = "http://tupian.qqjay.com/u/2011/0729/e755c434c91fed9f6f73152731788cb3.jpg";
        SMSSDK.submitUserInfo(uid, nickName, avatar, country, phone);
    }

    private void login(){
       // mTencent = Tencent.createInstance(APPID, this.getApplicationContext());
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
            isServerSideLogin = false;
            //Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
        } else {
            if (isServerSideLogin) { // Server-Side 模式的登陆, 先退出，再进行SSO登陆
                mTencent.logout(this);
                mTencent.login(this, "all", loginListener);
                isServerSideLogin = false;
                //Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
                return;
            }
            mTencent.logout(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
        }


//    IUiListener loginListener = new BaseUiListener() {
//        @Override
//        protected void doComplete(JSONObject values) {
//            Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
//            initOpenidAndToken(values);
//        }
//    };
//
//    public static void initOpenidAndToken(JSONObject jsonObject) {
//        try {
//            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
//            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
//            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
//            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
//                    && !TextUtils.isEmpty(openId)) {
//                mTencent.setAccessToken(token, expires);
//                mTencent.setOpenId(openId);
//            }
//        } catch(Exception e) {
//        }
//    }
//
//
//
//
//    private class BaseUiListener implements IUiListener {
//
//        @Override
//        public void onComplete(Object response) {
//            if (null == response) {
//               // Util.showResultDialog(UserLogin.this, "返回为空", "登录失败");
//                Log.e("onComplete", "返回为空 "+response.toString());
//                return;
//            }
//            JSONObject jsonResponse = (JSONObject) response;
//            if (null != jsonResponse && jsonResponse.length() == 0) {
//                Log.e("onComplete", "返回为空 "+response.toString());
//                return;
//            }
//            //Util.showResultDialog(UserLogin.this, response.toString(), "登录成功");
//            // 有奖分享处理
//          //  handlePrizeShare();
//
//
//            doComplete((JSONObject)response);
//            login_succ();
//        }
//
//        protected void doComplete(JSONObject values) {
//            Log.e("onComplete", "登录成功 "+values.toString());
//            login_succ();
//        }
//
//        @Override
//        public void onError(UiError e) {
//          // Util.toastMessage(UserLogin.this, "onError: " + e.errorDetail);
//            //Util.dismissDialog();
//        }
//
//        @Override
//        public void onCancel() {
////            Util.toastMessage(UserLogin.this, "onCancel: ");
////            Util.dismissDialog();
////            if (isServerSideLogin) {
////                isServerSideLogin = false;
////            }
//        }
//    }
//
//    private  void login_succ(){
//        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
//        Intent Intent = new Intent(this,MainActivity.class);
//        startActivity(Intent);
//    }


    }
}

package com.lzl.yk.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.lzl.yk.R;

public class Welcome extends Activity implements Runnable{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Thread thread=new Thread(this);
	    thread.start();
		
	}

	@Override
	public void run() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		Intent intent=new Intent();
		intent.setClass(Welcome.this,GuideActivity.class);
		startActivity(intent);
//		SharedPreferences sp_key = getSharedPreferences("key", 0);
//        int key = sp_key.getInt("key1",0);
//        if(key==0){
//            SharedPreferences.Editor editor = sp_key.edit();
//            editor.putInt("key1",1);
//            editor.commit();
//            Intent intent=new Intent();
//            intent.setClass(Welcome.this,GuideActivity.class);
//            startActivity(intent);
//        }else{
//            Intent intent=new Intent();
//            intent.setClass(Welcome.this,MainActivity.class);
//            startActivity(intent);
//        }
		 
		
	}

}

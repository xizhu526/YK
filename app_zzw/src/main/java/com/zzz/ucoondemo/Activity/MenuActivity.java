package com.zzz.ucoondemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zzz.ucoondemo.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnMyInfo, btnMyPublic, btnMySerice;
    public static final String TOOLBARTITLE = "ToolBarTitle";
    public static final String MYINFO = "MyInfo";
    public static final String MYSERVICE = "MyService";
    public static final String MYPUBLIC = "MyPublic";
    public Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        initView();
        btnMyInfo.setOnClickListener(this);
        btnMyPublic.setOnClickListener(this);
        btnMySerice.setOnClickListener(this);
    }

    private void initView() {
        btnMyInfo = (Button) this.findViewById(R.id.idBtnMyInfo);
        btnMyPublic = (Button) this.findViewById(R.id.idBtnMyPublic);
        btnMySerice = (Button) this.findViewById(R.id.idBtnMyService);
    }

    @Override
    public void onClick(View v) {
        mIntent = new Intent();
        int i = v.getId();
        if (i == R.id.idBtnMyInfo) {
            Intent intent = mIntent.putExtra(TOOLBARTITLE, MYINFO);
        } else if (i == R.id.idBtnMyPublic) {
            mIntent.putExtra(TOOLBARTITLE, MYPUBLIC);

        } else if (i == R.id.idBtnMyService) {
            mIntent.putExtra(TOOLBARTITLE, MYSERVICE);

        }
        mIntent.setClass(this, FragmentContainActivity.class);
        this.startActivity(mIntent);
    }
}

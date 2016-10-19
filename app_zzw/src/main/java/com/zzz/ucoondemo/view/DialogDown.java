package com.zzz.ucoondemo.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.zzz.ucoondemo.R;

/**
 * Created by 请叫我张懂 on 2016/10/6.
 */

public class DialogDown extends AlertDialog implements View.OnClickListener {

    private ImageButton IBDialogClose, IBDialogConfirm, IbDialogConsole;
    private Context mContext;

    public DialogDown(Context context) {
        super(context);
        mContext = context;
    }

    protected DialogDown(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected DialogDown(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_dialog_down);
        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.setCanceledOnTouchOutside(false);
        initView();
        initData();
    }

    private void initData() {
        IBDialogClose.setOnClickListener(this);
        IBDialogConfirm.setOnClickListener(this);
        IbDialogConsole.setOnClickListener(this);
    }

    private void initView() {
        IBDialogClose = (ImageButton) this.findViewById(R.id.idIBDialogClose);
        IBDialogConfirm = (ImageButton) this.findViewById(R.id.idIBDialogConfirm);
        IbDialogConsole = (ImageButton) this.findViewById(R.id.idIBDialogConsole);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.idIBDialogClose) {
            dismiss();

        } else if (i == R.id.idIBDialogConfirm) {
            Toast.makeText(mContext, "idIBDialogConfirm", Toast.LENGTH_SHORT).show();

        } else if (i == R.id.idIBDialogConsole) {
            dismiss();

        }
    }
}

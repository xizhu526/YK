package com.lzl.yk.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lzl.yk.R;
import com.squareup.picasso.Picasso;

/**
 * @Module :
 * @Comments : PicassoUtils
 * @Author : KnightOneAdmin
 * @CreateDate : 16/9/11
 * @ModifiedBy : KnightOneAdmin
 * @ModifiedDate: 下午3:36
 * @Modified : PicassoUtils
 */
public class PicassoUtils {
    /**
     * 设置图片
     * @param context
     * @param url
     * @param img
     */
    public static void setAvatarImg(Context context, String url, ImageView img) {
        Picasso.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fit()
                .config(Bitmap.Config.RGB_565)
                .into(img);
    }
}

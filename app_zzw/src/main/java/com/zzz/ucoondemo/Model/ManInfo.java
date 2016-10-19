package com.zzz.ucoondemo.Model;

import java.io.Serializable;

/**
 * 个人信息
 * Created by 请叫我张懂 on 2016/9/28.
 */

public class ManInfo implements Serializable{
    private String name;//昵称
    private int icon;//头像
    private String size;//签名
    private String phoneNumber;//电话号码
    private String weCharNumber;//微信号

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeCharNumber() {
        return weCharNumber;
    }

    public void setWeCharNumber(String weCharNumber) {
        this.weCharNumber = weCharNumber;
    }
}

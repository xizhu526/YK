package com.zzz.ucoondemo.Model;

import java.util.Date;

/**
 * Created by 请叫我张懂 on 2016/10/5.
 */

public class ChatMsgInfo {
    private String name;
    private String msg;
    private Type type;
    private Date date;

    public enum Type {
        INCOMING, OUTCOMING
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

package com.zzz.ucoondemo.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 请叫我张懂 on 2016/9/28.
 */

public class ServiceInfo extends ManInfo implements Serializable {
    private String money;//赏金
    private String state;//状态
    private String content;//服务的内容
    private String publicDate;//发布时间

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

package com.zzz.ucoondemo.Interface;

import com.zzz.ucoondemo.Model.ManInfo;
import com.zzz.ucoondemo.Model.ServiceInfo;

/**
 * Created by 请叫我张懂 on 2016/10/4.
 */

public interface ServiceItemListener {
    public void ItemConnectTaClick(ServiceInfo serviceInfo);

    public void ItemLookMoreClick(ServiceInfo serviceInfo);
}

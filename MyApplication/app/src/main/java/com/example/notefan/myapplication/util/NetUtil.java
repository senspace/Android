package com.example.notefan.myapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

/**
 * Created by notefan on 15/3/26.
 * "调用检测网络连接状态方法"：
 * 在连接之前，可以直接通过以下语句检测网络是否连接：
 *  NetUtil.getNetworkState(this) != NetUtil.NETWORK_NONE
 */
public class NetUtil {
    public static final int NETWORK_NONE = 0;
    public static final int NETWORK_WIFI = 1;
    public static final int NETWORK_MOBILE = 2;

    public static int getNetworkState(Context context){
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        //wifi
        State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (state == State.CONNECTED || state == State.CONNECTING){
            return NETWORK_WIFI;
        }

        //mobile
        state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if (state == State.CONNECTED || state == State.CONNECTING){
            return NETWORK_MOBILE;
        }
        return NETWORK_NONE;
    }
}

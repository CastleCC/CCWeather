package com.ccweather.chencheng.ccweather.util;

/**
 * Created by ChenCheng on 2016/3/13.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}

package cn.shper.okhttppan.callback;

import cn.shper.okhttppan.constant.HttpError;

/**
 * Author shper
 * Description TODO
 * Version 0.1 16-6-13 C 创建
 */
public abstract class BaseCallback {

    public abstract void onFail(HttpError error, int requestId);

}

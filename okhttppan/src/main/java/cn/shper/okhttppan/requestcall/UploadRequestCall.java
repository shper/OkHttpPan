package cn.shper.okhttppan.requestcall;

import java.util.concurrent.TimeUnit;

import cn.shper.okhttppan.OkHttpPan;
import cn.shper.okhttppan.callback.UploadCallback;
import cn.shper.okhttppan.request.BaseRequest;
import cn.shper.okhttppan.utils.Logger;
import okhttp3.OkHttpClient;

/**
 * Author shper
 * Description TODO
 * Version 0.1 16-6-12 C 创建
 */
public class UploadRequestCall extends BaseRequestCall {

    private OkHttpClient uploadClient;

    public UploadRequestCall(BaseRequest request) {
        super(request);
    }

    private void buildCall(UploadCallback callback) {
        Logger.d("[TimeoutConfig - " + baseRequest.getRequestMethod() + "]" +
                " ConnectTimeout: " + baseRequest.getConnectTimeout() +
                " ReadTimeout: " + baseRequest.getReadTimeout() +
                " WriteTimeout: " + baseRequest.getWriteTimeout());
        request = baseRequest.getRequest(callback);
        uploadClient = OkHttpPan.getInstance().getClient()
                .newBuilder()
                .connectTimeout(baseRequest.getConnectTimeout(), TimeUnit.SECONDS)
                .readTimeout(baseRequest.getReadTimeout(), TimeUnit.SECONDS)
                .writeTimeout(baseRequest.getWriteTimeout(), TimeUnit.SECONDS)
                .build();
        call = uploadClient.newCall(request);
    }

    public <T> void enqueue(Class<T> clazz, UploadCallback callback) {
        buildCall(callback);
        if (callback != null) {
            callback.onStart(request);
        }

        OkHttpPan.getInstance().enqueue(this, clazz, callback);
    }

}

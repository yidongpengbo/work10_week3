package com.example.week3_04.Until;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OKHttpUntil {
    /**
     * 1.单例
     */
    private static OKHttpUntil instance;
    private final OkHttpClient mClient;
    private HttpLoggingInterceptor mInterceptor;

    public static OKHttpUntil getInstance(){
        if (instance==null){
            synchronized (OKHttpUntil.class){
                if (null==instance){
                    instance=new OKHttpUntil();
                }
            }
        }
        return instance;
    }

    private OKHttpUntil(){
        //日志拦截器
        mInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("TAG","message="+message);
            }
        });
        mInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         * 2.构造方法
         */

        mClient = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(mInterceptor)
                .build();

    }

    /**
     * 3.接口
     */
    public interface CallBack{
        void fail(Exception e);
        void success(Object o);
    }
    /**
     * 4.get异步
     */
    Handler mHandler=new Handler(Looper.getMainLooper());
    public void getEnquene(String path, final Class clazz, final CallBack callBack){
        Request request = new Request.Builder().get().url(path).build();
        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                    callBack.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                                try {
                                    String string = response.body().string();
                                    Gson gson = new Gson();
                                    final Object o = gson.fromJson(string, clazz);
                                    mHandler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            callBack.success(o);
                                        }
                                    });
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
            }
        });
    }

}

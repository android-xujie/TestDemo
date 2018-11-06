package com.example.yunwen.testdemo1022.network.http;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private static ApiManager mInstance = new ApiManager();
    private Retrofit mRetrofit;
    private static int mTimeout = 20;
    private OkHttpClient mOkHttpClient;
//    private static String mBaseUrl = "http://www.gwadarpro.com";
    private static String mBaseUrl;

    public static ApiManager getInstance() {
        return mInstance;
    }

    public ApiManager() {
    }

    public <T> T getApiService(Class<T> service,String baseurl) {
        this.mBaseUrl = baseurl;
        return getRetrofit().create(service);
    }

    private Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(getOkHttpClient())
                    .build();
        }
        return mRetrofit;
    }

    private OkHttpClient getOkHttpClient() {
        // 打印网络请求数据
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("info: ",message);
            }
        });
        loggingInterceptor.setLevel(level);
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(mTimeout, TimeUnit.SECONDS)
                    .readTimeout(mTimeout, TimeUnit.SECONDS)
                    .writeTimeout(mTimeout, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(loggingInterceptor)
                    .build();
        }
        return mOkHttpClient;
    }

}

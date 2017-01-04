package com.idea.l.rxjavaandmvp.utils;

import android.util.Log;

import com.idea.l.rxjavaandmvp.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by l on 2016/6/2.
 */
public class RetrofitUtils {
    private static final int DEFAULT_TIMEOUT = 5;
    private static Retrofit singleton;
    public static <T> T createApi(Class<T> clazz){
        if(singleton == null){
            synchronized (RetrofitUtils.class){
                if (singleton == null){
                    OkHttpClient.Builder client = new OkHttpClient.Builder();
                    client.addInterceptor(createHeaderInterceptor());
                    client.addInterceptor(createLoggingInterceptor());
                    client.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//设置超时时间

                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder.baseUrl(BuildConfig.API_SERVER_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(client.build());
                    singleton = builder.build();
                }
            }
        }
        return singleton.create(clazz);
    }




    private static HttpLogInterceptor createLoggingInterceptor() {
        HttpLogInterceptor httpLoggingInterceptor = new HttpLogInterceptor();
        httpLoggingInterceptor.setLevel(HttpLogInterceptor.Level.BASIC);
        return httpLoggingInterceptor;
    }
    private static Interceptor createHeaderInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();

                Request.Builder builder = request.newBuilder();
                builder.addHeader("head", "weidaiwang")
                        .addHeader("aversion", "100")
                        .addHeader("sversion", "6.0")
                        .addHeader("stype", "android")
                        .addHeader("appid", "asdasd")
                        .addHeader("channel", "asdasd")
                        .build();
                return chain.proceed(builder.build());
            }
        };

        return interceptor;
    }


}

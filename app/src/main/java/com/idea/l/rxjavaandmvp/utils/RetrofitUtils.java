package com.idea.l.rxjavaandmvp.utils;

import android.content.Context;

import java.io.IOException;

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
    private static Retrofit singleton;
    public static <T> T createApi(Context context, Class<T> clazz){
        if(singleton == null){
            synchronized (RetrofitUtils.class){
                if (singleton == null){
                    Retrofit.Builder builder = new Retrofit.Builder();
                    builder .baseUrl("https://mobilesrv.weidai.com.cn/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(genericClient());
                    singleton = builder.build();
                }
            }
        }
        return singleton.create(clazz);
    }
    private static OkHttpClient genericClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("head", "weidaiwang")
                                .addHeader("aversion", "100")
                                .addHeader("sversion", "6.0")
                                .addHeader("stype", "android")
                                .addHeader("appid", "asdasd")
                                .addHeader("channel", "asdasd")
                                .build();
                        return chain.proceed(request);
                    }

                })
                .build();

        return httpClient;
    }

}

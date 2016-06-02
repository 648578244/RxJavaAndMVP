package com.idea.l.rxjavaandmvp.presenter;

import android.content.Context;

import com.idea.l.rxjavaandmvp.bean.UserBean;
import com.idea.l.rxjavaandmvp.model.UserModel;
import com.idea.l.rxjavaandmvp.utils.RetrofitUtils;
import com.idea.l.rxjavaandmvp.view.BaseView;
import com.idea.l.rxjavaandmvp.view.IUserView;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by l on 2016/6/1.
 */
public class UserPresenter extends BasePresenter<IUserView> {
    private UserModel service;
    public UserPresenter(Context context) {
        service = RetrofitUtils.createApi(context, UserModel.class);
    }
    public void getUser() {
        getMvpView().showProgressDialog();
        service.getUsers("2").subscribeOn(Schedulers.io())//在非UI县城中执行getUser
                .observeOn(AndroidSchedulers.mainThread())//在UI线程中执行结果
                .subscribe(new Subscriber<UserBean>() {

                    @Override
                    public void onCompleted() {
                        getMvpView().hidProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().showError(e.getMessage());
                        getMvpView().hidProgressDialog();
                    }

                    @Override
                    public void onNext(UserBean user) {
                        getMvpView().updateView(user);
                    }
                });
    }
    @Override
    public void attachView(IUserView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }
}

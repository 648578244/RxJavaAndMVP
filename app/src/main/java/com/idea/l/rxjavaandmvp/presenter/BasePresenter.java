package com.idea.l.rxjavaandmvp.presenter;

import com.idea.l.rxjavaandmvp.bean.UserBean;
import com.idea.l.rxjavaandmvp.model.UserModel;
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
public class BasePresenter<T extends BaseView> implements Presenter<T> {
    private T mBaseView;

    @Override
    public void attachView(T mvpView) {
        mBaseView = mvpView;
    }

    @Override
    public void detachView() {
        mBaseView = null;
    }
    public boolean isViewAttached() {
        return mBaseView != null;
    }
    public T getMvpView() {
        return mBaseView;
    }
    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }
    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}

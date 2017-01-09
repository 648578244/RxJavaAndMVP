package com.idea.l.rxjavaandmvp.presenter;


import com.idea.l.rxjavaandmvp.bean.Course;
import com.idea.l.rxjavaandmvp.view.IUserView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by l on 2016/6/1.
 */
public class UserPresenter extends BasePresenter<IUserView> {
    public UserPresenter(IUserView view) {
        attachView(view);
    }
    public void getUser() {
        getMvpView().showProgressDialog();
        userModel.getUsers("111111").subscribeOn(Schedulers.io())//在非UI县城中执行getUser
                .observeOn(AndroidSchedulers.mainThread())//在UI线程中执行结果
                .subscribe(new Subscriber<Course>() {

                    @Override
                    public void onCompleted() {
                        getMvpView().hidProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hidProgressDialog();
                    }

                    @Override
                    public void onNext(Course user) {
                        getMvpView().updateView(user);
                    }
                });
    }
}

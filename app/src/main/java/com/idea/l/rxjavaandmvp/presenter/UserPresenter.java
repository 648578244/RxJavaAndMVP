package com.idea.l.rxjavaandmvp.presenter;


import com.idea.l.rxjavaandmvp.bean.BaseBean;
import com.idea.l.rxjavaandmvp.bean.Course;
import com.idea.l.rxjavaandmvp.view.IUserView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by l on 2016/6/1.
 */
public class UserPresenter extends IUserPresenter {
    public UserPresenter(IUserView view) {
        attachView(view);
    }


    @Override
    public void getUser() {
        checkViewAttached();
        BaseObjectObserver<Course> objectObserver = new BaseObjectObserver<Course>(getMvpView()) {
            @Override
            protected void onFailure(BaseBean baseBean) {

            }

            @Override
            protected void onSuccess(Course d) {
                getMvpView().updateView(d);
            }
        };
        userModel.getUsers("111111").subscribeOn(Schedulers.io())//在非UI县城中执行getUser
                .observeOn(AndroidSchedulers.mainThread())//在UI线程中执行结果
                .subscribe(objectObserver);
    }
}

package com.idea.l.rxjavaandmvp.presenter;

import com.idea.l.rxjavaandmvp.model.UserModel;
import com.idea.l.rxjavaandmvp.utils.RetrofitUtils;
import com.idea.l.rxjavaandmvp.view.BaseView;
import java.util.ArrayList;

import rx.Subscription;
/**
 * Created by l on 2016/6/1.
 */
public class BasePresenter<T extends BaseView> implements Presenter<T> {
    private T mBaseView;
    protected UserModel userModel;
    protected ArrayList<Subscription> mSubscriptionList;

    public BasePresenter() {
        userModel = RetrofitUtils.createApi(UserModel.class);
        mSubscriptionList = new ArrayList<>();
    }

    @Override
    public void attachView(T mvpView) {
        mBaseView = mvpView;
    }

    @Override
    public void detachView() {
        mBaseView = null;
        for (Subscription subscription : mSubscriptionList) {
            subscription.unsubscribe();
        }
        mSubscriptionList.clear();
    }

    public void setSubscription(Subscription subscription) {
        mSubscriptionList.add(subscription);
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

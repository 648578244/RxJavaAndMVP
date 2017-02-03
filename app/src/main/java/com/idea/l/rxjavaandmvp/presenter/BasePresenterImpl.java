package com.idea.l.rxjavaandmvp.presenter;

import com.idea.l.rxjavaandmvp.model.GetDataManager;
import com.idea.l.rxjavaandmvp.view.IBaseView;

import java.util.ArrayList;

import rx.Subscription;

/**
 * Created by l on 2016/6/1.
 */
public class BasePresenterImpl<T extends IBaseView> implements IBasePresenter<T> {
    private T mBaseView;
    protected ArrayList<Subscription> mSubscriptionList;
    public GetDataManager manager;

    public BasePresenterImpl() {
        manager = GetDataManager.getInit();
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

    public T getView() {
        return mBaseView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call IBasePresenter.attachView(MvpView) before" +
                    " requesting data to the IBasePresenter");
        }
    }
}

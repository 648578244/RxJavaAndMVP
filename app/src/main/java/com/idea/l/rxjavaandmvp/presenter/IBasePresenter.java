package com.idea.l.rxjavaandmvp.presenter;

import com.idea.l.rxjavaandmvp.view.IBaseView;

/**
 * Created by l on 2016/6/2.
 */
public interface IBasePresenter<T extends IBaseView> {
    void attachView(T mvpView);

    void detachView();

}

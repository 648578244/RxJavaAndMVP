package com.idea.l.rxjavaandmvp.presenter;

import com.idea.l.rxjavaandmvp.view.BaseView;

/**
 * Created by l on 2016/6/2.
 */
public interface Presenter<V extends BaseView> {
    void attachView(V mvpView);

    void detachView();

}

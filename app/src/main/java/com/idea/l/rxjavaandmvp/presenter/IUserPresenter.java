package com.idea.l.rxjavaandmvp.presenter;

import com.idea.l.rxjavaandmvp.view.IUserView;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-10
 */
public abstract class IUserPresenter  extends BasePresenterImpl<IUserView>{
    public abstract void getUser();
}

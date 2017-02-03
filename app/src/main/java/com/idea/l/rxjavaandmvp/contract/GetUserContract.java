package com.idea.l.rxjavaandmvp.contract;

import com.idea.l.rxjavaandmvp.bean.Course;
import com.idea.l.rxjavaandmvp.presenter.BasePresenterImpl;
import com.idea.l.rxjavaandmvp.view.IBaseView;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-16
 */
public interface GetUserContract {
    interface IUserView extends IBaseView {
        void updateView(Course course);
    }
    abstract class IUserPresenter extends BasePresenterImpl<IUserView> {

       public abstract void fetchData(String values);


    }
}

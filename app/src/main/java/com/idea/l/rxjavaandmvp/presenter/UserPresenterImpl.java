package com.idea.l.rxjavaandmvp.presenter;


import com.idea.l.rxjavaandmvp.bean.Course;
import com.idea.l.rxjavaandmvp.contract.GetUserContract;
import com.idea.l.rxjavaandmvp.model.ResponseDataListener;

/**
 * Created by l on 2016/6/1.
 */
public class UserPresenterImpl extends GetUserContract.IUserPresenter {
    public UserPresenterImpl(GetUserContract.IUserView view){
        attachView(view);
    }


    @Override
    public void fetchData(String values) {
        getView().showProgressDialog();
        manager.getResponse("12345678", new ResponseDataListener<Course>() {

            @Override
            public void onSuccess(Course course) {
                getView().updateView(course);
                getView().hidProgressDialog();
            }

            @Override
            public void onFailed(String msg) {

            }
        });
    }

}

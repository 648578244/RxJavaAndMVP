package com.idea.l.rxjavaandmvp.view;

import android.text.Selection;

import com.idea.l.rxjavaandmvp.bean.Course;
import com.idea.l.rxjavaandmvp.bean.UserBean;

/**
 * Created by l on 2016/6/1.
 */
public interface IUserView extends BaseView{
    void updateView(Course user);

    void showError(String msg);
}

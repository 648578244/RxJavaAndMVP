package com.idea.l.rxjavaandmvp.model;

import com.idea.l.rxjavaandmvp.bean.Course;
import com.idea.l.rxjavaandmvp.bean.UserBean;
import com.idea.l.rxjavaandmvp.presenter.BaseObjectObserver;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by l on 2016/6/1.
 */
public interface IServerApi {
    @GET("/courses/{courseId}")
    Observable<BaseObjectBean<Course>> getUsers(@Path("courseId") String type);
}

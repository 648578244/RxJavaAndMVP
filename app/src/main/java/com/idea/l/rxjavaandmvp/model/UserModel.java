package com.idea.l.rxjavaandmvp.model;

import com.idea.l.rxjavaandmvp.bean.Course;
import com.idea.l.rxjavaandmvp.bean.UserBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by l on 2016/6/1.
 */
public interface UserModel {
    @GET("/courses/{courseId}")
    Observable<Course> getUsers(@Path("courseId") String type);
}

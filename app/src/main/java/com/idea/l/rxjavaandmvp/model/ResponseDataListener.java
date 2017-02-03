package com.idea.l.rxjavaandmvp.model;


/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-16
 */
public interface ResponseDataListener<T> {
    void onSuccess(T t);

    void onFailed(String msg);
}

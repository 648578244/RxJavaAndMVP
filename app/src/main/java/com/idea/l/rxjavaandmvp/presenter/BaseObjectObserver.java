package com.idea.l.rxjavaandmvp.presenter;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.idea.l.rxjavaandmvp.bean.BaseBean;
import com.idea.l.rxjavaandmvp.model.BaseObjectBean;
import com.idea.l.rxjavaandmvp.view.IBaseView;

import java.util.concurrent.TimeoutException;

import rx.Subscriber;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-10
 */
public abstract class BaseObjectObserver<T> extends Subscriber<BaseBean> {
    private IBaseView mBaseView;

    public BaseObjectObserver(IBaseView view){
        this.mBaseView = view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mBaseView.showProgressDialog();
    }

    @Override
    public void onCompleted() {
        Log.i("tag","onCompleted");
        mBaseView.hidProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        Log.i("tag","onError");

        if (e instanceof NetworkErrorException){
              //网络异常
          }else if (e instanceof TimeoutException){
              //请求超时
          }
    }

    @Override
    public void onNext(BaseBean baseBean) {
        Log.i("tag","onNext");
        mBaseView.hidProgressDialog();
        if(baseBean.getR() == 1){
            if (baseBean instanceof BaseObjectBean) {
                BaseObjectBean<T> baseObjectBean = (BaseObjectBean<T>) baseBean;
                onSuccess(baseObjectBean.d);//请求成功
            }
        }else{
            onFailure(baseBean);//请求异常
        }
    }

    protected abstract void onFailure(BaseBean baseBean);


    protected abstract void onSuccess(T d);

}

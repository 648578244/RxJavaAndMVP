package com.idea.l.rxjavaandmvp.presenter;

import android.util.Log;

import com.idea.l.rxjavaandmvp.bean.BaseBean;
import com.idea.l.rxjavaandmvp.model.BaseObjectBean;
import com.idea.l.rxjavaandmvp.model.ResponseDataListener;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import rx.Subscriber;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-10
 */
public  class BaseObjectObserver<T> extends Subscriber<BaseBean> {
    private ResponseDataListener mListener;
    public BaseObjectObserver(ResponseDataListener listener){
        this.mListener = listener;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        Log.i("tag","onError");
        if (e instanceof SocketTimeoutException){
            mListener.onFailed("网络中断，请检查您的网络状态");
        } else if(e instanceof ConnectException) {
            mListener.onFailed("网络中断，请检查您的网络状态");
        }  else {
            mListener.onFailed("服务端出错");
        }
    }

    @Override
    public void onNext(BaseBean baseBean) {
        Log.i("tag","onNext");
        if(baseBean.getR() == 1){
            if (baseBean instanceof BaseObjectBean) {
                BaseObjectBean<T> baseObjectBean = (BaseObjectBean<T>) baseBean;
               mListener.onSuccess(baseObjectBean.d);//请求成功
            }
        }else{
           mListener.onFailed(baseBean.getMsg());//请求异常
        }
    }


}

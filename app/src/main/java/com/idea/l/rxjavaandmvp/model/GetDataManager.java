package com.idea.l.rxjavaandmvp.model;

import com.idea.l.rxjavaandmvp.bean.Course;
import com.idea.l.rxjavaandmvp.presenter.BaseObjectObserver;
import com.idea.l.rxjavaandmvp.utils.RetrofitUtils;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-16
 */
public class GetDataManager {
    private static GetDataManager instance = null;
    private IServerApi serverApi;
    public static GetDataManager getInit() {
        if (instance == null) {
            synchronized (GetDataManager.class) {
                if (instance == null) {
                    instance = new GetDataManager();
                }
            }
        }
        return instance;
    }

    public GetDataManager() {
        serverApi = RetrofitUtils.createApi(IServerApi.class);

    }

    public void getResponse(String str,ResponseDataListener listener){
        serverApi.getUsers(str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObjectObserver<Course>(listener));
    }
}

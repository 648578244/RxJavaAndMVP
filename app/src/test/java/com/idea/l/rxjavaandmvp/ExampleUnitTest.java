package com.idea.l.rxjavaandmvp;

import android.util.Log;

import com.idea.l.rxjavaandmvp.bean.Course;
import com.idea.l.rxjavaandmvp.model.BaseObjectBean;
import com.idea.l.rxjavaandmvp.model.GetDataManager;
import com.idea.l.rxjavaandmvp.model.IServerApi;
import com.idea.l.rxjavaandmvp.model.ResponseDataListener;
import com.idea.l.rxjavaandmvp.utils.RetrofitUtils;
import com.idea.l.rxjavaandmvp.utils.RxUnitTestTools;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import rx.observers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    private GetDataManager manager;
    private IServerApi serverApi;
    @Before
    public void setUp() throws Exception{
//        RxUnitTestTools.openRxTools();
        serverApi = RetrofitUtils.createApi(IServerApi.class);
//        manager = GetDataManager.getInit();
    }
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getUser() throws  Exception{
//        manager.getResponse("aa", new ResponseDataListener() {
//            @Override
//            public void onSuccess(Object o) {
//
//            }
//
//            @Override
//            public void onFailed(String msg) {
//
//            }
//        });
        TestSubscriber<BaseObjectBean<Course>> testSubscriber = new TestSubscriber<>();
        serverApi.getUsers("aa").toBlocking().subscribe(testSubscriber);
        BaseObjectBean<Course> course = testSubscriber.getOnNextEvents().get(0);
        Assert.assertEquals(course.d.getDescr(),"多线程是日常开发中的常用知识，也是难用知识。bala bala...");
        Assert.assertEquals(course.d.getTitle(),"深入浅出Java多线程");
    }
}
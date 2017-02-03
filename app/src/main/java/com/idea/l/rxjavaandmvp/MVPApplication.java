package com.idea.l.rxjavaandmvp;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-11
 */
public class MVPApplication extends Application {
    public static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mPatchManager = new PatchManager(this);
        mPatchManager.init(BuildConfig.VERSION_NAME);
        mPatchManager.loadPatch();


    }
}

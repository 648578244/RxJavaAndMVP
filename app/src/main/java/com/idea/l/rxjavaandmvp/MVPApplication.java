package com.idea.l.rxjavaandmvp;

import android.app.Application;
import android.content.pm.PackageManager;

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

        String appVersion = null;
        try {
            appVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mPatchManager.init(appVersion);
        mPatchManager.loadPatch();


    }
}

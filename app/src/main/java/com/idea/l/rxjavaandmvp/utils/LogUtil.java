package com.idea.l.rxjavaandmvp.utils;

import android.util.Log;

import com.idea.l.rxjavaandmvp.BuildConfig;


/**
 * Describe: Log输出
 * User: LiYajun
 * Date: 2016-06-12
 */

public class LogUtil {
    private static final String TAG = "wdw";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            Log.i(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (DEBUG) {
            Log.v(TAG, msg);
        }

    }

    public static void w(String msg) {
        if (DEBUG) {
            Log.w(TAG, msg);
        }
    }

    public static void d(String from, String msg) {
        if (DEBUG) {
            StringBuffer sb = new StringBuffer(from).append(":").append(msg);
            Log.d(TAG, sb.toString());
        }

    }

    public static void i(String from, String msg) {
        if (DEBUG) {
            StringBuffer sb = new StringBuffer(from).append(":").append(msg);
            Log.i(TAG, sb.toString());
        }
    }

    public static void e(String from, String msg) {
        if (DEBUG) {
            StringBuffer sb = new StringBuffer(from).append(":").append(msg);
            Log.e(TAG, sb.toString());
        }
    }

    public static void v(String from, String msg) {
        if (DEBUG) {
            StringBuffer sb = new StringBuffer(from).append(":").append(msg);
            Log.v(TAG, sb.toString());
        }
    }

    public static void w(String from, String msg) {
        if (DEBUG) {
            StringBuffer sb = new StringBuffer(from).append(":").append(msg);
            Log.w(TAG, sb.toString());
        }
    }

    public static void e(Throwable e) {
        if (DEBUG) {
            Log.w(TAG, Log.getStackTraceString(e));
        }
    }
}

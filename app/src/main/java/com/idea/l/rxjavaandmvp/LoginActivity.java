package com.idea.l.rxjavaandmvp;

import android.os.Bundle;

import com.idea.l.rxjavaandmvp.fragment.BaseFragment;
import com.idea.l.rxjavaandmvp.fragment.MainFragment;

/**
 * Created by l on 2016/6/1.
 */
public class LoginActivity extends AppActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createTitle("登录");
    }

    @Override
    protected BaseFragment getFirstFragment() {
        return MainFragment.newInstance();
    }

}

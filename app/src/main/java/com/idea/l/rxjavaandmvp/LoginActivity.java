package com.idea.l.rxjavaandmvp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.idea.l.rxjavaandmvp.bean.UserBean;
import com.idea.l.rxjavaandmvp.presenter.UserPresenter;

/**
 * Created by l on 2016/6/1.
 */
public class LoginActivity extends BaseActivity {
    private TextView tvShow;
    public UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvShow = (TextView) findViewById(R.id.tv_show);
        mUserPresenter = new UserPresenter(this);
        mUserPresenter.attachView(this);
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserPresenter.getUser();
            }
        });

    }

    @Override
    public void updateView(UserBean user) {
        if (user == null) return;
        tvShow.setText(user.getMsg());
    }
}
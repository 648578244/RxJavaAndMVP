package com.idea.l.rxjavaandmvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.idea.l.rxjavaandmvp.view.IUserView;

public abstract class BaseActivity extends AppCompatActivity implements IUserView {
    private Context context;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("正在加载，请稍候..");
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hidProgressDialog() {
        mProgressDialog.hide();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}

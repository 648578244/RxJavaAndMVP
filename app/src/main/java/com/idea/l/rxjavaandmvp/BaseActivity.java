package com.idea.l.rxjavaandmvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.idea.l.rxjavaandmvp.fragment.BaseFragment;
import com.idea.l.rxjavaandmvp.view.IBaseView;

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    private Context context;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制为竖屏

        context = this;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setMessage("正在加载，请稍候..");
    }

    public void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    public void addFragmentWithAmin(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(
                            R.anim.slide_in_from_right,
                            R.anim.slide_out_to_left,
                            R.anim.slide_in_from_left,
                            R.anim.slide_out_to_right)
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    //移除fragment
    public void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //布局中Fragment的ID
    protected abstract int getFragmentContentId();


    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void hidProgressDialog() {
        mProgressDialog.hide();
    }

}

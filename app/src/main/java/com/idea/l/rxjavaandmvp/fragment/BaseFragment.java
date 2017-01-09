package com.idea.l.rxjavaandmvp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idea.l.rxjavaandmvp.BaseActivity;
import com.idea.l.rxjavaandmvp.view.BaseView;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-09
 */
public abstract class BaseFragment extends DialogFragment implements BaseView {
    protected BaseActivity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = getLayoutId(inflater,container);
        initView(savedInstanceState);
        return view;
    }

    //移除fragment
    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }

    //获取布局文件ID
    protected abstract View getLayoutId(LayoutInflater inflater, ViewGroup container);

    protected abstract void initView(Bundle savedInstanceState);

    //添加fragment
    protected void addFragment(BaseFragment fragment){
        if (null != fragment){
            getHoldingActivity().addFragment(fragment);
        }
    }
    //添加fragment 通过动画效果
    protected void addFragmentWithAmin(BaseFragment fragment){
        if (null != fragment){
            getHoldingActivity().addFragmentWithAmin(fragment);
        }
    }
    public BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public void showProgressDialog() {
        mActivity.showProgressDialog();
    }

    @Override
    public void hidProgressDialog() {
        mActivity.hidProgressDialog();
    }
}

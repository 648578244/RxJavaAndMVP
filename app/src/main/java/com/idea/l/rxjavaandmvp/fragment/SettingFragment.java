package com.idea.l.rxjavaandmvp.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idea.l.rxjavaandmvp.R;
import com.idea.l.rxjavaandmvp.databinding.FragmentSettingBinding;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-09
 */
public class SettingFragment extends BaseFragment{
    private FragmentSettingBinding mBinding;
    public static BaseFragment newInstance() {
        return new SettingFragment();
    }
    @Override
    protected View getLayoutId(LayoutInflater inflater, ViewGroup container) {
        mBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_setting,container,false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}

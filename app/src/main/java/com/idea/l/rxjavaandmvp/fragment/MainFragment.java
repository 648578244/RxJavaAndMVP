package com.idea.l.rxjavaandmvp.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.idea.l.rxjavaandmvp.MainActivity;
import com.idea.l.rxjavaandmvp.R;
import com.idea.l.rxjavaandmvp.databinding.FragmentMainBinding;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-09
 */
public class MainFragment extends BaseFragment {
    private FragmentMainBinding mBinding;
    public static BaseFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected View getLayoutId(LayoutInflater inflater, ViewGroup container) {
        mBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBinding.mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mActivity, MainActivity.class);
//                startActivity(intent);
                addFragmentWithAmin(FoundFragment.newInstance());
            }
        });
    }
}

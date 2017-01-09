package com.idea.l.rxjavaandmvp;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.idea.l.rxjavaandmvp.databinding.ActivityBaseBinding;
import com.idea.l.rxjavaandmvp.fragment.BaseFragment;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-09
 */
public abstract class AppActivity extends BaseActivity {
    private ActivityBaseBinding mBinding;
    //获取Intent
   protected void handleIntent(Intent intent){

   }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_base);
        if (null != getIntent()){
            handleIntent(getIntent());
        }
        if (null == getSupportFragmentManager().getFragments()){
            BaseFragment firstFragment = getFirstFragment();
            if (null != firstFragment){
                addFragment(firstFragment);
            }
        }
    }

    protected abstract BaseFragment getFirstFragment();

    /**
     * 设置标题名字
     * @param title
     */
    public void createTitle(String title){
        mBinding.tvTitle.setText(title);
    }
    @Override
    protected int getFragmentContentId() {
        return R.id.fragment_container;
    }
}


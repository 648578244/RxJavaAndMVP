package com.idea.l.rxjavaandmvp.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alipay.euler.andfix.annotation.MethodReplace;
import com.idea.l.rxjavaandmvp.MVPApplication;
import com.idea.l.rxjavaandmvp.MainActivity;
import com.idea.l.rxjavaandmvp.R;
import com.idea.l.rxjavaandmvp.databinding.FragmentMainBinding;

import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-09
 */
public class MainFragment extends BaseFragment implements View.OnClickListener {
    private static final String APATCH_PATH = "/fix.apatch"; // 补丁文件名
    private FragmentMainBinding mBinding;
    public static BaseFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected View getLayoutId(LayoutInflater inflater, ViewGroup container) {
        mBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_main,container,false);
        mBinding.setMainFragment(this);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_btn:
                Intent intent = new Intent(mActivity, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.hot_btn:
                    update();
                break;
            case R.id.click_btn:
                showToast();
                break;
        }
    }
    private void showToast() {
        Toast.makeText(this.getActivity(), "修复之后", Toast.LENGTH_LONG).show();
    }
    /**
     * 动态更新，加载补丁文件
     * @author zehua_chen
     * create at 2016/8/3 14:35
     */
    private void update() {
        String patchFileStr = Environment.getExternalStorageDirectory() + APATCH_PATH;
        Log.i("TAG",patchFileStr);
        try {
            MVPApplication.mPatchManager.addPatch(patchFileStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

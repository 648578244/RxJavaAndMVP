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
import com.idea.l.rxjavaandmvp.BuildConfig;
import com.idea.l.rxjavaandmvp.MVPApplication;
import com.idea.l.rxjavaandmvp.MainActivity;
import com.idea.l.rxjavaandmvp.R;
import com.idea.l.rxjavaandmvp.databinding.FragmentMainBinding;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-09
 */
public class MainFragment extends BaseFragment implements View.OnClickListener {
    private static final String APATCH_PATH_1 = "/fix_1.0.apatch"; // 补丁文件名
    private FragmentMainBinding mBinding;

    public static BaseFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected View getLayoutId(LayoutInflater inflater, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mBinding.setMainFragment(this);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn:
                Intent intent = new Intent(mActivity, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.hot_btn:
                update();
                break;
            case R.id.click_btn:
                showToast();
                mBinding.tvContent.setText(BuildConfig.VERSION_NAME);
                break;
        }
    }

    private void showToast() {
        Toast.makeText(this.getActivity(), "1.1", Toast.LENGTH_LONG).show();
    }

    /**
     * 动态更新，加载补丁文件
     *
     * @author zehua_chen
     * create at 2016/8/3 14:35
     */
    private void update() {
        String patchFileStr1 = Environment.getExternalStorageDirectory() + APATCH_PATH_1;

        try {
            Log.i("TAG", BuildConfig.VERSION_NAME);
            MVPApplication.mPatchManager.removeAllPatch();
            MVPApplication.mPatchManager.addPatch(patchFileStr1);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            MVPApplication.mPatchManager.removeAllPatch();
//            MVPApplication.mPatchManager.addPatch(patchFileStr);
////            SPUtils.save("hotfix", "hotfixID", hotfix.id);
//        } catch (Exception e) {
//            addLastApatch();
////            SPUtils.save("hotfix", "hotfixID", "0");
//            e.printStackTrace();
//        }
    }
//    private void addLastApatch() {
//        try {
//            File file = new File(getFilesDir(), "hotfix");
//            if (!file.exists()){
//                return;
//            }
//            String lastApatchPath = file.getAbsolutePath() + "/" + SPUtils.getString("hotfix", "hotfixID") + ".apatch";
//            MVPApplication.mPatchManager.addPatch(lastApatchPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}

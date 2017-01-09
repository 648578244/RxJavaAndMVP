package com.idea.l.rxjavaandmvp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.idea.l.rxjavaandmvp.adapter.ContentPagerAdapter;
import com.idea.l.rxjavaandmvp.databinding.ActivityMainBinding;
import com.idea.l.rxjavaandmvp.fragment.BaseFragment;
import com.idea.l.rxjavaandmvp.fragment.FoundFragment;
import com.idea.l.rxjavaandmvp.fragment.MainFragment;
import com.idea.l.rxjavaandmvp.fragment.SettingFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mBinding;
    private ContentPagerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(mBinding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_call:
                        mBinding.drawerLayout.closeDrawers();
                        break;
                }
                return true;
            }

        });
        mAdapter = new ContentPagerAdapter(getSupportFragmentManager(), getFragments());
        mBinding.viewpagerView.setAdapter(mAdapter);
        mBinding.viewpagerView.setCurrentItem(0, false);

    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    private ArrayList<BaseFragment> getFragments() {
        ArrayList<BaseFragment> mFragments = new ArrayList<>();
        mFragments.add(MainFragment.newInstance());
        mFragments.add(FoundFragment.newInstance());
        mFragments.add(SettingFragment.newInstance());
        return mFragments;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this, "You clicked backUp", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_delete:
                Toast.makeText(this, "You clicked delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "You clicked setting", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mBinding.drawerLayout.openDrawer(GravityCompat.START);
                break;


        }
        return true;
    }

}

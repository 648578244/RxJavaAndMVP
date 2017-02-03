package com.idea.l.rxjavaandmvp.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.idea.l.rxjavaandmvp.R;
import com.idea.l.rxjavaandmvp.adapter.FruitAdapter;
import com.idea.l.rxjavaandmvp.bean.Chapter;
import com.idea.l.rxjavaandmvp.bean.Course;
import com.idea.l.rxjavaandmvp.contract.GetUserContract;
import com.idea.l.rxjavaandmvp.databinding.FragmentFoundBinding;
import com.idea.l.rxjavaandmvp.presenter.UserPresenterImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-09
 */
public class FoundFragment extends BaseFragment implements GetUserContract.IUserView {
    private FragmentFoundBinding mBinding;
    private List<Chapter> fruitList = new ArrayList<>();
    private FruitAdapter adapter;

    public GetUserContract.IUserPresenter mUserPresenter;
    public static BaseFragment newInstance() {
        return new FoundFragment();
    }

    @Override
    protected View getLayoutId(LayoutInflater inflater, ViewGroup container) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_found, container, false);
        return mBinding.getRoot();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mUserPresenter = new UserPresenterImpl(this);
        mUserPresenter.fetchData("12345678");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        mBinding.recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitAdapter(fruitList);
        mBinding.recyclerView.setAdapter(adapter);
        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Data deleted", Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mActivity, "FAB Clicked", Toast.LENGTH_SHORT).show();

                    }
                }).show();
            }
        });
        mBinding.swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        initPullRefresh();
        initLoadMoreListener();
    }

    private void initLoadMoreListener() {
        mBinding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            List<Chapter> footerDatas = new ArrayList<>();
                            Chapter chapter = new Chapter();
                            chapter.setTitle("加载更多的数据");
                            chapter.setImageUrl("http://192.168.13.51:8080/resources/imgs/course-img.jpg");
                            footerDatas.add(chapter);
                            adapter.AddFooterItem(footerDatas);
                            //设置回到上拉加载更多
                            adapter.changeMoreStatus(adapter.PULLUP_LOAD_MORE);

                            Toast.makeText(mActivity, "更新了 " + footerDatas.size() + " 条目数据", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);


                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void initPullRefresh() {
        mBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }

            private void refreshFruits() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        FoundFragment.this.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mUserPresenter.fetchData("34567");
                                adapter.notifyDataSetChanged();
                                mBinding.swipeRefresh.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    @Override
    public void updateView(Course user) {
        fruitList.clear();
        fruitList.addAll(user.getChapterList());
        adapter.notifyDataSetChanged();
    }


}

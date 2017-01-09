package com.idea.l.rxjavaandmvp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.idea.l.rxjavaandmvp.R;
import com.idea.l.rxjavaandmvp.bean.Chapter;

import java.util.List;

/**
 * Describe:
 * User: 月月鸟
 * Date: 2017-01-03
 */
public class FruitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Chapter> mFruitList;

    public FruitAdapter(List<Chapter> fruitList) {
        this.mFruitList = fruitList;
    }

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE = 2;

    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 0;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            fruitImage = (ImageView) cardView.findViewById(R.id.fruit_iamge);
            fruitName = (TextView) cardView.findViewById(R.id.fruit_name);
            initListener(view);
        }

        private void initListener(View view) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();

        }
        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.ftuit_item, parent, false);

            return new ViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.load_more_footview_layout, parent, false);

            return new FooterViewHolder(itemView);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;

            Chapter chapter = mFruitList.get(position);
            viewHolder.fruitName.setText(chapter.getTitle());
            Glide.with(mContext).load(chapter.getImageUrl()).into(viewHolder.fruitImage);

        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            switch (mLoadMoreStatus) {
                case PULLUP_LOAD_MORE:
                    footerViewHolder.mTvLoadText.setText("加载更多...");
                    break;
                case LOADING_MORE:
                    footerViewHolder.mTvLoadText.setText("正加载更多...");
                    break;
                case NO_LOAD_MORE:
                    //隐藏加载更多
                    footerViewHolder.mLoadLayout.setVisibility(View.GONE);
                    break;
            }
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        ProgressBar mPbLoad;
        TextView mTvLoadText;
        RelativeLayout mLoadLayout;

        public FooterViewHolder(View itemView) {
            super(itemView);
            mLoadLayout = (RelativeLayout)itemView;
            mPbLoad = (ProgressBar) mLoadLayout.findViewById(R.id.pb_load);
            mTvLoadText = (TextView) mLoadLayout.findViewById(R.id.tv_load_text);
        }
    }

    @Override
    public int getItemCount() {
        return mFruitList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void AddHeaderItem(List<Chapter> items) {
        mFruitList.addAll(0, items);
        notifyDataSetChanged();
    }

    public void AddFooterItem(List<Chapter> items) {
        mFruitList.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 更新加载更多状态
     *
     * @param status
     */
    public void changeMoreStatus(int status) {
        mLoadMoreStatus = status;
        notifyDataSetChanged();
    }
}

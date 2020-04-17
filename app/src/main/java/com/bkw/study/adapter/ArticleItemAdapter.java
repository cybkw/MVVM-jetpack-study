package com.bkw.study.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bkw.study.R;
import com.bkw.study.data.home.ArticleVO;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

public class ArticleItemAdapter extends BaseQuickAdapter<ArticleVO, BaseViewHolder> {

    public ArticleItemAdapter(int layoutResId) {
        super(R.layout.item_main);
    }

    @Override
    protected void convert(BaseViewHolder holder, ArticleVO articleVO) {

        holder.setText(R.id.tv_author, articleVO.getAuthor());
        holder.setText(R.id.tv_chartName, articleVO.getSuperChapterName());
        holder.setText(R.id.tv_title, articleVO.getTitle());
        holder.setText(R.id.tv_date, articleVO.getNiceDate());
        ImageView imageView = holder.getView(R.id.iv_collect);
        if (articleVO.isCollect()) {
            imageView.setImageResource(R.drawable.article_collect);
        } else {
            imageView.setImageResource(R.drawable.article_un_collect);
        }
    }
}

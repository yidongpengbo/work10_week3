package com.example.week3_04;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week3_04.Until.NewsBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class XRecyAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
            private Context mContext;
            private List<NewsBean.DataBean> mjihe;

    public XRecyAdapter(Context context) {
        mContext = context;
        mjihe=new ArrayList <>();
    }

    /**
     * 刷新
     * @param mjihe
     */
    public void setMjihe(List <NewsBean.DataBean> mjihe) {
        this.mjihe = mjihe;
        notifyDataSetChanged();
    }

    /**
     * 加载
     * @param jihe
     */
    public void addMjihe(List <NewsBean.DataBean> jihe){
        mjihe.addAll(jihe);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view=LayoutInflater.from(mContext).inflate(R.layout.activity_adapter,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder viewHolder, int i) {
                ViewHolder holder= (ViewHolder) viewHolder;
        Glide.with(mContext).load(mjihe.get(i).getIcon()).into(holder.mImageView);
        holder.mTextPrice.setText(mjihe.get(i).getName());
        holder.mTextTitle.setText(mjihe.get(i).getCreatetime());
    }

    @Override
    public int getItemCount() {
        return mjihe.size();
    }
    class ViewHolder extends XRecyclerView.ViewHolder{
                ImageView mImageView;
                TextView mTextTitle;
                TextView mTextPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.XRecy_Image);
            mTextTitle=itemView.findViewById(R.id.XRecy_Title);
            mTextPrice=itemView.findViewById(R.id.XRecy_Price);

        }
    }
}

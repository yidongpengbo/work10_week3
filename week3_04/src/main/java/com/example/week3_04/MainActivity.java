package com.example.week3_04;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week3_04.Until.NewsBean;
import com.example.week3_04.ipresenter.IPresenterImp;
import com.example.week3_04.ivem.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {

    /**
     * 首页
     */
    private TextView mShou;
    /**
     * 切换
     */
    private TextView mSwitch;
    private XRecyclerView mXRecy;
    private XRecyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initXRecy();
    }
        int mPage;
    private void initXRecy() {
        mPage=1;
        mXRecy.setPullRefreshEnabled(true);
        mXRecy.setLoadingMoreEnabled(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        mXRecy.setLayoutManager(manager);


        mXRecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        initData();
    }
    IPresenterImp mIPresenterImp;
    private void initData() {
        String path="http://www.zhaoapi.cn/product/getCatagory";
        mIPresenterImp.startRequest(path,NewsBean.class);
    }

    private void initView() {
        mShou = (TextView) findViewById(R.id.shou);
        mSwitch = (TextView) findViewById(R.id.Switch);
        mSwitch.setOnClickListener(this);
        mXRecy = (XRecyclerView) findViewById(R.id.XRecy);
        mAdapter=new XRecyAdapter(this);
        mXRecy.setAdapter(mAdapter);
        mIPresenterImp=new IPresenterImp(this);
    }
        Boolean boo=true;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.Switch:
                initSwitch();
                break;
        }
    }

    private void initSwitch() {
        if (boo){
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            manager.setOrientation(OrientationHelper.VERTICAL);
            mXRecy.setLayoutManager(manager);
        }else {

            //线性
            LinearLayoutManager manager = new LinearLayoutManager(this);
            manager.setOrientation(OrientationHelper.VERTICAL);
            mXRecy.setLayoutManager(manager);
        }

        boo=!boo;
    }

    @Override
    public void setData(Object o) {
            if (o instanceof NewsBean){
                NewsBean newsBean=(NewsBean)o;
                    if (mPage==1){
                        mAdapter.setMjihe(newsBean.getData());
                    }else {
                        mAdapter.addMjihe(newsBean.getData());
                    }
                    mPage++;
                    mXRecy.loadMoreComplete();
                    mXRecy.refreshComplete();
            }else {
                Toast.makeText(this,"错误啦",Toast.LENGTH_LONG).show();
            }
    }
}

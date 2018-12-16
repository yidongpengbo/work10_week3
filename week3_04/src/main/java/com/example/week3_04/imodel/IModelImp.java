package com.example.week3_04.imodel;

import com.example.week3_04.Until.MyCallBack;
import com.example.week3_04.Until.NewsBean;
import com.example.week3_04.Until.OKHttpUntil;

public class IModelImp implements IModel {
    MyCallBack mMyCallBack;
    @Override
    public void requestData(String path, Class clazz, MyCallBack myCallBack) {
        this.mMyCallBack=myCallBack;
        OKHttpUntil.getInstance().getEnquene(path, clazz, new OKHttpUntil.CallBack() {
            @Override
            public void fail(Exception e) {

            }

            @Override
            public void success(Object o) {
                    if (o instanceof NewsBean){
                        NewsBean newsBean=(NewsBean)o;
                        mMyCallBack.getData(newsBean);
                    }
            }
        });
    }
}

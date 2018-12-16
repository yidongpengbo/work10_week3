package com.example.week3_04.ipresenter;

import com.example.week3_04.Until.MyCallBack;
import com.example.week3_04.Until.NewsBean;
import com.example.week3_04.imodel.IModel;
import com.example.week3_04.imodel.IModelImp;
import com.example.week3_04.ivem.IView;

public class IPresenterImp implements IPresenter{
            IView mIView;
            IModelImp mIModelImp;

    public IPresenterImp(IView IView) {
        mIView = IView;
        mIModelImp=new IModelImp();
    }

    @Override
    public void startRequest(String path, Class clazz) {
        mIModelImp.requestData(path, clazz, new MyCallBack() {
            @Override
            public void getData(Object o) {
                if (o instanceof NewsBean){
                    NewsBean newsBean=(NewsBean)o;
                    mIView.setData(newsBean);
                }
            }
        });
    }
}

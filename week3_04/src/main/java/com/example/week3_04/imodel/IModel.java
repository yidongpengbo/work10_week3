package com.example.week3_04.imodel;

import com.example.week3_04.Until.MyCallBack;

public interface IModel {
void requestData(String path, Class clazz, MyCallBack myCallBack);
}

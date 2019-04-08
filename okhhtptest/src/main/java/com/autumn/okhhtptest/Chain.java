package com.autumn.okhhtptest;

/**
 * Created by wuqi on 2019/1/8.
 */
public interface Chain {
    String request();
    String proceed(String request);
}

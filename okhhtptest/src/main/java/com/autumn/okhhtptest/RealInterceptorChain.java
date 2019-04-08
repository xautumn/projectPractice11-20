package com.autumn.okhhtptest;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.List;

/**
 * Created by wuqi on 2019/1/8.
 */
public class RealInterceptorChain implements Chain {
    private List<Interceptor> interceptors;
    private int index;
    private String request;

    public RealInterceptorChain(List<Interceptor> interceptors, int index, String request) {
        this.interceptors = interceptors;
        this.index = index;
        this.request = request;
    }

    @Override
    public String request() {
        return request;
    }

    @Override
    public String proceed(String request) {
        if (index >= interceptors.size()) return null;

        //获取下一个责任链
        RealInterceptorChain next = new RealInterceptorChain(interceptors, index+1, request);
        //System.out.println("执行  = " + next);
        // 执行当前的拦截器
        Interceptor interceptor = interceptors.get(index);

        return interceptor.interceptor(next);
    }

    @Override
    public String toString() {
        return "RealInterceptorChain{" +
                "interceptors=" + interceptors +
                ", index=" + index +
                ", request='" + request + '\'' +
                '}';
    }

}

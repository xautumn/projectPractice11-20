package com.autumn.okhhtptest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuqi on 2019/1/8.
 */
public class Main {

    public static void main(String args[]) {
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new BridgeInterceptor());
        interceptors.add(new RetryAndFollowInterceptor());
        interceptors.add(new CacheInterceptor());

        RealInterceptorChain request = new RealInterceptorChain(interceptors, 0, "request");
        request.proceed("request");
    }
}

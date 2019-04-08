package com.autumn.okhhtptest;

/**
 * Created by wuqi on 2019/1/8.
 */
public class CacheInterceptor implements Interceptor {

    @Override
    public String interceptor(Chain chain) {
        System.out.println("执行 CacheInterceptor 最后一个拦截器 返回最终数据");
        return "success";
    }
}

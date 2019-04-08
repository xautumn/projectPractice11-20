package com.autumn.okhhtptest;

/**
 * Created by wuqi on 2019/1/8.
 */
public class RetryAndFollowInterceptor implements Interceptor {
    @Override
    public String interceptor(Chain chain) {
        System.out.println("执行 RetryAndFollowInterceptor 拦截器之前代码");
        String proceed = chain.proceed(chain.request());
        System.out.println("执行 RetryAndFollowInterceptor 拦截器之后代码 得到最终数据：" + proceed);
        return proceed;
    }
}

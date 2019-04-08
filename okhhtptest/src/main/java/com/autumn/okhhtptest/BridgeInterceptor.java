package com.autumn.okhhtptest;

/**
 * Created by wuqi on 2019/1/8.
 */
public class BridgeInterceptor implements Interceptor {
    @Override
    public String interceptor(Chain chain) {
        System.out.println("执行 BridgeInterceptor 拦截器之前代码 = " + chain);
        String proceed = chain.proceed(chain.request());
        System.out.println("执行 BridgeInterceptor 拦截器之后代码 得到最终数据：" + proceed);
        return proceed;
    }
}

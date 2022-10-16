package com.poseidon.design.C48;

import com.poseidon.design.C40.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-16
 */
public class MetricsCollectorProxy {
    private MetricsCollector metricsCollector;

    public MetricsCollectorProxy() {
        this.metricsCollector = new MetricsCollector();
    }

    public Object createProxy(Object proxyObject){
        // getInterfaces()方法和Java的反射机制有关。它能够获得这个对象所实现的接口。
        Class<?>[] interfaces = proxyObject.getClass().getInterfaces();
        DynamicProxyHandler handler = new DynamicProxyHandler(proxyObject);
        // newProxyInstance，方法有三个参数：
        // loader: 用哪个类加载器去加载代理对象
        // interfaces:动态代理类需要实现的接口
        // handler:动态代理方法在执行时，会调用handler里面的invoke方法去执行
        return Proxy.newProxyInstance(proxyObject.getClass().getClassLoader(), interfaces, handler);
    }

    private class DynamicProxyHandler implements InvocationHandler {
        private Object proxyObject;

        public DynamicProxyHandler(Object proxyObject) {
            this.proxyObject = proxyObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
            long startTimestamp = System.currentTimeMillis();
            Object result = method.invoke(proxyObject, args);
            long endTimestamp = System.currentTimeMillis();
            long respTime = endTimestamp - startTimestamp;
            String apiName = proxyObject.getClass().getName() + ":" + method.getName();
            RequestInfo requestInfo = new RequestInfo(apiName, respTime, startTimestamp);
            metricsCollector.recordRequest(requestInfo);
            return  result;
        }
    }
}

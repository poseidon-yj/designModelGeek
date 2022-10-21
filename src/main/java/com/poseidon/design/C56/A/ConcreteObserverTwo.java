package com.poseidon.design.C56.A;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-21
 */
public class ConcreteObserverTwo implements Observer{
    @Override
    public void update(String msg) {
        //TODO 获取消息通知，执行业务逻辑
        System.out.println(msg);
    }
}

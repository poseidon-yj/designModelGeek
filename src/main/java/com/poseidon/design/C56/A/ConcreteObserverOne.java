package com.poseidon.design.C56.A;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-21
 */
public class ConcreteObserverOne implements Observer{

    public void update(String msg) {
        //TODO :获取消息通知，执行自己的逻辑
        System.out.println(msg);
    }
}

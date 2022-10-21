package com.poseidon.design.C56.A;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-21
 */
public class Demo {
    public static void main(String[] args) {
        Observer observer1 = new ConcreteObserverOne();
        Observer observer2 = new ConcreteObserverTwo();

        Subject subject = new ConcreteSubject();
        subject.registerObserver(observer1);
        subject.registerObserver(observer2);
        subject.notifyObservers("test");
    }
}

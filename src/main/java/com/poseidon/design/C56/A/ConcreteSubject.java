package com.poseidon.design.C56.A;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-21
 */
public class ConcreteSubject implements Subject{
    List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String msg) {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }
}

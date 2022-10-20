package com.poseidon.design.C51;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-20
 */
public class ObjectAdaptor implements ITarget{
    private Adaptee adaptee;

    public ObjectAdaptor(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void f1() {
        adaptee.fa();
    }

    @Override
    public void f2() {
        adaptee.fb();
    }

    @Override
    public void fc() {
        adaptee.fc();
    }
}

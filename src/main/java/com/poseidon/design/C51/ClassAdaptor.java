package com.poseidon.design.C51;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-20
 */
public class ClassAdaptor extends Adaptee implements ITarget{
    @Override
    public void f1() {
        super.fa();
    }

    @Override
    public void f2() {
        super.fb();
    }

    // fc已经在Adaptee中实现过了

}

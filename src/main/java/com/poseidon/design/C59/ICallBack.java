package com.poseidon.design.C59;

/**
 * 模板模式基于继承来实现，回调基于组合来实现。
 *
 * 相对于普通的函数调用来说，回调是一种双向调用关系。
 * A 类事先注册某个函数 F 到 B 类，A 类在调用 B 类的 P 函数的时候，B 类反过来调用 A 类注册给它的 F 函数。
 * 这里的 F 函数就是“回调函数”。A 调用 B，B 反过来又调用 A，这种调用机制就叫作“回调”。
 *
 * 回调可以分为同步回调和异步回调（或者延迟回调）。同步回调指在函数返回之前执行回调函数；异步回调指的是在函数返回之后执行回调函数。
 *
 * 从应用场景上来看，同步回调看起来更像模板模式，异步回调看起来更像观察者模式*/
public interface ICallBack {
    void methodToCallback();
}


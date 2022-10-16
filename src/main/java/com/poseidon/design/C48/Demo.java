package com.poseidon.design.C48;

import com.poseidon.design.C40.MetricsCollector;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-16
 *静态代理：
 * 在不改变原始类（或叫被代理类）的情况下，通过引入代理类来给原始类附加功能。一般情况下，
 * 我们让代理类和原始类实现同样的接口。但是，如果原始类并没有定义接口，并且原始类代码并不是
 * 我们开发维护的。在这种情况下，我们可以通过让代理类继承原始类的方法来实现代理模式。
 *
 * 动态代理：
 * 静态代理需要针对每个类都创建一个代理类，并且每个代理类中的代码都有点像模板式的“重复”代码，
 * 增加了维护成本和开发成本。对于静态代理存在的问题，我们可以通过动态代理来解决。我们不事先
 * 为每个原始类编写代理类，而是在运行的时候动态地创建原始类对应的代理类，然后在系统中用代理类替换掉原始类。
 */
public class Demo {
    public static void main(String[] args) {
        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
        UserController userController = (UserController) proxy.createProxy(new UserControllerImpl());
        userController.login("1342432","zhang");
        System.out.println("haha");
    }

}

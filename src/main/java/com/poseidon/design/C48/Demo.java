package com.poseidon.design.C48;

import com.poseidon.design.C40.MetricsCollector;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-16
 */
public class Demo {
    public static void main(String[] args) {
        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
        UserController userController = (UserController) proxy.createProxy(new UserControllerImpl());
        userController.login("1342432","zhang");
        System.out.println("haha");
    }

}

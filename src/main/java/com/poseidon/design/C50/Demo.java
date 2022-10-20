package com.poseidon.design.C50;

import java.io.IOException;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-20
 */
public class Demo {

    /**
     * 1. 装饰器类和原始类继承同样的父类，这样我们可以对原始类“嵌套”多个装饰器类。
     * 2. 装饰器类是对功能的增强，这也是装饰器模式应用场景的一个重要特点 */

    public static void main(String[] args) throws IOException {


        InputStream in = new FileInputStream("/user/hive/xx");
        InputStream bin = new BufferedInputStream(in);
        byte[] bytes = new byte[128];
        while (bin.read(bytes) != -1){
            //...
        }

        DataInputStream din = new DataInputStream(bin);
        int data = din.readInt();

    }

}

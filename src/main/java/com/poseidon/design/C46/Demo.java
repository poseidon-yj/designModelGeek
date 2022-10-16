package com.poseidon.design.C46;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-16
 */
public class Demo {
    public static void main(String[] args) {
        ResourcePoolConfig resourcePoolConfig = new ResourcePoolConfig.Builder()
                .setMaxIdle(10)
                .setMaxTotal(20)
                .setMinIdle(8)
                .setName("test")
                .build();
    }
}

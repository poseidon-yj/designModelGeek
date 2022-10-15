package com.poseidon.design.C41;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-15
 * 饿汉式单例模式
 */
public class IdGenerator {
    private static final IdGenerator instance = new IdGenerator();
    private AtomicLong id = new AtomicLong(0);

    public IdGenerator() {
    }

    public IdGenerator getInstance(){
        return instance;
    }

    public long getId(){
        return id.getAndIncrement();
    }
}

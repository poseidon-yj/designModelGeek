package com.poseidon.design.C41;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-15
 * 懒汉式单例模式
 */
public class IdGenerator2 {
    private AtomicLong id = new AtomicLong(0);
    private static IdGenerator2 instance;

    public IdGenerator2() {
    }

    public static synchronized IdGenerator2 getInstance(){
        //并发性能差
        if (instance == null){
            instance = new IdGenerator2();
        }
        return instance;
    }

    public long getId(){
        return id.getAndIncrement();
    }
}

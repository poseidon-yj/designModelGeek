package com.poseidon.design.C29;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-07
 */
public class RedisDistributedLock {
    public RedisDistributedLock() {
    }

    private static final RedisDistributedLock redisDistributedLock = new RedisDistributedLock();

    public static RedisDistributedLock getSingletonIntance(){
        return redisDistributedLock;
    }

    public boolean lockTransction(String id){

        return true;
    }

    public void unlockTransction(String id){

    }
}

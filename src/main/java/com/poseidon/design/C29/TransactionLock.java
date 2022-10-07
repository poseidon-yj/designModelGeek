package com.poseidon.design.C29;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-07
 */
public class TransactionLock {
    public boolean lock(String id){
        return RedisDistributedLock.getSingletonIntance().lockTransction(id);
    }

    public void unlock(String id){
        RedisDistributedLock.getSingletonIntance().unlockTransction(id);
    }
}

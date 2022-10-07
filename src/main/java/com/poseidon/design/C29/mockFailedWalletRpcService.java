package com.poseidon.design.C29;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-07
 */
public class mockFailedWalletRpcService extends WalletRpcService{
    @Override
    public String moveMoney(String id, Long buyerId, Long sellerId, Double amount){
        return null;
    }
}

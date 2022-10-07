package com.poseidon.design.C29;

import lombok.Data;

import javax.transaction.InvalidTransactionException;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-07
 */
@Data
public class Transaction {
    private String id;
    private Long buyerId;
    private Long sellerId;
    private Long productId;
    private Long orderId;
    private Long createTimestamp;
    private Double amount;
    private STATUS status;
    private String walletTransactionId;
    private WalletRpcService walletRpcService;
    private TransactionLock lock;

    public void setWalletRpcService(WalletRpcService walletRpcService){
        this.walletRpcService = walletRpcService;
    }
    public void setLock(TransactionLock lock){
        this.lock = lock;
    }
    // ...get() methods...

    public Transaction(String preAssignedId, Long buyerId, Long sellerId, Long productId, Long orderId, Double amount) {
        if (preAssignedId != null && !preAssignedId.isEmpty()) {
            this.id = preAssignedId;
        } else {
            this.id = IdGenerator.generateTransactionId();
        }
        if (!this.id.startsWith("t_")) {
            this.id = "t_" + preAssignedId;
        }
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.orderId = orderId;
        this.status = STATUS.TO_BE_EXECUTED;
        this.createTimestamp = System.currentTimeMillis();
        this.amount = amount;
    }

    public boolean isExpired(){
        long executionInvokedTimestamp = System.currentTimeMillis();
        return executionInvokedTimestamp - createTimestamp > 86400000 * 14;
    }

    public boolean isExecuted(){
        return status == STATUS.EXECUTED;
    }

    public boolean execute() throws InvalidTransactionException {
        if ((buyerId == null || (sellerId == null || amount < 0.0))) {
            throw new InvalidTransactionException("exception");
        }
        if (status == STATUS.EXECUTED) return true;
        boolean isLocked = false;
        try {
            isLocked = lock.lock(id);
            if (!isLocked) {
                return false; // 锁定未成功，返回false，job兜底执行
            }
            if (isExecuted()) return true; // double check

            if (isExpired()) {
                this.status = STATUS.EXPIRED;
                return false;
            }
            //WalletRpcService walletRpcService = new WalletRpcService();
            String walletTransactionId = walletRpcService.moveMoney(id, buyerId, sellerId, amount);
            if (walletTransactionId != null) {
                this.walletTransactionId = walletTransactionId;
                this.status = STATUS.EXECUTED;
                return true;
            } else {
                this.status = STATUS.FAILED;
                return false;
            }
        } finally {
            if (isLocked) {
                lock.unlock(id);
            }
        }
    }
}

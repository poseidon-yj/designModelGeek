package com.poseidon.design.C29;

import org.junit.Test;

import static org.junit.Assert.*;
import com.poseidon.design.C29.Transaction;

import javax.transaction.InvalidTransactionException;

public class TransactionTest {

    /**
     * 1.正常情况下，交易执行成功，回填用于对账（交易与钱包的交易流水）用的 walletTransactionId，交易状态设置为 EXECUTED，函数返回 true。
     * 2.buyerId、sellerId 为 null、amount 小于 0，返回 InvalidTransactionException。
     * 3.交易已过期（createTimestamp 超过 14 天），交易状态设置为 EXPIRED，返回 false。
     * 4.交易已经执行了（status==EXECUTED），不再重复执行转钱逻辑，返回 true。
     * 5.钱包（WalletRpcService）转钱失败，交易状态设置为 FAILED，函数返回 false。*/
    @Test
    public void testExecute() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        Double amount = 1.0;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, amount);
        transaction.setWalletRpcService(new mockWalletRpcService());
        transaction.setLock(new TransactionLock(){
            public boolean lock(String id){
                return true;
            }
            public void unlock(){}
        });
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
        assertEquals(STATUS.EXECUTED, transaction.getStatus());
    }

    //2.buyerId、sellerId 为 null、amount 小于 0，返回 InvalidTransactionException。
    @Test(expected = InvalidTransactionException.class)
    public void testInvalidTransactionExceptionExecute() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        Double amount = -1.0;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, amount);
        transaction.setWalletRpcService(new mockWalletRpcService());
        transaction.setLock(new TransactionLock(){
            //@Override
            public boolean lock(String id){
                return true;
            }
            public void unlock(){}
        });
        transaction.execute();

    }

    //3.交易已过期（createTimestamp 超过 14 天），交易状态设置为 EXPIRED，返回 false。
    @Test
    public void testExpiredExecute() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        Double amount = 1.0;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, amount){
            public boolean isExpired(){
                return true;
            }
        };
        transaction.setWalletRpcService(new mockWalletRpcService());
        transaction.setLock(new TransactionLock(){
            public boolean lock(String id){
                return true;
            }
            public void unlock(){}
        });
        boolean res = transaction.execute();
        assertFalse(res);
        assertEquals(STATUS.EXPIRED, transaction.getStatus());
    }

    //4.交易已经执行了（status==EXECUTED），不再重复执行转钱逻辑，返回 true。
    @Test
    public void testExecuted() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        Double amount = 1.0;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, amount){
            public boolean isExecuted(){return true;}
        };
        transaction.setWalletRpcService(new mockFailedWalletRpcService());
        transaction.setLock(new TransactionLock(){
            public boolean lock(String id){
                return true;
            }
            public void unlock(){}
        });
        boolean executedResult = transaction.execute();
        assertTrue(executedResult);
        assertNotEquals(STATUS.EXECUTED, transaction.getStatus());
    }

    //5.钱包（WalletRpcService）转钱失败，交易状态设置为 FAILED，函数返回 false。
    @Test
    public void testFailedExecute() throws InvalidTransactionException {
        Long buyerId = 123L;
        Long sellerId = 234L;
        Long productId = 345L;
        Long orderId = 456L;
        Double amount = 1.0;
        Transaction transaction = new Transaction(null, buyerId, sellerId, productId, orderId, amount);
        transaction.setWalletRpcService(new mockFailedWalletRpcService());
        transaction.setLock(new TransactionLock(){
            public boolean lock(String id){
                return true;
            }
            public void unlock(){}
        });
        boolean executedResult = transaction.execute();
        assertFalse(executedResult);
        assertEquals(STATUS.FAILED, transaction.getStatus());
    }
}
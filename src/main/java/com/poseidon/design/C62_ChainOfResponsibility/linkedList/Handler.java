package com.poseidon.design.C62_ChainOfResponsibility.linkedList;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-26
 */

public abstract class Handler {
    protected Handler successor = null;

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    // 模版模式 抽象出关键逻辑到父类
    public final void handle() {
        boolean handled = doHandle();
        if (successor != null && !handled) {
            successor.handle();
        }
    }

    protected abstract boolean doHandle();
}



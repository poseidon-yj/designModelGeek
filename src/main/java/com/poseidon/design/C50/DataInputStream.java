package com.poseidon.design.C50;

public class DataInputStream extends InputStream {
    protected volatile InputStream in;

    protected DataInputStream(InputStream in) {
        this.in = in;
    }

    public int readInt(){
        return  0;
    }

    //...实现读取基本类型数据的接口
}

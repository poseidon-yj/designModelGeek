package com.poseidon.design.C50;

import java.io.IOException;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-20
 */

public abstract class InputStream {
    //...
    public int read(byte b[]) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte b[], int off, int len) throws IOException {
        //...
        return 0;
    }

    public long skip(long n) throws IOException {
        //...
        return 0;
    }

    public int available() throws IOException {
        return 0;
    }

    public void close() throws IOException {}

    public synchronized void mark(int readlimit) {}

    public synchronized void reset() throws IOException {
        throw new IOException("mark/reset not supported");
    }

    public boolean markSupported() {
        return false;
    }
}


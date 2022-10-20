package com.poseidon.design.C50;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-20
 */
public class FileInputStream extends InputStream{
    String path;

    protected FileInputStream(String path){
        this.path = path;

    }
}

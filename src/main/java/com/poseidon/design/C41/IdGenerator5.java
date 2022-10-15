package com.poseidon.design.C41;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-15
 * 枚举类单例模式
 *
 * Java规范字规定，每个枚举类型及其定义的枚举变量在JVM中都是唯一的，因此在枚举类型的序列化和反序列化上，Java做了特殊的规定。
 * 在序列化的时候Java仅仅是将枚举对象的name属性输到结果中，反序列化的时候则是通过java.lang.Enum的valueOf()方法来根据
 * 名字查找枚举对象。也就是说，序列化的时候只将DATASOURCE这个名称输出，反序列化的时候再通过这个名称，查找对应的枚举类型，
 * 因此反序列化后的实例也会和之前被序列化的对象实例相同。
 */

public enum IdGenerator5 {
    INSTANCE;
    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}

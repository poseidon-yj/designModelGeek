package com.poseidon.design.C35;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomIdGeneratorTest {

    @Test
    public void getLastSubstrSplittedByDot() {
        RandomIdGenerator randomIdGenerator = new RandomIdGenerator();
        String res = randomIdGenerator.getLastSubstrSplittedByDot("haha1.haha2.haha3");
        assertEquals("haha3", res);

        res = randomIdGenerator.getLastSubstrSplittedByDot("haha1#haha2#haha3");
        assertNotEquals("haha3", res);

        res = randomIdGenerator.getLastSubstrSplittedByDot("haha3");
        assertEquals("haha3", res);
    }

    @Test
    public void generateRandomAlphameric() {
        RandomIdGenerator idGenerator = new RandomIdGenerator();
        String actualRandomString = idGenerator.generateRandomAlphameric(6);
        assertNotNull(actualRandomString);
        assertEquals(6, actualRandomString.length());
        for (char c : actualRandomString.toCharArray()) {
            assertTrue(('0' <= c && c <= '9') || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z'));
        }
    }

    /**
     * 我们再来看 generate() 函数。它依赖主机名、随机函数、时间函数，我们该如何测试呢？需要 mock 这些函数的实现吗？
     * 实际上，这要分情况来看。我们前面讲过，写单元测试的时候，测试对象是函数定义的功能，而非具体的实现逻辑。
     * 这样我们才能做到，函数的实现逻辑改变了之后，单元测试用例仍然可以工作。
     * 那 generate() 函数实现的功能是什么呢？
     * 这完全是由代码编写者自己来定义的。比如，针对同一份 generate() 函数的代码实现，我们可以有 3 种不同的功能定义，对应 3 种不同的单元测试。
     * * 如果我们把 generate() 函数的功能定义为：“生成一个随机唯一 ID”，那我们只要测试多次调用 generate() 函数生成的 ID 是否唯一即可。
     * * 如果我们把 generate() 函数的功能定义为：“生成一个只包含数字、大小写字母和中划线的唯一 ID”，那我们不仅要测试 ID 的唯一性，
     *     还要测试生成的 ID 是否只包含数字、大小写字母和中划线。
     * * 如果我们把 generate() 函数的功能定义为：“生成唯一 ID，格式为：{主机名 substr}-{时间戳}-{8 位随机数}。在主机名获取失败时，
     *     返回：null-{时间戳}-{8 位随机数}”，那我们不仅要测试 ID 的唯一性，还要测试生成的 ID 是否完全符合格式要求。
     * 总结一下，单元测试用例如何写，关键看你如何定义函数。*/
    @Test
    public void generate() {
    }
}
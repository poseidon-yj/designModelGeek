package com.poseidon.design.C34;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-07
 */

/**
 * 这段ID生成器代码存在的问题：
 * 代码质量方面：
 * 1。IdGenerator 设计成了实现类而非接口，调用者直接依赖实现而非接口，违反基于接口而非实现编程的设计思想
 * 2。把 IdGenerator 的 generate() 函数定义为静态函数，会影响使用该函数的代码的可测试性.主要表现在:不能通过传入不同的实例，改变代码的实现方式。
 * 3。代码的可读性并不好，缺乏注释
 * 功能实现方面：
 * 4。hostname未盘空，UnknownHostException未抛出，
 * 5。生成 ID 都需要获取本机名，randomAscii 的范围是 0～122，但可用数字仅包含三段子区间（0~9，a~z，A~Z），性能差
 * */
public class IdGenerator {
    private static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);

    public static String generate() {
        String id = "";
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            String[] tokens = hostName.split("\\.");
            if (tokens.length > 0) {
                hostName = tokens[tokens.length - 1];
            }
            char[] randomChars = new char[8];
            int count = 0;
            Random random = new Random();
            while (count < 8) {
                int randomAscii = random.nextInt(122);
                if (randomAscii >= 48 && randomAscii <= 57) {
                    randomChars[count] = (char)('0' + (randomAscii - 48));
                    count++;
                } else if (randomAscii >= 65 && randomAscii <= 90) {
                    randomChars[count] = (char)('A' + (randomAscii - 65));
                    count++;
                } else if (randomAscii >= 97 && randomAscii <= 122) {
                    randomChars[count] = (char)('a' + (randomAscii - 97));
                    count++;
                }
            }
            id = String.format("%s-%d-%s", hostName,
                    System.currentTimeMillis(), new String(randomChars));
        } catch (UnknownHostException e) {
            logger.warn("Failed to get the host name.", e);
        }

        return id;
    }
}

package com.poseidon.design.C35;

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang3.StringUtils;
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
 * hostName 变量不应该被重复使用，尤其当这两次使用时的含义还不同的时候；
 * 将获取 hostName 的代码抽离出来，定义为 getLastfieldOfHostName() 函数；
 * 删除代码中的魔法数，比如，57、90、97、122；
 * 将随机数生成的代码抽离出来，定义为 generateRandomAlphameric() 函数；
 * generate() 函数中的三个 if 逻辑重复了，且实现过于复杂，我们要对其进行简化；
 * 对 IdGenerator 类重命名，并且抽象出对应的接口。
 * */
public class RandomIdGenerator implements LogTraceIdGenerator {
    private static final Logger logger = LoggerFactory.getLogger(com.poseidon.design.C34.IdGenerator.class);


    /**
     * Get the local hostname and
     * extract the last field of the name string splitted by delimiter '.'.
     *
     * @return the last field of hostname. Returns null if hostname is not obtained.
     */
    private String getLastfieldOfHostName() throws UnknownHostException {
        String hostName  = InetAddress.getLocalHost().getHostName();

        return getLastSubstrSplittedByDot(hostName);
    }

    /**
     * Get the last field of {@hostName} splitted by delemiter '.'.
     *
     * @param hostname should not be null
     * @return the last field of {@hostName}. Returns empty string if {@hostName} is empty string.
     */
    @VisibleForTesting
    protected String getLastSubstrSplittedByDot(String hostname) {
        if (StringUtils.isBlank(hostname)) return null;
        String[] tokens = hostname.split("\\.");
        return tokens[tokens.length - 1];
    }

    /**
     * Generate random string which
     * only contains digits, uppercase letters and lowercase letters.
     *
     * @param length should not be less than 0
     * @return the random string. Returns empty string if {@length} is 0
     */
    @VisibleForTesting
    protected String generateRandomAlphameric(int length) {
        if (length <= 0){
            return "";
        }
        char[] randomChars = new char[length];
        int count = 0;
        Random random = new Random();
        int maxAscii = 'z';
        while (count < length) {
            int randomAscii = random.nextInt(maxAscii);
            boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
            boolean isUpper = randomAscii >= 'A' && randomAscii <= 'Z';
            boolean isLower = randomAscii >= 'a';

            if (isDigit || isLower || isUpper){
                randomChars[count] = (char) randomAscii;
                ++count;
            }
        }
        return new String(randomChars);
    }

    /**
     * Generate the random ID. The IDs may be duplicated only in extreme situation.
     *
     * @return an random ID
     */
    public  String generate() {
        String id = "";

        try {
            String subStrOfHostName = getLastfieldOfHostName();

            String randomString = generateRandomAlphameric(8);

            id = String.format("%s-%d-%s", subStrOfHostName,
                    System.currentTimeMillis(), randomString);
        } catch (UnknownHostException e) {
            logger.warn("Failed to get the host name.", e);
        }

        return id;
    }

    public static void main(String[] args) {
        LogTraceIdGenerator logTraceIdGenerator = new RandomIdGenerator();
        System.out.println(logTraceIdGenerator.generate());
    }
}

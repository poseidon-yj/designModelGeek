package com.poseidon.design.C60;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-26
 */

// 策略的定义
public interface DiscountStrategy {
    double calDiscount(Order order);
}


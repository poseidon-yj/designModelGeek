package com.poseidon.design.C60;

/**
 * @Description:
 * @Author: jiong.yu
 * @Date: Create at 2022-10-26
 */

public class OrderService {
    public double discount(Order order) {
        double discount = 0.0;
        OrderType type = order.getType();
        if (type.equals(OrderType.NORMAL)) { // 普通订单
            return new NormalDiscountStrategy().calDiscount(order);
        } else if (type.equals(OrderType.GROUPON)) { // 团购订单
            return new GrouponDiscountStrategy().calDiscount(order);
        } else if (type.equals(OrderType.PROMOTION)) { // 促销订单
            return new PromotionDiscountStrategy().calDiscount(order);
        }
        return discount;
    }
}

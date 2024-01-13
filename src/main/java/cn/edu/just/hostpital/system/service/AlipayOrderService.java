package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.model.AlipayOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.edu.just.hostpital.system.common.Result;

/**
 * <p>
 * 支付宝支付订单表 服务类
 * </p>
 *
 * @author javgo
 * @since 2024-01-13
 */
public interface AlipayOrderService extends IService<AlipayOrder> {

    /**
     * 创建支付宝订单
     */
    Result<?> createOrder();

    /**
     * 支付宝回调
     *
     * @param orderId 订单的唯一标识符
     */
    Result<?> getOrderInfo(String orderId);
}

package cn.edu.just.hostpital.system.service;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.model.AlipayOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
    Result<?> createOrder();

    /**
     * 支付宝回调
     *
     * @param orderId 订单的唯一标识符
     */
    @Transactional
    Result<?> getOrderInfo(String orderId);

    /**
     * 支付成功回调
     *
     * @param orderId   订单ID
     * @param payMethod 支付方式
     */
    @Transactional
    Result<?> paySuccess(String orderId, Integer payMethod);

    /**
     * 根据订单ID处理支付成功后的业务逻辑
     *
     * @param orderId   订单ID
     * @param payMethod 支付方式
     */
    @Transactional
    void paySuccessByOrderId(String orderId, Integer payMethod);
}

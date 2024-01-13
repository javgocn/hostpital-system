package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.service.AlipayOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 支付宝支付订单表 前端控制器
 * </p>
 *
 * @author javgo
 * @since 2024-01-13
 */
@RestController
@RequestMapping("/alipayOrder")
public class AlipayOrderController {

    @Resource
    private AlipayOrderService alipayOrderService;

    @ApiOperation("创建订单")
    @PostMapping("/createOrder")
    public Result<?> createOrder() {
        return alipayOrderService.createOrder();
    }

    @ApiOperation("获取订单信息")
    @GetMapping("/getOrderInfo")
    public Result<?> getOrderInfo(String orderId) {
        return alipayOrderService.getOrderInfo(orderId);
    }
}

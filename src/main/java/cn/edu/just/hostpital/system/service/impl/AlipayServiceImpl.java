package cn.edu.just.hostpital.system.service.impl;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.config.AlipayConfig;
import cn.edu.just.hostpital.system.enums.PayMethod;
import cn.edu.just.hostpital.system.enums.TradeStatusType;
import cn.edu.just.hostpital.system.model.AlipayOrder;
import cn.edu.just.hostpital.system.req.AliPayReq;
import cn.edu.just.hostpital.system.service.AlipayOrderService;
import cn.edu.just.hostpital.system.service.AlipayService;
import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 支付宝支付服务实现类
 *
 * @author javgo.cn
 * @date 2024/1/13
 */
@Slf4j
@Service
public class AlipayServiceImpl implements AlipayService {

    @Resource
    private AlipayConfig alipayConfig;

    @Resource
    private AlipayClient alipayClient;

    @Resource
    private AlipayOrderService alipayOrderService;

    /**
     * 电脑网站支付产品编号(固定值)
     */
    private static final String PC_PRODUCT_CODE = "FAST_INSTANT_TRADE_PAY";

    /**
     * 手机网站支付产品编号(固定值)
     */
    private static final String MOBILE_PRODUCT_CODE = "QUICK_WAP_WAY";

    /**
     * 交易结算信息
     */
    private static final String TRADE_SETTLE_INFO = "trade_settle_info";

    @Override
    public Result<?> initiatePcPayment(AliPayReq aliPayReq) {
        return initiatePayment(aliPayReq, PC_PRODUCT_CODE, "支付宝 PC 端支付请求失败", "支付宝 PC 端支付请求成功");
    }

    @Override
    public Result<?> initiateMobilePayment(AliPayReq aliPayReq) {
        return initiatePayment(aliPayReq, MOBILE_PRODUCT_CODE, "支付宝手机端支付请求失败", "支付宝手机端支付请求成功");
    }

    @Override
    public Result<?> processPaymentNotification(Map<String, String> params) {
        String result = "failure";
        boolean signVerified = false;
        try {
            // 1. 验证签名
            signVerified = AlipaySignature.rsaCheckV1(params, alipayConfig.getAlipayPublicKey(), alipayConfig.getCharset(), alipayConfig.getSignType());
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return Result.fail("支付宝支付结果通知签名验证失败");
        }
        if (signVerified) {
            // 2. 验证交易状态
            String tradeStatus = params.get("trade_status");
            if ("TRADE_SUCCESS".equals(tradeStatus)) {
                // 3. 更新订单状态
                result = "success";
                AlipayOrder alipayOrder = BeanUtil.mapToBean(params, AlipayOrder.class, true, null);
                alipayOrder.setOrderId(params.get("out_trade_no"));
                alipayOrder.setTradeStatus(TradeStatusType.TRADE_SUCCESS.getCode());
                alipayOrder.setPayMethod(PayMethod.ALIPAY.getCode());
                log.info("支付宝支付结果通知参数：{}", JSON.toJSONString(alipayOrder));
                QueryWrapper<AlipayOrder> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("order_id", alipayOrder.getOrderId());
                alipayOrderService.update(alipayOrder, queryWrapper);
                log.info("支付宝订单交易成功，交易状态：{}", tradeStatus);
                // 4.执行回调
                alipayOrderService.paySuccessByOrderId(alipayOrder.getOrderId(), alipayOrder.getPayMethod());
            } else {
                log.error("支付宝订单交易失败，交易状态：{}", tradeStatus);
            }
        } else {
            log.error("支付宝支付结果通知签名验证失败");
        }
        return result.equals("success") ? Result.success() : Result.fail("支付宝支付结果通知处理失败");
    }

    @Override
    public Result<?> queryPaymentStatus(String outTradeNo, String tradeNo) {
        // 1. 创建支付宝支付查询请求
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        // 2. 设置支付宝支付请求参数
        JSONObject bizContent = new JSONObject();
        if (StringUtils.isNotEmpty(outTradeNo)) {
            // 商户订单号
            bizContent.put("out_trade_no", outTradeNo);
        }
        if (StringUtils.isNotEmpty(tradeNo)) {
            // 支付宝交易号
            bizContent.put("trade_no", tradeNo);
        }
        // 交易结算信息
        String[] queryOptions = new String[]{TRADE_SETTLE_INFO};
        bizContent.put("query_options", queryOptions);
        alipayRequest.setBizContent(bizContent.toJSONString());
        // 3. 发起支付宝支付查询请求
        AlipayTradeQueryResponse alipayResponse = null;
        try {
            alipayResponse = alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            log.error("支付宝支付查询请求失败", e);
            return Result.fail("支付宝支付查询请求失败");
        }
        // 4. 处理支付宝支付查询结果（支付状态见 TradeStatusType）
        if (alipayResponse.isSuccess()) {
            log.info("支付宝支付查询请求成功");
            // 5.执行回调
            alipayOrderService.paySuccessByOrderId(outTradeNo, PayMethod.ALIPAY.getCode());
            return Result.success(alipayResponse.getTradeStatus());
        } else {
            log.error("支付宝支付查询请求失败");
            return Result.fail(alipayResponse.getTradeStatus(), "支付宝支付查询请求失败");
        }
    }

    /**
     * 发起支付宝支付请求（电脑网站支付和手机网站支付）
     * @param aliPayReq 支付请求参数
     * @param productCode 产品编号
     * @param failMessage 失败提示信息
     * @param successMessage 成功提示信息
     * @return Result 返回支付结果，包含支付表单或错误信息
     */
    private Result<?> initiatePayment(AliPayReq aliPayReq, String productCode, String failMessage, String successMessage) {
        // 1. 创建支付宝支付请求
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 2. 设置支付宝支付同步通知页面和异步通知地址
        setNotifyAndReturnUrl(alipayRequest);

        // 3. 设置支付宝支付请求参数
        JSONObject bizContent = constructBizContent(aliPayReq, productCode);
        alipayRequest.setBizContent(bizContent.toJSONString());

        String formHtml = null;
        try {
            formHtml = alipayClient.pageExecute(alipayRequest).getBody();
            return Result.success(formHtml);
        } catch (Exception e) {
            log.error(failMessage, e);
            return Result.fail(formHtml, failMessage);
        }
    }

    /**
     * 设置支付宝支付同步通知页面和异步通知地址
     * @param alipayRequest 支付宝支付请求
     */
    private void setNotifyAndReturnUrl(AlipayTradePagePayRequest alipayRequest) {
        // 设置同步通知页面
        if (StringUtils.isNotEmpty(alipayConfig.getReturnUrl())) {
            alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
        }
        // 设置异步通知地址
        if (StringUtils.isNotEmpty(alipayConfig.getNotifyUrl())) {
            alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        }
    }

    /**
     * 构造支付宝支付请求参数
     *
     * @param aliPayReq   支付请求参数
     * @param productCode 产品编号
     * @return JSONObject 支付宝支付请求参数
     */
    private JSONObject constructBizContent(AliPayReq aliPayReq, String productCode) {
        JSONObject bizContent = new JSONObject();
        // 订单标题（不可以使用特殊字符）
        bizContent.put("subject", aliPayReq.getSubject());
        // 商户订单号（由商家自定义的唯一订单号）
        bizContent.put("out_trade_no", aliPayReq.getOutTradeNo());
        // 订单总金额(元)，最小值为0.01
        bizContent.put("total_amount", aliPayReq.getTotalAmount());
        // 销售产品码，与支付宝签约的产品码名称（固定值）
        bizContent.put("product_code", productCode);
        return bizContent;
    }
}

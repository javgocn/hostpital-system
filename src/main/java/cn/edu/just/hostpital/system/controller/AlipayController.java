package cn.edu.just.hostpital.system.controller;

import cn.edu.just.hostpital.system.common.Result;
import cn.edu.just.hostpital.system.config.AlipayConfig;
import cn.edu.just.hostpital.system.enums.ContentType;
import cn.edu.just.hostpital.system.req.AliPayReq;
import cn.edu.just.hostpital.system.service.AlipayService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝支付控制器
 *
 * @author javgo.cn
 * @date 2024/1/13
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController {

    @Resource
    private AlipayConfig alipayConfig;

    @Resource
    private AlipayService alipayService;

    @ApiOperation("支付宝电脑网站支付")
    @GetMapping("/pcPayment")
    public void pcPayment(AliPayReq aliPayReq, HttpServletResponse response) throws IOException {
        response.setContentType(ContentType.TEXT_HTML.getContentType() + ";charset=" + alipayConfig.getCharset());
        Result<?> result = alipayService.initiatePcPayment(aliPayReq);
        response.getWriter().write(result.getData().toString());
        response.getWriter().flush();
        response.getWriter().close();
    }

    @ApiOperation("支付宝手机网站支付")
    @GetMapping("/mobilePayment")
    public void mobilePayment(AliPayReq aliPayReq, HttpServletResponse response) throws IOException {
        response.setContentType(ContentType.TEXT_HTML.getContentType() + ";charset=" + alipayConfig.getCharset());
        Result<?> result = alipayService.initiateMobilePayment(aliPayReq);
        response.getWriter().write(result.getData().toString());
        response.getWriter().flush();
        response.getWriter().close();
    }

    @ApiOperation("支付宝支付通知")
    @PostMapping("/notify")
    @ResponseBody
    public Result<?> processPaymentNotification(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        requestParams.keySet().forEach(r -> params.put(r, request.getParameter(r)));
        return alipayService.processPaymentNotification(params);
    }

    @ApiOperation("查询支付状态")
    @GetMapping("/queryPaymentStatus")
    @ResponseBody
    public Result<?> queryPaymentStatus(String outTradeNo, String tradeNo) {
        return alipayService.queryPaymentStatus(outTradeNo, tradeNo);
    }
}

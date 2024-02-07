package cn.edu.just.hostpital.system.filter;

import cn.edu.just.hostpital.system.config.properties.RequestWhiteList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * 登录检查过滤器
 *
 * @author javgo.cn
 * @date 2024/1/21
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    @Resource
    private RequestWhiteList requestWhiteList;

    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();
    private static final String ATTR_ADMIN = "admin";
    private static final String ATTR_USER = "user";
    private static final String ATTR_DOCTOR = "doctor";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        log.info("拦截到请求:{}", requestURI);

        if (isInWhiteList(requestURI)) {
            log.info("本次请求 {} 不需要处理", requestURI);
        } else if (isAuthenticated(request)) {
            log.info("用户已认证，请求 {} 被放行", requestURI);
        } else {
            log.info("用户未认证，请求 {} 被重定向到登录页面", requestURI);
            response.sendRedirect(requestWhiteList.getLoginPage());
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 判断请求是否在白名单中
     * @param requestURI 请求URI
     * @return 是否在白名单中
     */
    private boolean isInWhiteList(String requestURI) {
        return requestWhiteList.getUrls().stream().anyMatch(url -> ANT_PATH_MATCHER.match(url, requestURI));
    }

    /**
     * 判断用户是否已认证
     * @param request 请求
     * @param requestURI 请求URI
     * @return 是否已认证
     */
    private boolean isAuthenticated(HttpServletRequest request) {
        return Objects.nonNull(request.getSession().getAttribute(ATTR_ADMIN)) ||
                Objects.nonNull(request.getSession().getAttribute(ATTR_USER)) ||
                Objects.nonNull(request.getSession().getAttribute(ATTR_DOCTOR));
    }
}

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

    /**
     * Ant 风格的路径匹配器
     */
    public static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        log.info("拦截到请求:{}", requestURI);

        // 判断本次请求是否需要处理
        boolean checked = check(requestWhiteList.getUrls(), requestURI);
        if (checked) {
            log.info("本次请求 {} 不需要处理", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 判断登录状态，如果已登录则直接放行
        if (Objects.nonNull(request.getSession().getAttribute("admin")) && requestURI.equals(requestWhiteList.getAdminPage())) {
            filterChain.doFilter(request, response);
            return;
        }
        if (Objects.nonNull(request.getSession().getAttribute("user")) && requestURI.equals(requestWhiteList.getUserPage())) {
            filterChain.doFilter(request, response);
            return;
        }
        if (Objects.nonNull(request.getSession().getAttribute("doctor")) && requestURI.equals(requestWhiteList.getDoctorPage())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 未登录，重定向到登录页
        log.info("本次请求 {} 需要处理", requestURI);
        response.sendRedirect(requestWhiteList.getLoginPage());
    }

    /**
     * 检查请求是否需要处理
     *
     * @param urls       无需处理的请求（请求白名单）
     * @param requestURI 本次请求
     * @return true 需要处理，false 不需要处理
     */
    public boolean check(List<String> urls, String requestURI) {
        for (String url : urls) {
            if (ANT_PATH_MATCHER.match(url, requestURI)) {
                return true;
            }
        }
        return false;
    }
}

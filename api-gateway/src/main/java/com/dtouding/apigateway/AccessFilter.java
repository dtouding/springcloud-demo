package com.dtouding.apigateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul过滤器
 */
@Slf4j
public class AccessFilter extends ZuulFilter {

    /**
     * 返回过滤器的类型，决定过滤器在请求的哪个声明周期中执行。
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序。
     * 当请求在一个阶段存在多个过滤器时，需要根据该方法返回值来依次执行。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行。
     * 可以指定过滤器的有效范围。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        log.info("send {} request to {}.", request.getMethod(),
                request.getRequestURL().toString());
        String accessToken = request.getParameter("accessToken");
        if (StringUtils.isBlank(accessToken)) {
            log.warn("access token is empty.");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            return null;
        }
        log.info("access token ok.");
        return null;
    }
}

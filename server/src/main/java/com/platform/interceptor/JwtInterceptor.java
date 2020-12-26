package com.platform.interceptor;

import com.alibaba.fastjson.JSON;
import com.platform.VO.HttpResult;
import com.platform.constant.JwtConst;
import com.platform.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;
import java.util.Set;


@Component
public class JwtInterceptor implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String token = request.getHeader(JwtUtil.getHeaderKey());
        if (!StringUtils.isBlank(token)) {
            // 校验并解析token，如果token过期或者篡改，则会返回null
            Claims claims = JwtUtil.verifyAndGetClaimsByToken(token);
            if (claims != null) {
                // 校验通过后，设置用户信息到request里，在controller中从request域中获取用户信息
                request.setAttribute(JwtConst.UID_KEY, claims.get("uid"));
                request.setAttribute(JwtConst.RID_KEY, claims.get("rid"));
            }
        }
        System.out.println("JwtInterceptor");
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}

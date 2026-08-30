package com.zhiyu.plantsislandssystem.config;

import com.zhiyu.plantsislandssystem.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //跨域预检请求直接放行
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            if (jwtUtils.validateToken(token)){
                Long userId = jwtUtils.getUserId(token);
                request.setAttribute("userId",userId);
                return true;
            }
        }

        //未登录或Token失败
        response.setStatus(401);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("""
                {"code":401,"message":"未登录或登录已过期,请重新登录","data":null}
                """);
        return false;
    }
}

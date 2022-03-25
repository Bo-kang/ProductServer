package com.productserver.config;

import com.productserver.domain.Member;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handle)
        throws Exception{
        try {
            Member mem = (Member) req.getSession().getAttribute("member");

            if (mem == null) {
                res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Login 필요");

                return false;
            }
        }catch (Exception e){
            res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Login 필요");

            return false;
        }

        return true;
    }
}

package kr.go.egov.pungdong.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/** MVC sample: supplies view context; access control belongs to Spring Security. */
@Component
public class CmsContextInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (request.getUserPrincipal() != null) {
            request.setAttribute("cmsActor", request.getUserPrincipal().getName());
        }
        request.setAttribute("cmsRequestPath", request.getRequestURI());
        return true;
    }
}

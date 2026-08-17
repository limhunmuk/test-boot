package com.test.boot.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);
	private static final String START_TIME_ATTRIBUTE = "startTime";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		request.setAttribute(START_TIME_ATTRIBUTE, System.currentTimeMillis());
		log.info("[INTERCEPTOR] preHandle : {} {} -> {}", request.getMethod(), request.getRequestURI(), handler);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		Long startTime = (Long) request.getAttribute(START_TIME_ATTRIBUTE);
		long elapsed = startTime != null ? System.currentTimeMillis() - startTime : -1;
		log.info("[INTERCEPTOR] afterCompletion : {} {} - status={}, {}ms",
				request.getMethod(), request.getRequestURI(), response.getStatus(), elapsed);
	}

}

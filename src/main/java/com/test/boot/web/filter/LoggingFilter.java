package com.test.boot.web.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class LoggingFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		long startTime = System.currentTimeMillis();
		log.info("[FILTER] >> {} {}", request.getMethod(), request.getRequestURI());
		try {
			filterChain.doFilter(request, response);
		} finally {
			long elapsed = System.currentTimeMillis() - startTime;
			log.info("[FILTER] << {} {} - status={}, {}ms",
					request.getMethod(), request.getRequestURI(), response.getStatus(), elapsed);
		}
	}

}

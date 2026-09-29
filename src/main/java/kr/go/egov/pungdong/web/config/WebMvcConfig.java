package kr.go.egov.pungdong.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.go.egov.pungdong.web.interceptor.IpAccessInterceptor;
import kr.go.egov.pungdong.web.interceptor.LoggingInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final LoggingInterceptor loggingInterceptor;
	private final IpAccessInterceptor ipAccessInterceptor;

	public WebMvcConfig(LoggingInterceptor loggingInterceptor, IpAccessInterceptor ipAccessInterceptor) {
		this.loggingInterceptor = loggingInterceptor;
		this.ipAccessInterceptor = ipAccessInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggingInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/js/**", "/css/**", "/images/**");
		registry.addInterceptor(ipAccessInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/js/**", "/css/**", "/images/**",
						"/login", "/admin/ip-acl/**");
	}

}

package kr.go.egov.pungdong.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.go.egov.pungdong.web.interceptor.IpAccessInterceptor;
import kr.go.egov.pungdong.web.interceptor.LoggingInterceptor;
import kr.go.egov.pungdong.web.interceptor.CmsContextInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final LoggingInterceptor loggingInterceptor;
	private final IpAccessInterceptor ipAccessInterceptor;
	private final CmsContextInterceptor cmsContextInterceptor;

	public WebMvcConfig(LoggingInterceptor loggingInterceptor, IpAccessInterceptor ipAccessInterceptor,
			CmsContextInterceptor cmsContextInterceptor) {
		this.loggingInterceptor = loggingInterceptor;
		this.ipAccessInterceptor = ipAccessInterceptor;
		this.cmsContextInterceptor = cmsContextInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(cmsContextInterceptor).addPathPatterns("/admin/**");
		registry.addInterceptor(loggingInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/js/**", "/css/**", "/images/**");
		registry.addInterceptor(ipAccessInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/js/**", "/css/**", "/images/**",
						"/login", "/admin/ip-acl/**");
	}

}

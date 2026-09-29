package kr.go.egov.pungdong.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.go.egov.pungdong.domain.ipacl.service.IpAclService;
import kr.go.egov.pungdong.web.util.ClientIpUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class IpAccessInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(IpAccessInterceptor.class);

	private final IpAclService ipAclService;

	public IpAccessInterceptor(IpAclService ipAclService) {
		this.ipAclService = ipAclService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String clientIp = ClientIpUtils.resolveClientIp(request);
		if (!ipAclService.isAllowed(clientIp)) {
			log.warn("[IP-ACL] blocked access from {} to {} {}", clientIp, request.getMethod(),
					request.getRequestURI());
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			request.getRequestDispatcher("/WEB-INF/views/error/access-denied.jsp").forward(request, response);
			return false;
		}
		return true;
	}

}

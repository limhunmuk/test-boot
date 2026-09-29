package kr.go.egov.pungdong.web.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import kr.go.egov.pungdong.domain.member.service.MemberService;
import kr.go.egov.pungdong.web.util.ClientIpUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private final MemberService memberService;

	public LoginSuccessHandler(MemberService memberService) {
		this.memberService = memberService;
		setDefaultTargetUrl("/");
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		memberService.updateLastLogin(authentication.getName(), ClientIpUtils.resolveClientIp(request));
		super.onAuthenticationSuccess(request, response, authentication);
	}

}

package kr.go.egov.pungdong.web.config;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.go.egov.pungdong.domain.member.service.MemberService;
import kr.go.egov.pungdong.domain.member.vo.MemberVO;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberService memberService;

	public CustomUserDetailsService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		MemberVO member = memberService.getMemberByLoginId(loginId)
			.orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 계정입니다: " + loginId));

		List<SimpleGrantedAuthority> authorities = "ADMIN".equals(member.getRoleCd())
			? List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"))
			: List.of(new SimpleGrantedAuthority("ROLE_USER"));

		return User.builder()
			.username(member.getLoginId())
			.password(member.getPassword())
			.authorities(authorities)
			.build();
	}

}
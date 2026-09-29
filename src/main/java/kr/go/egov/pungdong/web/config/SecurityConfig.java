package kr.go.egov.pungdong.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import kr.go.egov.pungdong.domain.member.service.MemberService;
import kr.go.egov.pungdong.web.security.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public LoginSuccessHandler loginSuccessHandler(MemberService memberService) {
		return new LoginSuccessHandler(memberService);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, LoginSuccessHandler loginSuccessHandler) throws Exception {
		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/js/**", "/css/**", "/images/**").permitAll()
				.requestMatchers("/", "/login", "/notices/**", "/posts/**").permitAll()
				.requestMatchers("/mypage/**").authenticated()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.usernameParameter("loginId")
				.passwordParameter("password")
				.successHandler(loginSuccessHandler)
				.failureUrl("/login?error")
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.permitAll()
			);
		return http.build();
	}

}
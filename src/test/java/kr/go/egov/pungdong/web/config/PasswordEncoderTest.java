package kr.go.egov.pungdong.web.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

class PasswordEncoderTest {

	@Test
	void encodeAndVerifyRawPassword() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = "1111";

		String encodedPassword = passwordEncoder.encode(rawPassword);
		System.out.println("raw     : " + rawPassword);
		System.out.println("encoded : " + encodedPassword);

		assertThat(passwordEncoder.matches(rawPassword, encodedPassword)).isTrue();
	}

}
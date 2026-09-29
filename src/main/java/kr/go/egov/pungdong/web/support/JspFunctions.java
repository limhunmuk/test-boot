package kr.go.egov.pungdong.web.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class JspFunctions {

	private JspFunctions() {
	}

	public static String formatDateTime(LocalDateTime value, String pattern) {
		if (value == null) {
			return "";
		}
		return value.format(DateTimeFormatter.ofPattern(pattern));
	}

}

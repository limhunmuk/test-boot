package com.test.boot;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	private final JdbcTemplate jdbcTemplate;

	public SampleController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@GetMapping("/api/samples")
	public List<Map<String, Object>> samples() {
		return jdbcTemplate.queryForList("SELECT * FROM SAMPLE");
	}

}

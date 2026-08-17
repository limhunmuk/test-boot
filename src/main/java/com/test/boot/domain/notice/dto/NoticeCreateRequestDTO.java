package com.test.boot.domain.notice.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NoticeCreateRequestDTO {

	@NotBlank(message = "제목을 입력해주세요.")
	private String title;

	private String content;

	private boolean importantYn;

}

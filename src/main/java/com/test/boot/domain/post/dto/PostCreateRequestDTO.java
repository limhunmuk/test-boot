package com.test.boot.domain.post.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PostCreateRequestDTO {

	@NotBlank(message = "제목을 입력해주세요.")
	private String title;

	private String content;

}

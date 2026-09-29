package kr.go.egov.pungdong.domain.notice.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NoticeUpdateRequestDTO {

	@NotBlank(message = "제목을 입력해주세요.")
	private String title;

	private String content;

	private boolean importantYn;

}

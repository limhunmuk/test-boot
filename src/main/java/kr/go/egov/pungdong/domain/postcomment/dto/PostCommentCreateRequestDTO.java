package kr.go.egov.pungdong.domain.postcomment.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PostCommentCreateRequestDTO {

	@NotBlank(message = "댓글 내용을 입력해주세요.")
	private String content;

	private Long parentCommentId;

}
package kr.go.egov.pungdong.domain.member.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberUpdateRequestDTO {

	@NotBlank(message = "이름을 입력해주세요.")
	private String memNm;

	@NotBlank(message = "닉네임을 입력해주세요.")
	private String nickNm;

	private String phoneNo;

	private String addr;

	private String addrDetail;

}
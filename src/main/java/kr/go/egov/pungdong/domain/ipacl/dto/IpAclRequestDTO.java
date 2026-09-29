package kr.go.egov.pungdong.domain.ipacl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class IpAclRequestDTO {

	@NotBlank(message = "IP 주소를 입력해 주세요.")
	@Pattern(regexp = "^\\d{1,3}(\\.\\d{1,3}){3}$", message = "IPv4 형식으로 입력해 주세요. 예) 192.168.0.1")
	private String ipAddr;

	@NotBlank(message = "목록 유형을 선택해 주세요.")
	private String listType;

	private String description;

	private String useYn = "Y";

}

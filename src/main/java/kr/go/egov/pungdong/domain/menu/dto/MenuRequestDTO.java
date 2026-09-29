package kr.go.egov.pungdong.domain.menu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class MenuRequestDTO {

	@NotBlank(message = "메뉴명을 입력해주세요.")
	private String menuNm;

	@NotBlank(message = "메뉴 키를 입력해주세요.")
	private String menuKey;

	@NotBlank(message = "메뉴 유형을 선택해주세요.")
	@Pattern(regexp = "BOARD|URL", message = "메뉴 유형은 BOARD 또는 URL이어야 합니다.")
	private String menuType;

	@NotBlank(message = "URL을 입력해주세요.")
	private String url;

	@NotNull(message = "정렬 순서를 입력해주세요.")
	private Integer sortOrder;

	@Pattern(regexp = "|ADMIN|USER", message = "필요 권한은 없음, USER, ADMIN 중 하나여야 합니다.")
	private String requiredRole;

}
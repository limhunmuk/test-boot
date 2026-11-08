package kr.go.egov.pungdong.domain.menu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class MenuRequestDTO {

	@NotBlank(message = "메뉴명을 입력해주세요.")
	@Size(max = 50)
	private String menuNm;

	@NotBlank(message = "메뉴 키를 입력해주세요.")
	@Pattern(regexp = "[A-Za-z0-9_-]{1,30}", message = "메뉴 키는 영문, 숫자, _, -로 30자 이내 입력해주세요.")
	private String menuKey;

	@NotBlank(message = "메뉴 유형을 선택해주세요.")
	@Pattern(regexp = "BOARD|URL", message = "메뉴 유형은 BOARD 또는 URL이어야 합니다.")
	private String menuType;

	@NotBlank(message = "URL을 입력해주세요.")
	@Size(max = 500)
	private String url;

	@NotNull(message = "정렬 순서를 입력해주세요.")
	@Min(0)
	private Integer sortOrder;

	@Pattern(regexp = "|ADMIN|USER", message = "필요 권한은 없음, USER, ADMIN 중 하나여야 합니다.")
	private String requiredRole;

	private Long parentMenuId;
	@Size(max = 500)
	private String menuDc;
	@NotNull
	@Pattern(regexp = "Y|N")
	private String useYn = "Y";
	@NotNull
	@Pattern(regexp = "Y|N")
	private String publicYn = "Y";

}

package kr.go.egov.pungdong.domain.menu.vo;

import lombok.Data;

@Data
public class MenuVO {

	private Long menuId;
	private String menuNm;
	private String menuKey;
	private String menuType;
	private String url;
	private Integer sortOrder;
	private String requiredRole;

}
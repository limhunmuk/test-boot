package kr.go.egov.pungdong.web.advice;

import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.go.egov.pungdong.domain.menu.service.MenuService;
import kr.go.egov.pungdong.domain.menu.vo.MenuVO;

@ControllerAdvice
public class GlobalModelAdvice {

	private final MenuService menuService;

	public GlobalModelAdvice(MenuService menuService) {
		this.menuService = menuService;
	}

	@ModelAttribute("menus")
	public List<MenuVO> menus() {
		return menuService.getVisibleMenuList();
	}

}
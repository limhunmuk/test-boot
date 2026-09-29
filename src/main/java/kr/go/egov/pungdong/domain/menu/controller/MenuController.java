package kr.go.egov.pungdong.domain.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import kr.go.egov.pungdong.domain.menu.service.MenuService;
import kr.go.egov.pungdong.domain.menu.vo.MenuVO;

@Controller
public class MenuController {

	private final MenuService menuService;

	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping("/link/{menuId}")
	public String link(@PathVariable Long menuId, Model model) {
		MenuVO menu = menuService.getMenu(menuId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 메뉴입니다."));
		model.addAttribute("menu", menu);
		return "menu/link";
	}

}
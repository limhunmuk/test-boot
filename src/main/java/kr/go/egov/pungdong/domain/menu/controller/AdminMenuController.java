package kr.go.egov.pungdong.domain.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.go.egov.pungdong.domain.menu.dto.MenuRequestDTO;
import kr.go.egov.pungdong.domain.menu.service.MenuService;
import kr.go.egov.pungdong.domain.menu.vo.MenuVO;

import jakarta.validation.Valid;

@Controller
public class AdminMenuController {

	private final MenuService menuService;

	public AdminMenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping("/admin/menus")
	public String list(Model model) {
		model.addAttribute("menuList", menuService.getMenuList());
		return "admin/menu/list";
	}

	@GetMapping("/admin/menus/new")
	public String newForm(Model model) {
		MenuRequestDTO request = new MenuRequestDTO();
		request.setSortOrder(menuService.getNextSortOrder());
		model.addAttribute("menuRequest", request);
		return "admin/menu/form";
	}

	@PostMapping("/admin/menus")
	public String create(@Valid @ModelAttribute("menuRequest") MenuRequestDTO request, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/menu/form";
		}
		menuService.createMenu(request);
		return "redirect:/admin/menus";
	}

	@GetMapping("/admin/menus/{menuId}/edit")
	public String editForm(@PathVariable Long menuId, Model model) {
		MenuVO menu = menuService.getMenu(menuId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴입니다."));

		MenuRequestDTO request = new MenuRequestDTO();
		request.setMenuNm(menu.getMenuNm());
		request.setMenuKey(menu.getMenuKey());
		request.setMenuType(menu.getMenuType());
		request.setUrl(menu.getUrl());
		request.setSortOrder(menu.getSortOrder());
		request.setRequiredRole(menu.getRequiredRole());

		model.addAttribute("menuId", menuId);
		model.addAttribute("menuRequest", request);
		return "admin/menu/form";
	}

	@PostMapping("/admin/menus/{menuId}/edit")
	public String update(@PathVariable Long menuId,
			@Valid @ModelAttribute("menuRequest") MenuRequestDTO request,
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("menuId", menuId);
			return "admin/menu/form";
		}
		menuService.updateMenu(menuId, request);
		return "redirect:/admin/menus";
	}

	@PostMapping("/admin/menus/{menuId}/delete")
	public String delete(@PathVariable Long menuId) {
		menuService.deleteMenu(menuId);
		return "redirect:/admin/menus";
	}

}
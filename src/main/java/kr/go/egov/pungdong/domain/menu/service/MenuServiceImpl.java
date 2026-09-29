package kr.go.egov.pungdong.domain.menu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import kr.go.egov.pungdong.domain.menu.dto.MenuRequestDTO;
import kr.go.egov.pungdong.domain.menu.mapper.MenuMapper;
import kr.go.egov.pungdong.domain.menu.vo.MenuVO;

@Service
public class MenuServiceImpl implements MenuService {

	private final MenuMapper menuMapper;

	public MenuServiceImpl(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	@Override
	public List<MenuVO> getMenuList() {
		return menuMapper.selectMenuList();
	}

	@Override
	public List<MenuVO> getVisibleMenuList() {
		return menuMapper.selectMenuList().stream()
			.filter(this::isVisibleToCurrentUser)
			.toList();
	}

	private boolean isVisibleToCurrentUser(MenuVO menu) {
		String requiredRole = menu.getRequiredRole();
		if (requiredRole == null || requiredRole.isBlank()) {
			return true;
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return false;
		}

		String authority = "ROLE_" + requiredRole;
		return authentication.getAuthorities().stream()
			.anyMatch(granted -> granted.getAuthority().equals(authority));
	}

	@Override
	public Optional<MenuVO> getMenu(Long menuId) {
		return menuMapper.selectMenu(menuId);
	}

	@Override
	public int getNextSortOrder() {
		Integer maxSortOrder = menuMapper.selectMaxSortOrder();
		return (maxSortOrder == null ? 0 : maxSortOrder) + 1;
	}

	@Override
	public Long createMenu(MenuRequestDTO request) {
		MenuVO menu = toMenuVO(request);
		menuMapper.insertMenu(menu);
		return menu.getMenuId();
	}

	@Override
	public void updateMenu(Long menuId, MenuRequestDTO request) {
		MenuVO menu = toMenuVO(request);
		menu.setMenuId(menuId);
		menuMapper.updateMenu(menu);
	}

	@Override
	public void deleteMenu(Long menuId) {
		menuMapper.deleteMenu(menuId);
	}

	private MenuVO toMenuVO(MenuRequestDTO request) {
		MenuVO menu = new MenuVO();
		menu.setMenuNm(request.getMenuNm());
		menu.setMenuKey(request.getMenuKey());
		menu.setMenuType(request.getMenuType());
		menu.setUrl(request.getUrl());
		menu.setSortOrder(request.getSortOrder());
		menu.setRequiredRole(request.getRequiredRole());
		return menu;
	}

}
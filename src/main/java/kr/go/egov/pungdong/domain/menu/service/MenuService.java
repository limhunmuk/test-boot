package kr.go.egov.pungdong.domain.menu.service;

import java.util.List;
import java.util.Optional;

import kr.go.egov.pungdong.domain.menu.dto.MenuRequestDTO;
import kr.go.egov.pungdong.domain.menu.vo.MenuVO;

public interface MenuService {

	List<MenuVO> getMenuList();

	List<MenuVO> getVisibleMenuList();

	Optional<MenuVO> getMenu(Long menuId);

	int getNextSortOrder();

	Long createMenu(MenuRequestDTO request);

	void updateMenu(Long menuId, MenuRequestDTO request);

	void deleteMenu(Long menuId);

}
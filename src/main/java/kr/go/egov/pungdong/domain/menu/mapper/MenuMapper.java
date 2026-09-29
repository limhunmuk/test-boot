package kr.go.egov.pungdong.domain.menu.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import kr.go.egov.pungdong.domain.menu.vo.MenuVO;

@Mapper
public interface MenuMapper {

	List<MenuVO> selectMenuList();

	Optional<MenuVO> selectMenu(Long menuId);

	Integer selectMaxSortOrder();

	void insertMenu(MenuVO menu);

	void updateMenu(MenuVO menu);

	void deleteMenu(Long menuId);

}
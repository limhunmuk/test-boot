package kr.go.egov.pungdong.domain.menu;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import java.util.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import kr.go.egov.pungdong.common.mapper.CommonMapper;
import kr.go.egov.pungdong.domain.menu.dto.MenuRequestDTO;
import kr.go.egov.pungdong.domain.menu.service.MenuServiceImpl;
import kr.go.egov.pungdong.domain.menu.vo.MenuVO;

class MenuServiceTest {
    private CommonMapper mapper;
    private MenuServiceImpl service;
    @BeforeEach void setUp() {
        mapper = mock(CommonMapper.class); service = new MenuServiceImpl(mapper);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
            "admin", "", List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"))));
        when(mapper.selectOne(eq("CmsMenu.countMenuKey"), any())).thenReturn(0);
    }
    @AfterEach void cleanUp() { SecurityContextHolder.clearContext(); }
    private MenuVO menu(long id, Long parent) {
        MenuVO m = new MenuVO(); m.setMenuId(id); m.setParentMenuId(parent);
        m.setUseYn("Y"); m.setPublicYn("Y"); return m;
    }
    private MenuRequestDTO request() {
        MenuRequestDTO r = new MenuRequestDTO(); r.setMenuNm("메뉴"); r.setMenuKey("test");
        r.setMenuType("BOARD"); r.setUrl("/notices"); r.setSortOrder(1); return r;
    }
    @Test void ordersHierarchyAndHidesDescendantsOfHiddenParent() {
        MenuVO root = menu(1, null); MenuVO child = menu(2, 1L); MenuVO other = menu(3, null);
        when(mapper.<MenuVO>selectList("CmsMenu.selectMenuList", null)).thenReturn(List.of(child, root, other));
        assertEquals(List.of(1L, 2L, 3L), service.getMenuList().stream().map(MenuVO::getMenuId).toList());
        assertEquals(1, child.getDepth());
        root.setPublicYn("N");
        assertEquals(List.of(3L), service.getVisibleMenuList().stream().map(MenuVO::getMenuId).toList());
    }
    @Test void rejectsMovingRootUnderDescendant() {
        when(mapper.selectOne("CmsMenu.selectMenu", Map.of("menuId", 1L))).thenReturn(menu(1, null));
        when(mapper.selectOne("CmsMenu.selectMenu", Map.of("menuId", 2L))).thenReturn(menu(2, 1L));
        MenuRequestDTO r = request(); r.setParentMenuId(2L);
        assertThrows(IllegalArgumentException.class, () -> service.updateMenu(1L, r));
        verify(mapper, never()).update(anyString(), any());
    }
    @Test void rejectsDeletingParentWithChildren() {
        when(mapper.selectOne("CmsMenu.selectMenu", Map.of("menuId", 1L))).thenReturn(menu(1, null));
        when(mapper.selectOne("CmsMenu.countChildren", Map.of("menuId", 1L))).thenReturn(1);
        assertThrows(IllegalArgumentException.class, () -> service.deleteMenu(1L));
        verify(mapper, never()).delete(anyString(), any());
    }
    @Test void rejectsDuplicateKeyAndUnsafeUrl() {
        MenuRequestDTO r = request(); r.setUrl("javascript:alert(1)");
        assertThrows(IllegalArgumentException.class, () -> service.createMenu(r));
        r.setUrl("//example.com");
        assertThrows(IllegalArgumentException.class, () -> service.createMenu(r));
        r.setUrl("/notices");
        when(mapper.selectOne(eq("CmsMenu.countMenuKey"), any())).thenReturn(1);
        assertThrows(IllegalArgumentException.class, () -> service.createMenu(r));
        verify(mapper, never()).insert(anyString(), any());
    }
    @Test void storesAuthenticatedActor() {
        service.createMenu(request());
        verify(mapper).insert(eq("CmsMenu.insertMenu"), argThat(value ->
            value instanceof MenuVO m && "admin".equals(m.getRegId()) && "admin".equals(m.getModId())));
    }
}

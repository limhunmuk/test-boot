package kr.go.egov.pungdong.domain.menu.service;

import java.net.URI;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import kr.go.egov.pungdong.common.mapper.CommonMapper;
import kr.go.egov.pungdong.domain.menu.dto.MenuRequestDTO;
import kr.go.egov.pungdong.domain.menu.vo.MenuVO;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService {
    private static final String SQL = "CmsMenu.";
    private final CommonMapper mapper;
    public MenuServiceImpl(CommonMapper mapper) { this.mapper = mapper; }

    public List<MenuVO> getMenuList() {
        List<MenuVO> all = mapper.selectList(SQL + "selectMenuList", null);
        List<MenuVO> ordered = new ArrayList<>();
        append(all, null, 0, ordered, new HashSet<>());
        return ordered;
    }
    private void append(List<MenuVO> all, Long parent, int depth, List<MenuVO> result, Set<Long> visited) {
        for (MenuVO menu : all) {
            if (Objects.equals(menu.getParentMenuId(), parent) && visited.add(menu.getMenuId())) {
                menu.setDepth(depth); result.add(menu);
                append(all, menu.getMenuId(), depth + 1, result, visited);
            }
        }
    }
    public List<MenuVO> getVisibleMenuList() {
        List<MenuVO> result = new ArrayList<>();
        Set<Long> visible = new HashSet<>();
        var auth = SecurityContextHolder.getContext().getAuthentication();
        for (MenuVO menu : getMenuList()) {
            String role = menu.getRequiredRole();
            boolean allowed = role == null || role.isBlank() || (auth != null && auth.isAuthenticated()
                && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_" + role)));
            if (allowed && "Y".equals(menu.getUseYn()) && "Y".equals(menu.getPublicYn())
                    && (menu.getParentMenuId() == null || visible.contains(menu.getParentMenuId()))) {
                result.add(menu); visible.add(menu.getMenuId());
            }
        }
        return result;
    }
    public Optional<MenuVO> getMenu(Long id) {
        return Optional.ofNullable(mapper.selectOne(SQL + "selectMenu", Map.of("menuId", id)));
    }
    public int getNextSortOrder() {
        Integer max = mapper.selectOne(SQL + "selectMaxSortOrder", null);
        return (max == null ? 0 : max) + 1;
    }
    @Transactional
    public Long createMenu(MenuRequestDTO request) {
        validate(null, request);
        MenuVO menu = convert(request);
        mapper.insert(SQL + "insertMenu", menu);
        return menu.getMenuId();
    }
    @Transactional
    public void updateMenu(Long id, MenuRequestDTO request) {
        require(id); validate(id, request);
        MenuVO menu = convert(request); menu.setMenuId(id);
        mapper.update(SQL + "updateMenu", menu);
    }
    @Transactional
    public void deleteMenu(Long id) {
        require(id);
        Integer children = mapper.selectOne(SQL + "countChildren", Map.of("menuId", id));
        if (children > 0) throw new IllegalArgumentException("하위 메뉴를 먼저 삭제하거나 이동해주세요.");
        mapper.delete(SQL + "deleteMenu", Map.of("menuId", id));
    }
    private void require(Long id) {
        getMenu(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 메뉴입니다."));
    }
    private void validate(Long id, MenuRequestDTO request) {
        Map<String, Object> params = new HashMap<>();
        params.put("menuKey", request.getMenuKey()); params.put("menuId", id);
        Integer count = mapper.selectOne(SQL + "countMenuKey", params);
        if (count > 0) throw new IllegalArgumentException("이미 사용 중인 메뉴 키입니다.");
        Set<Long> visited = new HashSet<>();
        Long parent = request.getParentMenuId();
        while (parent != null) {
            if (parent.equals(id) || !visited.add(parent))
                throw new IllegalArgumentException("자신 또는 하위 메뉴를 상위 메뉴로 지정할 수 없습니다.");
            MenuVO ancestor = getMenu(parent).orElseThrow(() -> new IllegalArgumentException("상위 메뉴가 존재하지 않습니다."));
            parent = ancestor.getParentMenuId();
        }
        try {
            String url = request.getUrl(); URI uri = URI.create(url);
            boolean internal = url.startsWith("/") && !url.startsWith("//") && !url.contains("\\") && uri.getHost() == null;
            boolean external = "URL".equals(request.getMenuType()) && uri.getHost() != null
                && ("https".equalsIgnoreCase(uri.getScheme()) || "http".equalsIgnoreCase(uri.getScheme())) && uri.getUserInfo() == null;
            if (!internal && !external) throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("내부 경로(/...) 또는 URL 유형의 http/https 주소를 입력해주세요.");
        }
    }
    private MenuVO convert(MenuRequestDTO r) {
        MenuVO m = new MenuVO();
        m.setMenuNm(r.getMenuNm()); m.setMenuKey(r.getMenuKey()); m.setMenuType(r.getMenuType());
        m.setUrl(r.getUrl()); m.setSortOrder(r.getSortOrder()); m.setRequiredRole(r.getRequiredRole());
        m.setParentMenuId(r.getParentMenuId()); m.setMenuDc(r.getMenuDc()); m.setUseYn(r.getUseYn()); m.setPublicYn(r.getPublicYn());
        String actor = SecurityContextHolder.getContext().getAuthentication().getName();
        m.setRegId(actor); m.setModId(actor); return m;
    }
}

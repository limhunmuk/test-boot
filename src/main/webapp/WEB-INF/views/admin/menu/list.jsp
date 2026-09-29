<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="메뉴 관리" active="">
	<style>
		:root {
			--border: #e2e2e2;
			--muted: #888;
			--accent: #2b6cb0;
			--danger: #c0392b;
		}
		body {
			font-family: -apple-system, "Apple SD Gothic Neo", "Segoe UI", sans-serif;
			margin: 0;
			background: #fafafa;
			color: #222;
		}
		.wrap { max-width: 860px; margin: 0 auto; padding: 40px 24px; }
		.header-row { display: flex; align-items: flex-end; justify-content: space-between; margin-bottom: 20px; }
		h1 { font-size: 22px; margin: 0; }
		.write-btn {
			display: inline-block;
			padding: 8px 16px;
			background: var(--accent);
			color: #fff;
			border-radius: 4px;
			text-decoration: none;
			font-size: 13px;
		}
		.write-btn:hover { background: #235a92; }
		table {
			border-collapse: collapse;
			width: 100%;
			background: #fff;
			box-shadow: 0 1px 3px rgba(0,0,0,0.06);
		}
		th, td { border-bottom: 1px solid var(--border); padding: 10px 14px; text-align: left; font-size: 14px; }
		th { background: #f5f6f8; color: #555; font-weight: 600; }
		td.num, th.num { width: 50px; color: var(--muted); }
		td.type, th.type { width: 90px; color: var(--muted); }
		td.order, th.order { width: 70px; color: var(--muted); }
		td.actions, th.actions { width: 130px; }
		tr:hover td { background: #fbfcff; }
		.type-badge {
			display: inline-block;
			font-size: 11px;
			padding: 2px 6px;
			border-radius: 3px;
			background: #eef2f8;
			color: var(--accent);
		}
		.actions form { display: inline; margin: 0; }
		.actions a, .actions button {
			font-size: 12px;
			padding: 4px 10px;
			border-radius: 4px;
			text-decoration: none;
			border: 1px solid var(--border);
			background: #fff;
			cursor: pointer;
			font-family: inherit;
		}
		.actions a { color: #444; margin-right: 4px; }
		.actions a:hover { background: #eef2f8; }
		.actions button { color: var(--danger); }
		.actions button:hover { background: #fdecea; border-color: var(--danger); }
		.empty { color: var(--muted); padding: 24px; text-align: center; background: #fff; }
	</style>
	<div class="wrap">
		<div class="header-row">
			<h1>메뉴 관리</h1>
			<a class="write-btn" href="<c:url value="/admin/menus/new"/>">메뉴 추가</a>
		</div>

		<c:if test="${not empty menuList}">
			<table>
				<thead>
					<tr>
						<th class="num">ID</th>
						<th>메뉴명</th>
						<th>메뉴 키</th>
						<th class="type">유형</th>
						<th>URL</th>
						<th class="order">순서</th>
						<th class="type">권한</th>
						<th class="actions">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${menuList}" var="menu">
						<tr>
							<td class="num"><c:out value="${menu.menuId}"/></td>
							<td><c:out value="${menu.menuNm}"/></td>
							<td><c:out value="${menu.menuKey}"/></td>
							<td class="type"><span class="type-badge"><c:out value="${menu.menuType}"/></span></td>
							<td><c:out value="${menu.url}"/></td>
							<td class="order"><c:out value="${menu.sortOrder}"/></td>
							<td class="type">${empty menu.requiredRole ? '전체' : menu.requiredRole}</td>
							<td class="actions">
								<a href="<c:url value="/admin/menus/${menu.menuId}/edit"/>">수정</a>
								<form action="<c:url value="/admin/menus/${menu.menuId}/delete"/>" method="post"
									  onsubmit="return confirm('삭제하시겠습니까?');">
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									<button type="submit">삭제</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

		<c:if test="${empty menuList}">
			<p class="empty">등록된 메뉴가 없습니다.</p>
		</c:if>
	</div>
</t:layout>

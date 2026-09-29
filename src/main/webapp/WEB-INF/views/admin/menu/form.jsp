<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="${empty menuId ? '메뉴 추가' : '메뉴 수정'}" active="">
	<style>
		:root {
			--border: #e2e2e2;
			--muted: #888;
			--accent: #2b6cb0;
			--error: #c0392b;
		}
		body {
			font-family: -apple-system, "Apple SD Gothic Neo", "Segoe UI", sans-serif;
			margin: 0;
			background: #fafafa;
			color: #222;
		}
		.wrap { max-width: 480px; margin: 0 auto; padding: 40px 24px; }
		h1 { font-size: 20px; margin: 0 0 20px; }
		.card {
			background: #fff;
			box-shadow: 0 1px 3px rgba(0,0,0,0.06);
			border-radius: 4px;
			padding: 28px;
		}
		.field { margin-bottom: 18px; }
		.field label { display: block; font-size: 13px; color: var(--muted); margin-bottom: 6px; }
		.field input[type="text"], .field input[type="number"], .field select {
			width: 100%;
			box-sizing: border-box;
			padding: 10px 12px;
			border: 1px solid var(--border);
			border-radius: 4px;
			font-size: 14px;
			font-family: inherit;
		}
		.field input:focus, .field select:focus { outline: none; border-color: var(--accent); }
		.error { color: var(--error); font-size: 12px; margin: 6px 0 0; }
		.actions { display: flex; gap: 8px; margin-top: 24px; }
		.actions button, .actions a {
			padding: 9px 18px;
			border-radius: 4px;
			font-size: 14px;
			text-decoration: none;
			cursor: pointer;
			border: none;
		}
		.actions button[type="submit"] { background: var(--accent); color: #fff; }
		.actions button[type="submit"]:hover { background: #235a92; }
		.actions a { border: 1px solid var(--border); color: #444; background: #fff; }
		.actions a:hover { background: #eef2f8; }
	</style>
	<div class="wrap">
		<h1>${empty menuId ? '메뉴 추가' : '메뉴 수정'}</h1>
		<div class="card">
			<c:choose>
				<c:when test="${not empty menuId}">
					<c:url value="/admin/menus/${menuId}/edit" var="formAction"/>
				</c:when>
				<c:otherwise>
					<c:url value="/admin/menus" var="formAction"/>
				</c:otherwise>
			</c:choose>
			<form:form action="${formAction}" modelAttribute="menuRequest" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="field">
					<label for="menuNm">메뉴명</label>
					<form:input id="menuNm" path="menuNm"/>
					<form:errors path="menuNm" element="p" cssClass="error"/>
				</div>
				<div class="field">
					<label for="menuKey">메뉴 키</label>
					<form:input id="menuKey" path="menuKey"/>
					<form:errors path="menuKey" element="p" cssClass="error"/>
				</div>
				<div class="field">
					<label for="menuType">메뉴 유형</label>
					<form:select id="menuType" path="menuType">
						<form:option value="BOARD">BOARD (내부 페이지)</form:option>
						<form:option value="URL">URL (외부/퍼블리싱 화면)</form:option>
					</form:select>
					<form:errors path="menuType" element="p" cssClass="error"/>
				</div>
				<div class="field">
					<label for="url">URL</label>
					<form:input id="url" path="url" placeholder="/notices 또는 https://..."/>
					<form:errors path="url" element="p" cssClass="error"/>
				</div>
				<div class="field">
					<label for="sortOrder">정렬 순서</label>
					<form:input id="sortOrder" path="sortOrder" type="number"/>
					<form:errors path="sortOrder" element="p" cssClass="error"/>
				</div>
				<div class="field">
					<label for="requiredRole">필요 권한</label>
					<form:select id="requiredRole" path="requiredRole">
						<form:option value="">없음 (전체 공개)</form:option>
						<form:option value="USER">USER (로그인 사용자)</form:option>
						<form:option value="ADMIN">ADMIN (관리자)</form:option>
					</form:select>
					<form:errors path="requiredRole" element="p" cssClass="error"/>
				</div>

				<div class="actions">
					<button type="submit">저장</button>
					<a href="<c:url value="/admin/menus"/>">취소</a>
				</div>
			</form:form>
		</div>
	</div>
</t:layout>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="${empty aclId ? 'IP 추가' : 'IP 수정'}" active="">
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
		.field input[type="text"], .field select {
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
		<h1>${empty aclId ? 'IP 추가' : 'IP 수정'}</h1>
		<div class="card">
			<c:choose>
				<c:when test="${not empty aclId}">
					<c:url value="/admin/ip-acl/${aclId}/edit" var="formAction"/>
				</c:when>
				<c:otherwise>
					<c:url value="/admin/ip-acl" var="formAction"/>
				</c:otherwise>
			</c:choose>
			<form:form action="${formAction}" modelAttribute="ipAclRequest" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="field">
					<label for="ipAddr">IP 주소</label>
					<form:input id="ipAddr" path="ipAddr" placeholder="예) 211.114.22.74"/>
					<form:errors path="ipAddr" element="p" cssClass="error"/>
				</div>
				<div class="field">
					<label for="listType">목록 유형</label>
					<form:select id="listType" path="listType">
						<form:option value="BLACKLIST">BLACKLIST (차단)</form:option>
						<form:option value="WHITELIST">WHITELIST (허용)</form:option>
					</form:select>
					<form:errors path="listType" element="p" cssClass="error"/>
				</div>
				<div class="field">
					<label for="description">설명</label>
					<form:input id="description" path="description" placeholder="등록 사유 등"/>
				</div>
				<div class="field">
					<label for="useYn">사용 여부</label>
					<form:select id="useYn" path="useYn">
						<form:option value="Y">사용</form:option>
						<form:option value="N">미사용</form:option>
					</form:select>
				</div>

				<div class="actions">
					<button type="submit">저장</button>
					<a href="<c:url value="/admin/ip-acl"/>">취소</a>
				</div>
			</form:form>
		</div>
	</div>
</t:layout>

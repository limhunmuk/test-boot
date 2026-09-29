<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="IP 접근 관리" active="">
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
		.policy-card {
			background: #fff;
			box-shadow: 0 1px 3px rgba(0,0,0,0.06);
			border-radius: 4px;
			padding: 18px 20px;
			margin-bottom: 20px;
			display: flex;
			align-items: center;
			gap: 12px;
			font-size: 14px;
		}
		.policy-card select {
			padding: 6px 10px;
			border: 1px solid var(--border);
			border-radius: 4px;
			font-size: 13px;
		}
		.policy-card button {
			padding: 6px 14px;
			border-radius: 4px;
			border: 1px solid var(--border);
			background: #fff;
			cursor: pointer;
			font-size: 13px;
		}
		.policy-card button:hover { background: #eef2f8; }
		.policy-note { color: var(--muted); font-size: 12px; }
		table {
			border-collapse: collapse;
			width: 100%;
			background: #fff;
			box-shadow: 0 1px 3px rgba(0,0,0,0.06);
		}
		th, td { border-bottom: 1px solid var(--border); padding: 10px 14px; text-align: left; font-size: 14px; }
		th { background: #f5f6f8; color: #555; font-weight: 600; }
		td.num, th.num { width: 50px; color: var(--muted); }
		td.type, th.type { width: 100px; color: var(--muted); }
		td.use, th.use { width: 70px; color: var(--muted); }
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
		.type-badge.blacklist { background: #fdecea; color: var(--danger); }
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
			<h1>IP 접근 관리</h1>
			<a class="write-btn" href="<c:url value="/admin/ip-acl/new"/>">IP 추가</a>
		</div>

		<div class="policy-card">
			<form action="<c:url value="/admin/ip-acl/policy"/>" method="post" style="display:flex; align-items:center; gap:10px;">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<strong>정책 모드</strong>
				<select name="policyMode">
					<option value="BLACKLIST" ${policyMode == 'BLACKLIST' ? 'selected' : ''}>BLACKLIST (기본 허용, 등록된 IP만 차단)</option>
					<option value="WHITELIST" ${policyMode == 'WHITELIST' ? 'selected' : ''}>WHITELIST (기본 차단, 등록된 IP만 허용)</option>
				</select>
				<button type="submit">적용</button>
			</form>
			<span class="policy-note">현재 모드에서는 목록 유형이 <c:out value="${policyMode}"/> 인 항목만 실제로 사용됩니다.</span>
		</div>

		<c:if test="${not empty ipAclList}">
			<table>
				<thead>
					<tr>
						<th class="num">ID</th>
						<th>IP 주소</th>
						<th class="type">유형</th>
						<th>설명</th>
						<th class="use">사용</th>
						<th class="actions">관리</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ipAclList}" var="acl">
						<tr>
							<td class="num"><c:out value="${acl.aclId}"/></td>
							<td><c:out value="${acl.ipAddr}"/></td>
							<td class="type">
								<span class="type-badge ${acl.listType == 'BLACKLIST' ? 'blacklist' : ''}"><c:out value="${acl.listType}"/></span>
							</td>
							<td><c:out value="${acl.description}"/></td>
							<td class="use">${acl.useYn == 'Y' ? 'Y' : 'N'}</td>
							<td class="actions">
								<a href="<c:url value="/admin/ip-acl/${acl.aclId}/edit"/>">수정</a>
								<form action="<c:url value="/admin/ip-acl/${acl.aclId}/delete"/>" method="post"
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

		<c:if test="${empty ipAclList}">
			<p class="empty">등록된 IP가 없습니다.</p>
		</c:if>
	</div>
</t:layout>

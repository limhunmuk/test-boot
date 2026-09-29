<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="내 정보 수정" active="">
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
		.field input[type="text"] {
			width: 100%;
			box-sizing: border-box;
			padding: 10px 12px;
			border: 1px solid var(--border);
			border-radius: 4px;
			font-size: 14px;
			font-family: inherit;
		}
		.field input:focus { outline: none; border-color: var(--accent); }
		.field .static-value { font-size: 14px; color: var(--muted); padding: 10px 0; }
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
		<h1>내 정보 수정</h1>
		<div class="card">
			<c:url value="/mypage/edit" var="formAction"/>
			<form:form action="${formAction}" modelAttribute="memberUpdateRequest" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<div class="field">
					<label>아이디</label>
					<p class="static-value"><c:out value="${member.loginId}"/></p>
				</div>
				<div class="field">
					<label for="memNm">이름</label>
					<form:input id="memNm" path="memNm"/>
					<form:errors path="memNm" element="p" cssClass="error"/>
				</div>
				<div class="field">
					<label for="nickNm">닉네임</label>
					<form:input id="nickNm" path="nickNm"/>
					<form:errors path="nickNm" element="p" cssClass="error"/>
				</div>
				<div class="field">
					<label for="phoneNo">연락처</label>
					<form:input id="phoneNo" path="phoneNo"/>
				</div>
				<div class="field">
					<label for="addr">주소</label>
					<form:input id="addr" path="addr"/>
				</div>
				<div class="field">
					<label for="addrDetail">상세주소</label>
					<form:input id="addrDetail" path="addrDetail"/>
				</div>

				<div class="actions">
					<button type="submit">저장</button>
					<a href="<c:url value="/mypage"/>">취소</a>
				</div>
			</form:form>
		</div>
	</div>
</t:layout>

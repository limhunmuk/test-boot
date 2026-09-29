<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="로그인" active="">
	<style>
		body {
			font-family: -apple-system, "Apple SD Gothic Neo", "Segoe UI", sans-serif;
			margin: 0;
			background: #fafafa;
			color: #222;
		}
		.wrap { max-width: 360px; margin: 0 auto; padding: 100px 24px; }
		.wrap h1 { font-size: 20px; text-align: center; margin-bottom: 24px; }
		.wrap .error { color: #c0392b; font-size: 13px; text-align: center; margin-bottom: 16px; }
		.wrap form { display: flex; flex-direction: column; gap: 12px; }
		.wrap input {
			padding: 10px 12px;
			border: 1px solid #ddd;
			border-radius: 4px;
			font-size: 14px;
		}
		.wrap button {
			padding: 10px 12px;
			border: none;
			border-radius: 4px;
			background: #2b6cb0;
			color: #fff;
			font-size: 14px;
			cursor: pointer;
		}
		.wrap .back { display: block; text-align: center; margin-top: 16px; color: #888; text-decoration: none; font-size: 13px; }
	</style>

	<div class="wrap">
		<h1>로그인</h1>
		<c:if test="${not empty paramValues.error}">
			<p class="error">로그인 아이디 또는 비밀번호가 올바르지 않습니다.</p>
		</c:if>
		<form action="<c:url value="/login"/>" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="text" name="loginId" placeholder="아이디" required autofocus>
			<input type="password" name="password" placeholder="비밀번호" required>
			<button type="submit">로그인</button>
		</form>
		<a class="back" href="<c:url value="/"/>">← 홈으로</a>
	</div>
</t:layout>

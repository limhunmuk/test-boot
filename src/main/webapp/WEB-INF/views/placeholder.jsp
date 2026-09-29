<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="준비 중" active="">
	<style>
		body {
			font-family: -apple-system, "Apple SD Gothic Neo", "Segoe UI", sans-serif;
			margin: 0;
			background: #fafafa;
			color: #222;
		}
		.wrap { max-width: 640px; margin: 0 auto; padding: 100px 24px; text-align: center; }
		.wrap p { color: #888; margin-bottom: 24px; }
		.wrap a { color: #2b6cb0; text-decoration: none; font-size: 14px; }
	</style>

	<div class="wrap">
		<p><c:out value="${message}"/></p>
		<a href="<c:url value="/"/>">← 홈으로</a>
	</div>
</t:layout>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="접근 제한" active="">
	<style>
		.deny-wrap {
			max-width: 480px;
			margin: 100px auto;
			padding: 0 24px;
			text-align: center;
			font-family: -apple-system, "Apple SD Gothic Neo", "Segoe UI", sans-serif;
			color: #222;
		}
		.deny-wrap h1 { font-size: 26px; margin-bottom: 12px; }
		.deny-wrap p { color: #888; font-size: 14px; line-height: 1.6; }
	</style>
	<div class="deny-wrap">
		<h1>접근할 수 없습니다</h1>
		<p>현재 접속 중인 IP는 접근이 제한되어 있습니다.<br>관리자에게 문의해 주세요.</p>
	</div>
</t:layout>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<t:layout title="${menu.menuNm}" active="${menu.menuKey}">
	<style>
		body { margin: 0; }
		.frame-wrap { width: 100%; height: calc(100vh - 52px); }
		.frame-wrap iframe { width: 100%; height: 100%; border: none; }
	</style>
	<div class="frame-wrap">
		<iframe src="<c:out value='${menu.url}'/>" title="<c:out value='${menu.menuNm}'/>"></iframe>
	</div>
</t:layout>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:layout title="${menu.menuNm}" active="${menu.menuKey}">
	<style>
		body { margin: 0; }
		.frame-wrap { width: 100%; height: calc(100vh - 52px); }
		.frame-wrap iframe { width: 100%; height: 100%; border: none; }
	</style>
	<div class="frame-wrap">
		<iframe src="${menu.url}" title="${menu.menuNm}"></iframe>
	</div>
</t:layout>

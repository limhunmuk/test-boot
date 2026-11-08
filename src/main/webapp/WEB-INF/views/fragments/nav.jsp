<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<nav class="site-nav">
	<div class="nav-inner">
		<a class="brand" href="<c:url value="/"/>">TEST-BOOT</a>
		<ul class="nav-links"><t:menuTree menus="${menus}" active="${param.active}"/></ul>
		<div class="nav-auth">
			<sec:authorize access="!isAuthenticated()">
				<a href="<c:url value="/login"/>">로그인</a>
			</sec:authorize>
			<sec:authorize access="hasRole('ADMIN')">
				<a href="<c:url value="/admin/menus"/>">메뉴 관리</a>
				<a href="<c:url value="/admin/ip-acl"/>">IP 접근 관리</a>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<a href="<c:url value="/mypage"/>">내정보</a>
				<form action="<c:url value="/logout"/>" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button type="submit" class="link-button">로그아웃</button>
				</form>
			</sec:authorize>
		</div>
	</div>
</nav>

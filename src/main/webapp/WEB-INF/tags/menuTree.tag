<%@ tag pageEncoding="UTF-8" %>
<%@ attribute name="menus" required="true" type="java.util.List" %>
<%@ attribute name="parentId" required="false" type="java.lang.Long" %>
<%@ attribute name="active" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<c:forEach items="${menus}" var="item">
    <c:if test="${item.parentMenuId == parentId}">
        <li>
            <c:choose>
                <c:when test="${item.menuType == 'URL'}"><c:url value="/link/${item.menuId}" var="href"/></c:when>
                <c:otherwise><c:url value="${item.url}" var="href"/></c:otherwise>
            </c:choose>
            <a href="<c:out value='${href}'/>" class="${active == item.menuKey ? 'active' : ''}"><c:out value="${item.menuNm}"/></a>
            <c:set var="hasChildren" value="false"/>
            <c:forEach items="${menus}" var="child"><c:if test="${child.parentMenuId == item.menuId}"><c:set var="hasChildren" value="true"/></c:if></c:forEach>
            <c:if test="${hasChildren}"><ul><t:menuTree menus="${menus}" parentId="${item.menuId}" active="${active}"/></ul></c:if>
        </li>
    </c:if>
</c:forEach>

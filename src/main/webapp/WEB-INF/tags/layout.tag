<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="active" required="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${empty title ? 'TEST-BOOT' : title}</title>
	<link rel="stylesheet" href="<c:url value="/css/nav.css"/>">
</head>
<body>
	<jsp:include page="/WEB-INF/views/fragments/nav.jsp">
		<jsp:param name="active" value="${active}"/>
	</jsp:include>
	<jsp:doBody/>
	<jsp:include page="/WEB-INF/views/fragments/footer.jsp"/>
</body>
</html>

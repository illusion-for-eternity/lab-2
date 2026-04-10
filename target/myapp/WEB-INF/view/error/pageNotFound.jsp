<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>

<h2>System Error</h2>
<hr>

<h3>Something went wrong</h3>

<c:if test="${errorMessage != null}">
    <div style="color:red; font-size:18px; margin-top:20px;">
            ${errorMessage}
    </div>
</c:if>

<br/>

<a href="${pageContext.request.contextPath}/app/books">
    Back to books
</a>

</body>
</html>
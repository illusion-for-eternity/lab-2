<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ua.kpi.utils.PathsHolder" %>
<%@ page import="ua.kpi.utils.AttributesHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Books</title>
    <!-- FONTS -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
            href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap"
            rel="stylesheet"
    />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style1.css" />
</head>
<body>
<div class="container">
    <header>
        <h1>Library</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
            <a href="${pageContext.request.contextPath}${PathsHolder.BOOKS}">All Books</a>
            <a href="${pageContext.request.contextPath}${PathsHolder.ADD_BOOK}">Details / Add Book</a>
        </nav>
    </header>

    <h2>Books</h2>

    <a class="center-link" href="${pageContext.request.contextPath}${PathsHolder.ADD_BOOK}">
        Add Book
    </a>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Reader</th>
            <th>Actions</th>
        </tr>

        <c:forEach var="book" items="${requestScope[AttributesHolder.BOOKS]}">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.genre}</td>
                <td>
                    <c:if test="${book.takenByreader != null}">
                        ${book.takenByreader.id}
                    </c:if>
                </td>

                <td>
                    <a href="${pageContext.request.contextPath}${PathsHolder.EDIT_BOOK}/${book.id}">
                        Edit
                    </a>

                    <form method="post"
                          action="${pageContext.request.contextPath}${PathsHolder.DELETE_BOOK}/${book.id}"
                          style="display:inline">
                        <button class="btn-delete" type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>
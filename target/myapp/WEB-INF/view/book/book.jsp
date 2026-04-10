<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ua.kpi.utils.AttributesHolder" %>
<%@ page import="ua.kpi.utils.PathsHolder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Book</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style1.css?v=2" />
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

    <h2>
        <c:choose>
            <c:when test="${book.id != null}">
                Edit Book
            </c:when>
            <c:otherwise>
                Add Book
            </c:otherwise>
        </c:choose>
    </h2>

    <div class="form-wrapper">
        <p class="form-note">
            Fill in the information about the book
        </p>

        <form method="post"
              action="${pageContext.request.contextPath}${book.id == null ? PathsHolder.ADD_BOOK : PathsHolder.EDIT_BOOK}">

            <input type="hidden" name="${AttributesHolder.ID}" value="${book.id}" />

            <div class="form-group">
                <label class="form-label" for="title">Title</label>
                <c:if test="${errors != null && errors.messages[AttributesHolder.TITLE] != null}">
                    <div class="field-error">
                            ${errors.messages[AttributesHolder.TITLE]}
                    </div>
                </c:if>
                <input class="form-input"
                       id="title"
                       type="text"
                       name="${AttributesHolder.TITLE}"
                       value="${book.title}"
                       required />
            </div>

            <div class="form-group">
                <label class="form-label" for="author">Author</label>
                <c:if test="${errors != null && errors.messages[AttributesHolder.AUTHOR] != null}">
                    <div class="field-error">
                            ${errors.messages[AttributesHolder.AUTHOR]}
                    </div>
                </c:if>
                <input class="form-input"
                       id="author"
                       type="text"
                       name="${AttributesHolder.AUTHOR}"
                       value="${book.author}"
                       required />
            </div>

            <div class="form-group">
                <label class="form-label" for="genre">Genre</label>
                <c:if test="${errors != null && errors.messages[AttributesHolder.GENRE] != null}">
                    <div class="field-error">
                            ${errors.messages[AttributesHolder.GENRE]}
                    </div>
                </c:if>
                <input class="form-input"
                       id="genre"
                       type="text"
                       name="${AttributesHolder.GENRE}"
                       value="${book.genre}"
                       required />
            </div>

            <div class="form-group">
                <label class="form-label" for="reader">Reader</label>
                <select class="form-select" id="reader" name="${AttributesHolder.TAKEN_BY_READER_ID}">
                    <option value="">-- none --</option>

                    <c:forEach var="reader" items="${readers}">
                        <option value="${reader.id}"
                                <c:if test="${book.takenByreader != null && reader.id == book.takenByreader.id}">
                                    selected
                                </c:if>>
                                ${reader.id} - ${reader.firstName} ${reader.lastName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-actions">
                <button class="btn-save" type="submit">Save</button>
            </div>
        </form>
    </div>

</div>
</body>
</html>
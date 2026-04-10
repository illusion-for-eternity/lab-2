<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Library</title>
    <!-- FONTS -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
            href="https://fonts.googleapis.com/css2?family=Inter:ital,opsz,wght@0,14..32,100..900;1,14..32,100..900&display=swap"
            rel="stylesheet"
    />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body>
<div class="container">
    <header>
        <h1>Library</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/index.jsp">Home</a>
            <a href="${pageContext.request.contextPath}/app/books">All Books</a>
            <a href="${pageContext.request.contextPath}/app/books/addBook">Details / Add Book</a>
        </nav>
        </nav>
    </header>

    <section>
        <h2>WELCOME!</h2>
        <div class="page one">
            <h3>Details / Add Book</h3>
            <p>
                Сторінка з інформацією про певний запис (ця сторінка має використовуватись для перегляду, редагування та створення запису). При цьому інформація з додаткової сутності має повністю показуватись на сторінці, а не лише її ідентифікатор.
            </p>
        </div>
        <div class="page two">
            <h3>All Books</h3>
            <p>
                Сторінка зі списком записів певної сутності й кнопками для створення, редагування, видалення запису певної сутності. Інформація з додаткової сутності може показуватись лише частково (наприкла, id).
            </p>
        </div>
        <img src="${pageContext.request.contextPath}/images/cat.jpg" alt="" />
    </section>
</div>
</body>
</html>

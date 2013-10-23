<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.org.fakeurl.page.utils.*"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="assets/bootstrap/css/bootswatch.css" />
    <link rel="stylesheet" type="text/css" href="assets/css/mainpage.css">
    <title>Main Page</title>
</head>
<body>
<%--<%=BuildPageUtil.createNavbar(false)%>--%>
<jsp:include page="navbar.jsp"></jsp:include>

<div class="container">
    <h1>Тут буде форма додавання нового сайту або редагування старого</h1>
    <h1>Вибір для гугла та яндекса</h1>
    <h1>Додавання(включення) та видалення(відключення) слів</h1>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
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
    <h1>Тут буде статистика позицій слів в гуглі та яндексі(дві таблиці)</h1>
    <h1>Можна вибрати період показу статистики</h1>
    <h1>Кнопка вигрузки в ексель</h1>
    <h1>Над кожною таблицею графік</h1>
    <h1>В таблиці біля кожного слова кнопка (Вкл/Викл). При поції слова не будуть шукатись ні в яндексі, ні в гуглі</h1>
</div>

<jsp:include page="footer.jsp"/>
</body>
</html>
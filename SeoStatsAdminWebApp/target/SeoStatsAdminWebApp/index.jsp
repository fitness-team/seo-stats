<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
	<jsp:useBean id="admin" class="org.gymAdviser.dto.Admin" scope="session"></jsp:useBean>
<%
	if (request.getRemoteAddr().equals("127.0.0.1")
			|| request.getRemoteAddr().equals("89.252.54.10")
			|| request.getRemoteAddr().equals("109.197.217.122")) {
%>
	<%
		if (admin.getAdminName() == null) {
	%>
		<form action="login" method="post">
		database<input type="text" name="database"/>
		login<input type="text" name="userID"/>
		password<input type="password" name="password"/>
		<input type="submit" name="submit"/>
		</form>
	<%
		} else {
				response.sendRedirect("tables-list.jsp");
			}
		} else {
			out.print(request.getRemoteAddr());
		}
	%>

</body>
</html>
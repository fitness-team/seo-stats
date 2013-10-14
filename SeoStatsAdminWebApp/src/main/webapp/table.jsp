<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Collection,java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="org.gymAdviser.dto.Admin,org.gymAdviser.dto.TableRow"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success</title>
<body>
	<%
		if (request.getRemoteAddr().equals("127.0.0.1")
				|| request.getRemoteAddr().equals("89.252.54.10")) {
	%>
	<jsp:useBean id="admin" class="org.gymAdviser.dto.Admin"
		scope="session"></jsp:useBean>
	<jsp:useBean id="table" class="org.gymAdviser.dto.Table"
		scope="session"></jsp:useBean>
	<h3>
		Admin:
		<jsp:getProperty property="adminName" name="admin" />
	</h3>
	<a href="tables-list.jsp"><p>return</p></a>
	<h4>
		Table:
		<%
		out.println((String) request.getSession().getAttribute(
					"tableName"));
	%>
	</h4>

	<form accept-charset="ISO-8859-1" action="tableinsert" method="post">
		<table style="border: 1px solid">
			<tr>
				<%
					TableRow row = table.getRow(0);
						for (int j = 0; j < row.size(); ++j) {
				%>
				<td style="border: 1px solid"><b><%=row.getFild(j)%></b></td>
				<%
					}
				%>
				<%
					for (int i = 1; i < table.size(); ++i) {
							row = table.getRow(i);
				%>
			</tr>

			<tr>
				<%
					for (int j = 0; j < row.size(); ++j) {
				%>
				<td style="border: 1px solid"><%=row.getFild(j)%></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
			<tr>
				<%
					row = table.getRow(0);
						for (int j = 0; j < row.size(); ++j) {
				%>
				<td><input type="text" name=<%=row.getFild(j)%>
					value=<%=row.getFild(j)%> /></td>
				<%
					}
				%>
			</tr>

		</table>
		<input type="submit" name="submit" value="Insert" />
	</form>
	<%
		} else {
			out.print(request.getRemoteAddr());
		}
	%>
</body>
</html>
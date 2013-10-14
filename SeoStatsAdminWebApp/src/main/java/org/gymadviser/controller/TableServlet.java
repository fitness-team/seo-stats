package org.gymadviser.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gymAdviser.dto.Admin;
import org.gymAdviser.dto.Table;
import org.gymadviser.service.LoginService;
import org.gymadviser.service.TablesService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/table")
public class TableServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String database, userId, password;
		database = (String) request.getSession().getAttribute("database");
		userId = (String) request.getSession().getAttribute("userId");
		password = (String) request.getSession().getAttribute("password");
		LoginService loginService = new LoginService();
		boolean result = loginService
				.authentificate(database, userId, password);
		if (result) {
			TablesService tablesService = new TablesService();
			System.out.println(request.getParameter("name"));
			Table table = tablesService.getTable(database, userId, password,
					request.getParameter("name"));
			request.getSession().setAttribute("tableName",
					request.getParameter("name"));
			request.getSession().setAttribute("table", table);
			response.sendRedirect("table.jsp");
			return;
		}
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * String database, userId, password; database =
		 * request.getParameter("database"); userId =
		 * request.getParameter("userID"); password =
		 * request.getParameter("password"); LoginService loginService = new
		 * LoginService(); boolean result =
		 * loginService.authentificate(database, userId, password); if (result)
		 * { Admin admin = loginService.getAdminDetales(userId); TablesService
		 * tablesService = new TablesService(database, userId, password);
		 * ArrayList<String> tables = tablesService.getTables();
		 * request.getSession().setAttribute("admin", admin);
		 * request.getSession().setAttribute("tables", tables);
		 * System.out.print(admin.getAdminName());
		 * response.sendRedirect("tables.jsp"); return; }
		 * response.sendRedirect("index.jsp");
		 */
	}

}
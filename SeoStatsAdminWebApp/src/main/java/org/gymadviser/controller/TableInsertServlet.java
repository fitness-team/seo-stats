package org.gymadviser.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

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
@WebServlet("/tableinsert")
public class TableInsertServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
			String tableName = (String) request.getSession().getAttribute(
					"tableName");
			Map<String, String[]> params = request.getParameterMap();

			TablesService tablesService = new TablesService();
			tablesService.insertData(database, userId, password, tableName,
					params);
			request.getSession().setAttribute("tableName",
					request.getParameter("name"));
			response.sendRedirect("table?name=" + tableName);
			return;
		}
		response.sendRedirect("index.jsp");
	}

}
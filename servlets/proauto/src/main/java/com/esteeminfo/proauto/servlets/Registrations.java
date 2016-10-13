package com.esteeminfo.proauto.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esteeminfo.prouto.dao.EmployeeDAO;

/**
 * Servlet implementation class Registrations
 */
@WebServlet("Registrations")
public class Registrations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registrations() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String empName = request.getParameter("empName");
		String empPassword = request.getParameter("empPassword");
		String empEmail = request.getParameter("empEmail");
		String empRole = request.getParameter("empRole");
		System.out.println("empName = "+empName);
		System.out.println("empPassword = "+empPassword);
		System.out.println("empEmail = "+empEmail);
		System.out.println("empRole = "+empRole);

		response.sendRedirect("ereg.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

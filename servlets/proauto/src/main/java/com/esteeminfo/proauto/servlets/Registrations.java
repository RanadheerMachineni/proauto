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

		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		String userEmail = request.getParameter("userEmail");
		String userRole = request.getParameter("userRole");
		System.out.println("userName = "+userName);
		System.out.println("userPass = "+userPass);
		System.out.println("userEmail = "+userEmail);
		System.out.println("userRole = "+userRole);

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

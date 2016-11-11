//package com.esteeminfo.proauto.servlets;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.esteeminfo.prouto.dao.CommonDAO;
//
///**
// * Servlet implementation class AuthenticateUser
// */
//@WebServlet("AuthenticateUser")
//public class AuthenticateUser extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	/**
//	 * @see HttpServlet#HttpServlet()
//	 */
//	public AuthenticateUser() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//
//		String userid = request.getParameter("userid");
//		String password = request.getParameter("password");
//		String role = null;
//		try {
//			role = CommonDAO.authenticateUser(userid, password);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("role in servlet  = "+role);
//		if (null != role && role.length() > 0) {
//			request.getSession().setAttribute("role", role);
//			response.sendRedirect("dashboard.jsp");
//		}else{
//			response.sendRedirect("index.jsp");
//		}
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//	 *      response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//}

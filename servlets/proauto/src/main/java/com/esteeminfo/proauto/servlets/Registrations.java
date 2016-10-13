package com.esteeminfo.proauto.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esteeminfo.prouto.dao.CommonDAO;

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

		String regType = request.getParameter("regType");
		boolean registered = false;
		if(regType.equalsIgnoreCase("employee")){
			String empName = request.getParameter("empName");
			String empPassword = request.getParameter("empPassword");
			String empEmail = request.getParameter("empEmail");
			String empRole = request.getParameter("empRole");
			registered = CommonDAO.registerEmployee(empName,empPassword,empEmail,empRole);
			System.out.println("registered in servlet ="+registered);
			request.getSession().setAttribute("registered", registered);
			response.sendRedirect("ereg.jsp");
		}else if(regType.equalsIgnoreCase("vendor")){
			String vName = request.getParameter("vName");
			String vAddress = request.getParameter("vAddress");
			String vEmail = request.getParameter("vEmail");
			String vFirstContact = request.getParameter("vFirstContact");
			String vSecondContact = request.getParameter("vSecondContact");
			String vThirdContact = request.getParameter("vThirdContact");
			registered = CommonDAO.registerVendor(vName,vAddress,vEmail,vFirstContact,vSecondContact,vThirdContact);
			request.getSession().setAttribute("registered", registered);
			response.sendRedirect("vreg.jsp");
		}else if(regType.equalsIgnoreCase("customer")){
			String cName = request.getParameter("cName");
			String cAddress = request.getParameter("cAddress");
			String cEmail = request.getParameter("cEmail");
			String cFirstContact = request.getParameter("cFirstContact");
			String cSecondContact = request.getParameter("cSecondContact");
			String cThirdContact = request.getParameter("cThirdContact");
			registered = CommonDAO.registerCustomer(cName,cAddress,cEmail,cFirstContact,cSecondContact,cThirdContact);
			request.getSession().setAttribute("registered", registered);
			response.sendRedirect("creg.jsp");
		}
		
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

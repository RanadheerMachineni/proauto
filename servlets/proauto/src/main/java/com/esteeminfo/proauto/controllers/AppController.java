package com.esteeminfo.proauto.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.Vendor;
import com.esteeminfo.prouto.dao.CommonDAO;

@Controller
public class AppController {
	
	final static Logger logger = Logger.getLogger(AppController.class);


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	@RequestMapping(value = { "/dashboard"}, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("dashboard");
		return model;
	}
	
	@RequestMapping(value = { "/dms"}, method = RequestMethod.GET)
	public ModelAndView dbsPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("dms");
		return model;
	}

	@RequestMapping(value = { "/jobcard"}, method = RequestMethod.GET)
	public ModelAndView jobcardPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("jobcard");
		return model;
	}
	
	@RequestMapping(value = { "/costing"}, method = RequestMethod.GET)
	public ModelAndView costingPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("costing");
		return model;
	}

	@RequestMapping(value = { "/creg"}, method = RequestMethod.GET)
	public String showcregPage(Model model, @RequestParam(value="customerSelected", required=false) String customerSelected, HttpServletRequest request, HttpServletResponse response) {
		logger.info("***************************** creg GET customerSelected = "+customerSelected);
		Customer customer = new Customer();
		if(customerSelected!=null){
			customer = CommonDAO.retrieveCustomer(customerSelected);
		}
		String customerSearched = request.getParameter("searchCustomerInput");
		List<Customer> customerList = CommonDAO.retrieveAllCustomers(customerSearched);
		model.addAttribute("customerSelected", customer);
		model.addAttribute("customerList", customerList);
		return "creg";
	}
	
	@RequestMapping(value = { "creg"}, method = RequestMethod.POST)
	public String postcregPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		String create = request.getParameter("create");
		
		String cid = request.getParameter("cid");

		String cName = request.getParameter("cName");
		String cAddress = request.getParameter("cAddress");

		String nameOne = request.getParameter("name_one");
		String phoneOne = request.getParameter("phone_one");
		String emailOne = request.getParameter("email_one");

		String nameTwo = request.getParameter("name_two");
		String phonTwo = request.getParameter("phone_two");
		String emailTwo = request.getParameter("email_two");

		String namethree = request.getParameter("name_three");
		String phonethree = request.getParameter("phone_three");
		String emailthree = request.getParameter("email_three");

		String namefour = request.getParameter("name_four");
		String phonefour = request.getParameter("phone_four");
		String emailfour = request.getParameter("email_four");

		String namefive = request.getParameter("name_five");
		String phonefive = request.getParameter("phone_five");
		String emailfive = request.getParameter("email_five");

		String namesix = request.getParameter("name_six");
		String phonesix = request.getParameter("phone_six");
		String emailsix = request.getParameter("email_six");

		String nameseven = request.getParameter("name_seven");
		String phoneseven = request.getParameter("phone_seven");
		String emailseven = request.getParameter("email_seven");

		String nameeight = request.getParameter("name_eight");
		String phoneeight = request.getParameter("phone_eight");
		String emaileight = request.getParameter("email_eight");

		String namenine = request.getParameter("name_nine");
		String phonenine = request.getParameter("phone_nine");
		String emailnine = request.getParameter("email_nine");

		String nameten = request.getParameter("name_ten");
		String phoneten = request.getParameter("phone_ten");
		String emailten = request.getParameter("email_ten");

		logger.info("***************************** creg Post cName= "+cName+",create = "+create);
		
		try {
			CommonDAO.registerCustomer(create,cid, cName, cAddress, nameOne, phoneOne, emailOne, nameTwo, phonTwo, emailTwo, namethree, phonethree, emailthree, namefour, phonefour, emailfour,
					namefive, phonefive, emailfive, namesix, phonesix, emailsix, nameseven, phoneseven, emailseven, nameeight, phoneeight, emaileight, namenine, phonenine, emailnine,
					nameten, phoneten, emailten);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		
		List<Customer> customerList =CommonDAO.retrieveAllCustomers(null);
		model.addAttribute("customerList", customerList);
		return "creg";
	}
	
	@RequestMapping(value = { "/vreg"}, method = RequestMethod.GET)
	public String showvregPage(Model model, @RequestParam(value="vendorSelected", required=false) String vendorSelected, HttpServletRequest request, HttpServletResponse response) {
		logger.info("***************************** vreg GET vendorSelected = "+vendorSelected);
		Vendor vendor = new Vendor();
		if(vendorSelected!=null){
			vendor = CommonDAO.retrieveVendor(vendorSelected);
		}
		String vendorSearched = request.getParameter("searchVendorInput");
		List<Vendor> vendorList = CommonDAO.retrieveAllVendors(vendorSearched);
		model.addAttribute("vendorSelected", vendor);
		model.addAttribute("vendorList", vendorList);
		return "vreg";
	}
	
	@RequestMapping(value = { "/vreg"}, method = RequestMethod.POST)
	public String postvregPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		String create = request.getParameter("create");
		String vid = request.getParameter("vid");

		String vName = request.getParameter("vName");
		String vAddress = request.getParameter("vAddress");

		String nameOne = request.getParameter("name_one");
		String phoneOne = request.getParameter("phone_one");
		String emailOne = request.getParameter("email_one");

		String nameTwo = request.getParameter("name_two");
		String phonTwo = request.getParameter("phone_two");
		String emailTwo = request.getParameter("email_two");

		String namethree = request.getParameter("name_three");
		String phonethree = request.getParameter("phone_three");
		String emailthree = request.getParameter("email_three");

		String namefour = request.getParameter("name_four");
		String phonefour = request.getParameter("phone_four");
		String emailfour = request.getParameter("email_four");

		String namefive = request.getParameter("name_five");
		String phonefive = request.getParameter("phone_five");
		String emailfive = request.getParameter("email_five");

		String namesix = request.getParameter("name_six");
		String phonesix = request.getParameter("phone_six");
		String emailsix = request.getParameter("email_six");

		String nameseven = request.getParameter("name_seven");
		String phoneseven = request.getParameter("phone_seven");
		String emailseven = request.getParameter("email_seven");

		String nameeight = request.getParameter("name_eight");
		String phoneeight = request.getParameter("phone_eight");
		String emaileight = request.getParameter("email_eight");

		String namenine = request.getParameter("name_nine");
		String phonenine = request.getParameter("phone_nine");
		String emailnine = request.getParameter("email_nine");

		String nameten = request.getParameter("name_ten");
		String phoneten = request.getParameter("phone_ten");
		String emailten = request.getParameter("email_ten");

		logger.info("***************************** vreg Post "+vName);
		
		try {
			CommonDAO.registerVendor(create, vid, vName, vAddress, nameOne, phoneOne, emailOne, nameTwo, phonTwo, emailTwo, namethree, phonethree, emailthree, namefour, phonefour, emailfour,
					namefive, phonefive, emailfive, namesix, phonesix, emailsix, nameseven, phoneseven, emailseven, nameeight, phoneeight, emaileight, namenine, phonenine, emailnine,
					nameten, phoneten, emailten);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		
		List<Vendor> vendorList =CommonDAO.retrieveAllVendors(null);
		model.addAttribute("vendorList", vendorList);
		return "vreg";
	}
	
	@RequestMapping(value = { "/ereg"}, method = RequestMethod.GET)
	public ModelAndView eregPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("ereg");
		return model;
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView logoutPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}
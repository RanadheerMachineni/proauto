package com.esteeminfo.proauto.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.Employee;
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
	
	@RequestMapping(value = "/get_provider_list", method = RequestMethod.GET, headers="Accept=*/*")
	public @ResponseBody List<String> getProviderList(@RequestParam("provider") String provider, @RequestParam("starts") String starts) {
		logger.info("************* get_provider_list, provider = "+provider+",starts = "+starts);
		List<String> li = new ArrayList<String>();
		li.add("a");
		li.add("ab");
		li.add("ac");
		li.add("ad");

		return li;
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

	@RequestMapping(value = { "/rmat"}, method = RequestMethod.GET)
	public String rmatPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "rmat";
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
	public String showeregPage(Model model, @RequestParam(value="employeeSelected", required=false) String employeeSelected, HttpServletRequest request, HttpServletResponse response) {
		logger.info("***************************** ereg GET employeeSelected = "+employeeSelected);
		
		Employee employee = new Employee();
		if(employeeSelected!=null){
			employee = CommonDAO.retrieveEmployee(employeeSelected);
			model.addAttribute("employeeSelectedRole", employee.getRole());
			logger.info("***************************** ereg GET employeeSelectedRole = "+employee.getRole());

		}else{
			model.addAttribute("employeeSelectedRole", "ROLE_norole");
		}
		String employeeSearched = request.getParameter("searchEmployeeInput");
		List<Employee> employeeList = CommonDAO.retrieveAllEmployees(employeeSearched);
		logger.info("***************************** ereg GET employeeSelected empid = "+employee.getEmployeeId());

		model.addAttribute("employeeSelected", employee);

		model.addAttribute("employeeList", employeeList);
		
		Map<String, String> roleMap = new HashMap<String, String>(); 
		CommonDAO.loadRoleMap(roleMap);
		/*roleMap.put("ROLE_norole", "- Not user");
		roleMap.put("ROLE_admin", "Administrator");
		roleMap.put("ROLE_dms", "DMS user");*/
		model.addAttribute("roles", roleMap);
		return "ereg";
	}
	
	@RequestMapping(value = { "ereg"}, method = RequestMethod.POST)
	public String posteregPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		String create = request.getParameter("create");
		
		String eid = request.getParameter("eid");

		String efirstName = request.getParameter("efirstName");
		String eLastName = request.getParameter("eLastName");
		String gender = request.getParameter("gender");
		String eQualification = request.getParameter("eQualification");
		String eExperience = request.getParameter("eExperience");
		String married = request.getParameter("married");
		String eDesignation = request.getParameter("eDesignation");
		String eDob = request.getParameter("eDob");
		String eDoj = request.getParameter("eDoj");
		String eRole = request.getParameter("eRole");
		String eUserId = request.getParameter("eUserId");
		String password = request.getParameter("ePassword");
		String ePhone = request.getParameter("ePhone");
		String eEmail = request.getParameter("eEmail");
		String ePassport = request.getParameter("ePassport");
		String eEmergencyContact = request.getParameter("eEmergencyContact");
		String eCAddress = request.getParameter("eCAddress");
		String ePAddress = request.getParameter("ePAddress");
		String eNotes = request.getParameter("eNotes");

		logger.info("***************************** ereg Post efirstName= "+efirstName+",eRole = "+eRole+", gender = "+gender+", eDob"+eDob+", married"+married);
		
		try {
			CommonDAO.registerEmployee(create, eid, efirstName, eLastName, gender, eQualification, eExperience, married, eDesignation, eDob,eDoj, eRole, eUserId, password,
					ePhone, eEmail, ePassport, eEmergencyContact, eCAddress, ePAddress, eNotes);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		Map<String, String> roleMap = new HashMap<String, String>(); 
		CommonDAO.loadRoleMap(roleMap);
		model.addAttribute("roles", roleMap);
		model.addAttribute("employeeSelectedRole", "ROLE_norole");
		List<Employee> employeeList =CommonDAO.retrieveAllEmployees(null);
		model.addAttribute("employeeList", employeeList);
		return "ereg";
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
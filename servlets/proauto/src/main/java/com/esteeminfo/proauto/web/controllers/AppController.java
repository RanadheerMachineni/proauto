package com.esteeminfo.proauto.web.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.esteeminfo.proauto.dao.CommonDAO;
import com.esteeminfo.proauto.dao.EmployeeDao;
import com.esteeminfo.proauto.dto.EmployeeDTO;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.Role;

@Controller
public class AppController {
	
	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");
	
	final static Logger logger = Logger.getLogger(AppController.class);
	
	@Autowired(required=true)
	private EmployeeDao employeeDao ;
	
	@Autowired(required=true)
	private CommonDAO commonDAO ;

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
		
		return "creg";
	}
	
	@RequestMapping(value = { "creg"}, method = RequestMethod.POST)
	public String postcregPage(Model model, HttpServletRequest request, HttpServletResponse response) {	return "creg";
	}
	
	
	@RequestMapping(value = { "/ereg"}, method = RequestMethod.GET)
	public String showeregPage(Model model, @RequestParam(value="employeeSelected", required=false) String employeeSelected, HttpServletRequest request, HttpServletResponse response) {
		logger.info("***************************** ereg GET employeeSelected = "+employeeSelected);
		
		EmployeeDTO employeeDTO = new EmployeeDTO();
		if(employeeSelected!=null){
			Employee employee = employeeDao.findById(Integer.valueOf(employeeSelected));
			converEmployeeToDto(employee,employeeDTO);
			model.addAttribute("employeeSelectedRole", employeeDTO.getRoles().get(0));
		}else{
			model.addAttribute("employeeSelectedRole", "ROLE_norole");
		}
		
		String employeeSearched = request.getParameter("searchEmployeeInput");

		List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
		List<Employee> employeeList = employeeDao.retrieveAllEmployees(employeeSearched);

		for(Employee eachEmployee : employeeList){
			EmployeeDTO eachEmployeeDTO = new EmployeeDTO();
			converEmployeeToDto(eachEmployee,eachEmployeeDTO);
			employeeDTOList.add(eachEmployeeDTO);
		}
		model.addAttribute("employeeSelected", employeeDTO);

		model.addAttribute("employeeList", employeeDTOList);
		
		Map<String, String> roleMap = new HashMap<String, String>(); 
		//commonDAO.loadRoleMap(roleMap);
		roleMap.put("ROLE_norole", "- Not user");
		roleMap.put("ROLE_admin", "Administrator");
		roleMap.put("ROLE_dms", "DMS user");
		roleMap.put("ROLE_jobcard", "Jobcard user");
		roleMap.put("ROLE_costing", "Costing user");
		
		Map<String, String> sectionMap = new HashMap<String, String>(); 
		//commonDAO.loadRoleMap(roleMap);
		sectionMap.put("Section1", "Section1");
		sectionMap.put("Section2", "Section2");

		model.addAttribute("roles", roleMap);
		model.addAttribute("sections", sectionMap);

		return "ereg";
	}
	
	private void converEmployeeToDto(Employee employee, EmployeeDTO employeeDTO) {
		if(employee.getEmployeeId()>0){
			employeeDTO.setEmployeeId(employee.getEmployeeId());
			employeeDTO.setFirstName(employee.getFirstName());
			employeeDTO.setLastName(employee.getLastName());
			employeeDTO.setGender(employee.getGender());
			employeeDTO.setDesignation(employee.getDesignation());
			employeeDTO.setDob(ui_date_format.format(employee.getDob()));
			employeeDTO.setDoj(ui_date_format.format(employee.getDoj()));
			employeeDTO.setQualification(employee.getQualification());
			employeeDTO.setExperience(employee.getExperience());
			employeeDTO.setMarried(employee.getMarried());
			employeeDTO.setPassport(employee.getPassport());
			employeeDTO.setEmergencyContact(employee.getEmergencyContact());
			employeeDTO.setUserId(employee.getUserId());
			employeeDTO.setPassword(employee.getPassword());
			employeeDTO.setCurrentAddress(employee.getCurrentAddress());
			employeeDTO.setPermanentAddress(employee.getPermanentAddress());
			employeeDTO.setPhone(employee.getPhone());
			employeeDTO.setEmail(employee.getEmail());
			employeeDTO.setNotes(employee.getNotes());
			employeeDTO.setStatus(employee.getStatus());
			employeeDTO.setEmploymentType(employee.getEmployementType());
			employeeDTO.setSection(employee.getSection().getSectionId());
			List<String> roles =  new ArrayList<String>();
			for(Role eachRole : employee.getRoles()){
				roles.add(eachRole.getRoleId());
			}
			employeeDTO.setRoles(roles);
		}
	}

	@RequestMapping(value = { "ereg"}, method = RequestMethod.POST)
	public String posteregPage(Model model, /*@RequestParam("file") MultipartFile[] files, */HttpServletRequest request, HttpServletResponse response) {
		System.out.println("**********************posteregPage ");
		
		/*if(files!=null){
			System.out.println("no of files "+files.length);
			
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				try {
					byte[] bytes = file.getBytes();
					// Creating the directory to store file
					
				} catch (Exception e) {
				}
			}
		}*/
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
		String eEmploymentType = request.getParameter("eEmploymentType");
		String eSection = request.getParameter("eSection");

		logger.info("***************************** ereg Post efirstName= "+efirstName+",eRole = "+eRole+", gender = "+gender+", eDob"+eDob+", married"+married);
		
		try {
			employeeDao.registerEmployee(create, eid, efirstName, eLastName, gender, eQualification, eExperience, married, eDesignation, eDob,eDoj, eRole, eUserId, password,
					ePhone, eEmail, ePassport, eEmergencyContact, eCAddress, ePAddress, eNotes, eEmploymentType, eSection);
			Map<String, String> roleMap = new HashMap<String, String>(); 
			roleMap.put("ROLE_norole", "- Not user");
			roleMap.put("ROLE_admin", "Administrator");
			roleMap.put("ROLE_dms", "DMS user");
			roleMap.put("ROLE_jobcard", "Jobcard user");
			roleMap.put("ROLE_costing", "Costing user");
			model.addAttribute("roles", roleMap);
			model.addAttribute("employeeSelectedRole", "ROLE_norole");
			
			Map<String, String> sectionMap = new HashMap<String, String>(); 
			sectionMap.put("Section1", "Section1");
			sectionMap.put("Section2", "Section2");
			model.addAttribute("sections", sectionMap);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId(Integer.valueOf(eid));
			employeeDTO.setFirstName(efirstName);
			employeeDTO.setLastName(eLastName);
			employeeDTO.setGender(gender);
			employeeDTO.setDesignation(eDesignation);
			employeeDTO.setDob(eDob);
			employeeDTO.setDoj(eDoj);
			employeeDTO.setQualification(eQualification);
			employeeDTO.setExperience(eExperience);
			employeeDTO.setMarried(married);
			employeeDTO.setPassport(ePassport);
			employeeDTO.setEmergencyContact(eEmergencyContact);
			employeeDTO.setUserId(eUserId);
			employeeDTO.setPassword(password);
			employeeDTO.setCurrentAddress(eCAddress);
			employeeDTO.setPermanentAddress(ePAddress);
			employeeDTO.setPhone(ePhone);
			employeeDTO.setEmail(eEmail);
			employeeDTO.setNotes(eNotes);
			employeeDTO.setEmploymentType(eEmploymentType);
			employeeDTO.setSection(eSection);

			Map<String, String> roleMap = new HashMap<String, String>(); 
			roleMap.put("ROLE_norole", "- Not user");
			roleMap.put("ROLE_admin", "Administrator");
			roleMap.put("ROLE_dms", "DMS user");
			roleMap.put("ROLE_jobcard", "Jobcard user");
			roleMap.put("ROLE_costing", "Costing user");
			model.addAttribute("roles", roleMap);
			model.addAttribute("employeeSelectedRole", eRole);
			model.addAttribute("employeeSelected", employeeDTO);
			
			Map<String, String> sectionMap = new HashMap<String, String>(); 
			//commonDAO.loadRoleMap(roleMap);
			sectionMap.put("Section1", "Section1");
			sectionMap.put("Section2", "Section2");
			model.addAttribute("sections", sectionMap);
		}
		List<Employee> employeeList = employeeDao.retrieveAllEmployees(null);
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
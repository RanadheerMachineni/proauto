package com.esteeminfo.proauto.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.dto.EmployeeDTO;
import com.esteeminfo.proauto.dto.JobOpDTO;
import com.esteeminfo.proauto.dto.JobcardDTO;
import com.esteeminfo.proauto.dto.MachineDTO;
import com.esteeminfo.proauto.dto.PoDTO;
import com.esteeminfo.proauto.dto.PurchaseDTO;
import com.esteeminfo.proauto.dto.RawMaterialDTO;
import com.esteeminfo.proauto.dto.VendorDTO;
import com.esteeminfo.proauto.dto.MakeDTO;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.JobOperation;
import com.esteeminfo.proauto.entity.Jobcard;
import com.esteeminfo.proauto.entity.Machine;
import com.esteeminfo.proauto.entity.Make;
import com.esteeminfo.proauto.entity.PoTool;
import com.esteeminfo.proauto.entity.Purchase;
import com.esteeminfo.proauto.entity.PurchaseOrder;
import com.esteeminfo.proauto.entity.RawMaterial;
import com.esteeminfo.proauto.entity.Vendor;
import com.esteeminfo.proauto.service.CommonService;
import com.esteeminfo.proauto.service.CustomerService;
import com.esteeminfo.proauto.service.EmployeeService;
import com.esteeminfo.proauto.service.JobcardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AppController {

	final static Logger logger = Logger.getLogger(AppController.class);

	@Autowired(required = true)
	private EmployeeService employeeService;

	@Autowired(required = true)
	private CustomerService customerService;

	@Autowired(required = true)
	private CommonService commonService;

	@Autowired(required = true)
	private JobcardService jobcardService;

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

	@RequestMapping(value = { "/getPos" }, method = RequestMethod.GET)
	public @ResponseBody String getPos(@RequestParam("customer") String customer) throws JsonProcessingException {
		Map<String, String> pos = commonService.findPOByCustId(customer);
		String json = new ObjectMapper().writeValueAsString(pos);
		return json;
	}
	
	@RequestMapping(value = { "/getPoItems" }, method = RequestMethod.GET)
	public @ResponseBody String getPoItems(@RequestParam("po") String po) throws JsonProcessingException {
		Map<String, String> poItems = commonService.findPOItemsByPO(po);
		String json = new ObjectMapper().writeValueAsString(poItems);
		return json;
	}

	

	@RequestMapping(value = { "/getJobList" }, method = RequestMethod.GET)
	public @ResponseBody String getJobList(@RequestParam("po") String po) throws JsonProcessingException {
		Map<String, String> jobs = commonService.findJobsByPO(po);
		String json = new ObjectMapper().writeValueAsString(jobs);
		return json;
	}

	@RequestMapping(value = { "/getTaskList" }, method = RequestMethod.GET)
	public @ResponseBody String getTaskList(@RequestParam("jobcard") String jobcard) throws JsonProcessingException {
		Map<String, String> tasks = jobcardService.findTasksByJob(jobcard);
		String json = new ObjectMapper().writeValueAsString(tasks);
		return json;
	}

	@RequestMapping(value = { "/getMakes" }, method = RequestMethod.GET)
	public @ResponseBody String getMakes(@RequestParam("tool") String tool) throws JsonProcessingException {
		Map<String, String> makeMap = commonService.findMakesOfTool(tool);
		String json = new ObjectMapper().writeValueAsString(makeMap);
		return json;
	}

	@RequestMapping(value = { "/dashboard" }, method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("dashboard");
		return model;
	}

	@RequestMapping(value = { "/dms" }, method = RequestMethod.GET)
	public ModelAndView dbsPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("dms");
		return model;
	}

	@RequestMapping(value = { "/jobcard" }, method = RequestMethod.GET)
	public ModelAndView jobcardPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("jobcard");
		return model;
	}

	@RequestMapping(value = { "/machineusage" }, method = RequestMethod.GET)
	public String showMachineUse(Model model, @RequestParam(value = "muSelected", required = false) String muSelected,
			HttpServletRequest request, HttpServletResponse response) {
		// String purchaseSearched = request.getParameter("searchCodeInput");
		// List<PurchaseDTO> purchaseDTOList =
		// commonService.retrieveAllMMUDTO(muSelected);
		// model.addAttribute("muList", purchaseDTOList);

		Map<String, String> shiftMap = new HashMap<String, String>();
		shiftMap.put("1", "First shift");
		shiftMap.put("2", "Second shift");
		shiftMap.put("3", "Third shift");
		model.addAttribute("shifts", shiftMap);

		Map<String, String> machineMap = new HashMap<String, String>();
		machineMap = commonService.getMachines();
		model.addAttribute("machines", machineMap);

		Map<String, String> customerMap = new HashMap<String, String>();
		customerMap = customerService.retreiveCustomerMap();
		model.addAttribute("customers", customerMap);

		Map<String, String> empMap = new HashMap<String, String>();
		empMap = employeeService.getEmployees();
		model.addAttribute("employees", empMap);

		//tools/vendors/make/rm
		/*Map<String,String> toolMap  = new HashMap<String, String>();
		toolMap = commonService.getTools();
		model.addAttribute("tools", toolMap);
		
		Map<String,String> rmMap  = new HashMap<String, String>();
		rmMap = commonService.getRawMaterials();
		model.addAttribute("tools", rmMap);*/
		
		
		// Map<String, String> inventoryMap = new HashMap<String, String>();
		// inventoryMap = commonService.getInventoryItems();
		model.addAttribute("tools", null);

		return "machineusage";
	}

	@RequestMapping(value = { "/machineusage" }, method = RequestMethod.POST)
	public String postMachineUse(Model model, HttpServletRequest request, HttpServletResponse response) {
		String purchaseSearched = request.getParameter("searchCodeInput");
		List<PurchaseDTO> purchaseDTOList = commonService.retrieveAllPurchaseDTO(purchaseSearched);
		model.addAttribute("purchaseList", purchaseDTOList);
		return "machineusage";
	}

	@RequestMapping(value = { "/showinv" }, method = RequestMethod.GET)
	public String showinv(Model model,
			@RequestParam(value = "purchaseSelected", required = false) String purchaseSelected,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> typeMap = new HashMap<String, String>();
		typeMap = commonService.retreiveTypeMap();
		model.addAttribute("types", typeMap);

		Map<String, String> unitMap = new HashMap<String, String>();
		unitMap = commonService.retreiveUnitMap();
		model.addAttribute("units", unitMap);
		
		PurchaseDTO purchaseDTO = new PurchaseDTO();
		if (purchaseSelected != null && purchaseSelected.length() > 0) {
			purchaseDTO = commonService.findPurchaseDTOById(Integer.valueOf(purchaseSelected));
		}
		model.addAttribute("purchase", purchaseDTO);

		Map<String, String> makeMap = new HashMap<String, String>();
		makeMap = commonService.retreiveMakeMap();
		model.addAttribute("makes", makeMap);
		return "addtoinv";
	}

	@RequestMapping(value = { "/searchinv" }, method = RequestMethod.GET)
	public String searchinv(Model model, HttpServletRequest request, HttpServletResponse response) {
		String purchaseSearched = request.getParameter("searchCodeInput");
		List<PurchaseDTO> purchaseDTOList = commonService.retrieveAllPurchaseDTO(purchaseSearched);
		model.addAttribute("purchaseList", purchaseDTOList);
		return "searchinv";
	}

	@RequestMapping(value = { "/addtoinv" }, method = RequestMethod.POST)
	public String addtoinv(Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> typeMap = new HashMap<String, String>();
		typeMap = commonService.retreiveTypeMap();
		model.addAttribute("types", typeMap);

		Map<String, String> unitMap = new HashMap<String, String>();
		unitMap = commonService.retreiveUnitMap();
		model.addAttribute("units", unitMap);
		
		String create = request.getParameter("create");
		String parid = request.getParameter("parid");
		String particular = request.getParameter("particular");
		String code = request.getParameter("code");
		String make = request.getParameter("make");
		String unit = request.getParameter("unit");
		String desc = request.getParameter("desc");
		String type = request.getParameter("type");
		String authouredby = request.getParameter("authouredby");
		String additems = request.getParameter("additems");

		PurchaseDTO purchaseDTO = new PurchaseDTO();
		Purchase purchaseCreated = null;
		try {
			purchaseCreated = commonService.registerPurchase(create, parid, particular, code, make, unit, desc, type,
					authouredby, additems);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
			if (parid != null && parid.length() > 0) {
				purchaseDTO.setId(Integer.valueOf(parid));
			}
			purchaseDTO.setParticular(particular);
			purchaseDTO.setCode(code);
			purchaseDTO.setMake(make);
			purchaseDTO.setUnit(unit);
			purchaseDTO.setDesciption(desc);
			purchaseDTO.setTooltypeId(type);
			purchaseDTO.setAuthouredby(authouredby);
			purchaseDTO.setQuantity(additems);

			model.addAttribute("purchaseSelected", purchaseDTO);
		}

		if (purchaseCreated != null && purchaseCreated.getParticularId() > 0) {
			model.addAttribute("result", "sucess");
			model.addAttribute("purCreatedId", purchaseCreated.getParticularId());
			model.addAttribute("purCreatedCode", purchaseCreated.getCode());
		}
		Map<String, String> makeMap = new HashMap<String, String>();
		makeMap = commonService.retreiveMakeMap();
		model.addAttribute("makes", makeMap);
		
		return "addtoinv";
	}

	@RequestMapping(value = { "/rmat" }, method = RequestMethod.GET)
	public String rmatPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		return "rmat";
	}

	@RequestMapping(value = { "/ereg" }, method = RequestMethod.GET)
	public String showeregPage(Model model,
			@RequestParam(value = "employeeSelected", required = false) String employeeSelected,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("***************************** ereg GET employeeSelected = " + employeeSelected);

		EmployeeDTO employeeDTO = new EmployeeDTO();
		if (employeeSelected != null) {
			employeeDTO = employeeService.findDTOById(Integer.valueOf(employeeSelected));
			if (employeeDTO.getRoles() != null) {
				model.addAttribute("employeeSelectedRole", employeeDTO.getRoles().get(0));
			}
		} else {
			model.addAttribute("employeeSelectedRole", "ROLE_norole");
		}

		String employeeSearched = request.getParameter("searchEmployeeInput");

		List<EmployeeDTO> employeeDTOList = employeeService.retrieveAllEmployeesDTO(employeeSearched);
		model.addAttribute("employeeSelected", employeeDTO);

		model.addAttribute("employeeList", employeeDTOList);

		Map<String, String> roleMap = new HashMap<String, String>();
		// commonDAO.loadRoleMap(roleMap);
		roleMap.put("ROLE_norole", "- Not user");
		roleMap.put("ROLE_admin", "Administrator");
		roleMap.put("ROLE_dms", "DMS user");
		roleMap.put("ROLE_jobcard", "Jobcard user");
		roleMap.put("ROLE_costing", "Costing user");

		Map<String, String> sectionMap = new HashMap<String, String>();
		// commonDAO.loadRoleMap(roleMap);
		sectionMap.put("Section1", "Section1");
		sectionMap.put("Section2", "Section2");

		model.addAttribute("roles", roleMap);
		model.addAttribute("sections", sectionMap);

		return "ereg";
	}

	@RequestMapping(value = { "fileupload" }, method = RequestMethod.POST)
	public void uploadFile(Model model, @RequestParam("eFiles") MultipartFile[] files, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("in uploadFile " + request.getParameter("guid"));

		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				logger.info("Received file " + file.getOriginalFilename());
			}
		}
	}

	@RequestMapping(value = "/delete/{regType}/{id}/{fname}.{ext}", method = RequestMethod.DELETE)
	public void delete(Model model, @PathVariable("regType") String regType, @PathVariable("id") String id,
			@PathVariable("fname") String fname, @PathVariable("ext") String ext, HttpServletRequest request,
			HttpServletResponse response) {

		/*
		 * ModelAndView view = new ModelAndView(VIEW); service.delete(file + "."
		 * + ext); view.addObject("success", Boolean.TRUE); return view;
		 */
	}

	@RequestMapping(value = { "/creg" }, method = RequestMethod.GET)
	public String showcregPage(Model model,
			@RequestParam(value = "customerSelected", required = false) String customerSelected,
			HttpServletRequest request, HttpServletResponse response) {

		CustomerDTO customerDTO = new CustomerDTO();
		if (customerSelected != null) {
			customerDTO = customerService.findDTOById(Integer.valueOf(customerSelected));
		}
		String customerSearched = request.getParameter("searchCustomerInput");

		List<CustomerDTO> custDTOList = customerService.retrieveAllCustomerDTO(customerSearched);

		model.addAttribute("customerSelected", customerDTO);

		model.addAttribute("customerList", custDTOList);

		return "creg";
	}

	@RequestMapping(value = { "creg" }, method = RequestMethod.POST)
	public String postcregPage(Model model, @RequestParam("eFiles") MultipartFile[] files, HttpServletRequest request,
			HttpServletResponse response) {
		String create = request.getParameter("create");
		String cid = request.getParameter("cid");
		String cName = request.getParameter("cName");
		String cAddress = request.getParameter("cAddress");
		String[] contactname = request.getParameterValues("contactname");
		String[] phone = request.getParameterValues("phone");
		String[] email = request.getParameterValues("email");
		String[] fax = request.getParameterValues("fax");
		String[] notes = request.getParameterValues("notes");
		String removedFiles = request.getParameter("removedFiles");

		// String[] uploadedFilesArray =
		// request.getParameterValues("uploadedFiles");
		// List<String> uploadedFiles = new ArrayList<String>();;

		Map<String, List<String>> contactsMap = new HashMap<String, List<String>>();
		if (contactname != null && contactname.length > 0) {
			for (int i = 0; i < contactname.length; i++) {
				if (contactname[i] != null && contactname[i].length() > 0) {
					List<String> li = new ArrayList<String>();
					li.add(phone[i]);
					li.add(email[i]);
					li.add(fax[i]);
					li.add(notes[i]);
					contactsMap.put(contactname[i], li);
				}
			}
		}

		// if(uploadedFilesArray!=null && uploadedFilesArray.length>0){
		// for(String s: uploadedFilesArray){
		// if(s!=null && s.length()>0){
		// s = s.replaceAll("[\\[\\]]","");
		// uploadedFiles.addAll(Arrays.asList(s.split(",")));
		// }
		// }
		// }
		// List<String> uploadedFilesTrimmed = new ArrayList<String>();;
		// for(String s: uploadedFiles){
		// uploadedFilesTrimmed.add(s.trim());
		// }
		Customer customerCreated = null;
		try {
			customerCreated = customerService.registerCustomer(create, cid, cName, cAddress, contactsMap, files,
					removedFiles);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			CustomerDTO employeeDTO = new CustomerDTO();
			if (cid != null && cid.length() > 0) {
				employeeDTO.setCustomerId(Integer.valueOf(cid));
			}
			employeeDTO.setCustomerName(cName);
			employeeDTO.setAddress(cAddress);
			model.addAttribute("customerSelected", employeeDTO);
		}

		if (customerCreated != null && customerCreated.getCustomerId() > 0) {
			model.addAttribute("result", "sucess");
			model.addAttribute("cusCreated", customerCreated.getCustomerId());
			model.addAttribute("cusCreatedName", customerCreated.getCustomerName());
		}

		List<CustomerDTO> custDTOList = customerService.retrieveAllCustomerDTO(null);
		model.addAttribute("customerList", custDTOList);
		return "creg";
	}

	@RequestMapping(value = { "ereg" }, method = RequestMethod.POST)
	public String posteregPage(Model model, @RequestParam("eFiles") MultipartFile[] files, HttpServletRequest request,
			HttpServletResponse response) {
		// System.out.println("in posteregPage "+ request.getParameter("guid"));

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
		String removedFiles = request.getParameter("removedFiles");

		// String[] uploadedFilesArray =
		// request.getParameterValues("uploadedFiles");
		// List<String> uploadedFiles = new ArrayList<String>();;
		//
		// if(uploadedFilesArray!=null && uploadedFilesArray.length>0){
		// for(String s: uploadedFilesArray){
		// if(s!=null && s.length()>0){
		// s = s.replaceAll("[\\[\\]]","");
		// uploadedFiles.addAll(Arrays.asList(s.split(",")));
		// }
		// }
		// }
		// List<String> uploadedFilesTrimmed = new ArrayList<String>();;
		// for(String s: uploadedFiles){
		// uploadedFilesTrimmed.add(s.trim());
		// }
		Employee employeeCreated = null;
		try {
			employeeCreated = employeeService.registerEmployee(create, eid, efirstName, eLastName, gender,
					eQualification, eExperience, married, eDesignation, eDob, eDoj, eRole, eUserId, password, ePhone,
					eEmail, ePassport, eEmergencyContact, eCAddress, ePAddress, eNotes, eEmploymentType, eSection,
					files, removedFiles);
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
			model.addAttribute("error",
					"Following error occured while saving the employee. Please check. " + e.getMessage());
			e.printStackTrace();
			EmployeeDTO employeeDTO = new EmployeeDTO();
			if (eid != null && eid.length() > 0) {
				employeeDTO.setEmployeeId(Integer.valueOf(eid));
			}
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
			// commonDAO.loadRoleMap(roleMap);
			sectionMap.put("Section1", "Section1");
			sectionMap.put("Section2", "Section2");
			model.addAttribute("sections", sectionMap);
		}
		List<EmployeeDTO> employeeDTOList = employeeService.retrieveAllEmployeesDTO(null);

		model.addAttribute("employeeList", employeeDTOList);
		if (employeeCreated != null && employeeCreated.getEmployeeId() > 0) {
			model.addAttribute("result", "sucess");
			model.addAttribute("empCreated", employeeCreated.getEmployeeId());
			model.addAttribute("empCreatedName", employeeCreated.getFirstName());
		}
		return "ereg";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
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

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	@RequestMapping(value = { "/mreg" }, method = RequestMethod.GET)
	public String showmregPage(Model model,
			@RequestParam(value = "machineSelected", required = false) String machineSelected,
			HttpServletRequest request, HttpServletResponse response) {

		MachineDTO machineDTO = new MachineDTO();
		if (machineSelected != null) {
			Machine machine = commonService.findMachineById(Integer.valueOf(machineSelected));
			machineDTO = commonService.converMachineToDto(machine);
		}
		String machineSearched = request.getParameter("searchMachineInput");

		List<MachineDTO> machineDTOList = new ArrayList<MachineDTO>();
		List<Machine> machineList = commonService.retrieveAllMachines(machineSearched);

		for (Machine eachMachine : machineList) {
			MachineDTO eachMachineDTO = commonService.converMachineToDto(eachMachine);
			machineDTOList.add(eachMachineDTO);
		}
		model.addAttribute("machineSelected", machineDTO);

		model.addAttribute("machineList", machineDTOList);

		return "mreg";
	}

	@RequestMapping(value = { "mreg" }, method = RequestMethod.POST)
	public String postmregPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		String create = request.getParameter("create");
		String mid = request.getParameter("mid");
		String mName = request.getParameter("mName");
		String mCode = request.getParameter("mCode");
		String mAxle = request.getParameter("mAxle");
		String mCost = request.getParameter("mCost");
		Machine machineCreated = null;
		try {
			machineCreated = commonService.registerMachine(create, mid, mName, mCode, mAxle, mCost);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			MachineDTO machineDTO = new MachineDTO();
			if (mid != null && mid.length() > 0) {
				machineDTO.setMachineId(mid);
			}
			machineDTO.setAxle(mAxle);
			machineDTO.setCost(mCost);
			machineDTO.setCode(mCode);
			machineDTO.setName(mName);
			model.addAttribute("machineSelected", machineDTO);
		}
		List<MachineDTO> machineDTOList = new ArrayList<MachineDTO>();
		List<Machine> machines = commonService.retrieveAllMachines(null);

		for (Machine eachMachine : machines) {
			MachineDTO eachMachineDTO = commonService.converMachineToDto(eachMachine);
			machineDTOList.add(eachMachineDTO);
		}
		if (machineCreated != null && machineCreated.getMachineId() > 0) {
			model.addAttribute("result", "sucess");
			model.addAttribute("macCreated", machineCreated.getMachineId());
			model.addAttribute("macCreatedName", machineCreated.getMachineDesc());
		}
		model.addAttribute("machineList", machineDTOList);
		return "mreg";
	}

	@RequestMapping(value = { "/showpo" }, method = RequestMethod.GET)
	public String showpo(Model model, @RequestParam(value = "poSelected", required = false) String poSelected,
			HttpServletRequest request, HttpServletResponse response) {
		PoDTO poDTO = new PoDTO();
		if (poSelected != null) {
			poDTO = commonService.findPoDTOById(Integer.valueOf(poSelected));
		}
		Map<String, String> customerMap = new HashMap<String, String>();
		customerMap = customerService.retreiveCustomerMap();

		model.addAttribute("customers", customerMap);

		model.addAttribute("poSelected", poDTO);

		return "poreg";
	}

	@RequestMapping(value = { "/searchpo" }, method = RequestMethod.GET)
	public String searchpo(Model model, HttpServletRequest request, HttpServletResponse response) {
		String poSearched = request.getParameter("searchPoInput");

		List<PoDTO> poDTOs = commonService.retrieveAllPoDTOs(poSearched);

		model.addAttribute("poList", poDTOs);

		return "searchpo";
	}

	@RequestMapping(value = { "poreg" }, method = RequestMethod.POST)
	public String postporegPage(Model model, @RequestParam("eFiles") MultipartFile[] files, HttpServletRequest request,
			HttpServletResponse response) {
		String create = request.getParameter("create");
		String pid = request.getParameter("pid");
		String customerId = request.getParameter("customer");
		Customer customer = customerService.findById(Integer.valueOf(customerId));
		String poNumber = request.getParameter("poNumber");
		String poVersion = request.getParameter("poVersion");
		String poDate = request.getParameter("poDate");
		String vnoSender = request.getParameter("vnoSender");
		String poSender = request.getParameter("poSender");
		String poSenderDetails = request.getParameter("poSenderDetails");
		String senderEmail = request.getParameter("senderEmail");
		String senderPhone = request.getParameter("senderPhone");
		String senderFax = request.getParameter("senderFax");
		String notes = request.getParameter("notes");
		String totalValue = request.getParameter("totalValue");
		String[] matNo = request.getParameterValues("matNo");
		String[] matDesc = request.getParameterValues("matDesc");
		String[] unitPrice = request.getParameterValues("unitPrice");
		String[] quantity = request.getParameterValues("quantity");
		String[] discount = request.getParameterValues("discount");
		String[] value = request.getParameterValues("value");

		String removedFiles = request.getParameter("removedFiles");

		Map<String, List<String>> matMap = new HashMap<String, List<String>>();
		if (matNo != null && matNo.length > 0) {
			for (int i = 0; i < matNo.length; i++) {
				if (matNo[i] != null && matNo[i].length() > 0) {
					List<String> li = new ArrayList<String>();
					li.add(matDesc[i]);
					li.add(unitPrice[i]);
					li.add(quantity[i]);
					li.add(discount[i]);
					li.add(value[i]);
					matMap.put(matNo[i], li);
				}
			}
		}

		PurchaseOrder poCreated = null;
		PoDTO poDTO = null;
		try {
			poCreated = commonService.registerPO(create, pid, customer, poNumber, poVersion, poDate, vnoSender,
					poSender, poSenderDetails, senderEmail, senderPhone, senderFax, notes, totalValue, matMap, files,
					removedFiles);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			int purchaseid = (pid == null || pid.length() == 0) ? 0 : Integer.valueOf(pid);
			if (create.equalsIgnoreCase("false") && purchaseid > 0) {
				poDTO = commonService.findPoDTOById(purchaseid);
			} else {
				poDTO = new PoDTO();
				if (poNumber != null && poNumber.length() > 0) {
					poDTO.setPoId(poNumber);
				}
				poDTO.setCustomer(customerId);
				poDTO.setVersion(poVersion);
				poDTO.setDate(poDate);
				poDTO.setVendor(vnoSender);
				poDTO.setSender(poSender);
				poDTO.setSenderDetails(poSenderDetails);
				poDTO.setSenderEmail(senderEmail);
				poDTO.setSenderPhone(senderPhone);
				poDTO.setSenderFax(senderFax);
				poDTO.setNotes(notes);
				poDTO.setTotalValue(totalValue);
				List<String> poTools = new ArrayList<String>();

				for (int i = 0; i < matNo.length; i++) {
					if (matNo[i] != null && matNo[i].length() > 0) {
						poTools.add(matNo[i] + "|" + matDesc[i] + "|" + quantity[i] + "|" + unitPrice[i] + "|"
								+ discount[i] + "|" + value[i]);
					}
				}
				poDTO.setMaterial(poTools);
			}

			model.addAttribute("poSelected", poDTO);
		}

		if (poCreated != null) {
			poDTO = commonService.findPoDTOById(poCreated.getPid());
			model.addAttribute("poSelected", poDTO);
		}

		Map<String, String> customerMap = new HashMap<String, String>();
		customerMap = customerService.retreiveCustomerMap();

		model.addAttribute("customers", customerMap);

		return "poreg";
	}

	@RequestMapping(value = { "/createjobop" }, method = RequestMethod.GET)
	public String createjobop(Model model,
			@RequestParam(value = "operationSelected", required = false) String operationSelected,
			HttpServletRequest request, HttpServletResponse response) {

		JobOpDTO operationDTO = new JobOpDTO();
		if (operationSelected != null) {
			JobOperation jobOperation = commonService.findOperationById(Integer.valueOf(operationSelected));
			operationDTO = commonService.converOperationToDto(jobOperation);
		}
		String operationSearched = request.getParameter("searchOperationInput");

		List<JobOpDTO> operationDTOList = new ArrayList<JobOpDTO>();
		List<JobOperation> operationsList = commonService.retrieveAllOperations(operationSearched);

		for (JobOperation eachJobOperation : operationsList) {
			JobOpDTO eachJobOpDTO = commonService.converOperationToDto(eachJobOperation);
			operationDTOList.add(eachJobOpDTO);
		}
		model.addAttribute("operationSelected", operationDTO);

		model.addAttribute("operationList", operationDTOList);
		return "createjobop";
	}

	@RequestMapping(value = { "createjobop" }, method = RequestMethod.POST)
	public String postjobopPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		String create = request.getParameter("create");
		String oid = request.getParameter("oid");
		String oName = request.getParameter("oName");
		String oDescription = request.getParameter("oDescription");

		JobOperation jobOp = null;
		try {
			jobOp = commonService.registerJobOperation(create, oid, oName, oDescription);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			JobOpDTO jobOptDTO = new JobOpDTO();
			if (oid != null) {
				jobOptDTO.setId(Integer.valueOf(oid));
			}
			jobOptDTO.setName(oName);
			jobOptDTO.setDesc(oDescription);
			model.addAttribute("operationSelected", jobOptDTO);
		}
		List<JobOpDTO> jobOpList = new ArrayList<JobOpDTO>();
		List<JobOperation> jobOps = commonService.retrieveAllOperations(null);

		for (JobOperation jobOperation : jobOps) {
			JobOpDTO eachJobOpDTO = commonService.converOperationToDto(jobOperation);
			jobOpList.add(eachJobOpDTO);
		}
		model.addAttribute("operationList", jobOpList);

		if (jobOp != null && jobOp.getJoId() > 0) {
			model.addAttribute("result", "sucess");
			model.addAttribute("jobopCreated", jobOp.getJoId());
			model.addAttribute("jobopCreatedName", jobOp.getJobDesc());
		}
		return "createjobop";
	}

	@RequestMapping(value = { "/showjobcard" }, method = RequestMethod.GET)
	public String showjobcard(Model model,
			@RequestParam(value = "jobcardSelected", required = false) String jobcardSelected,
			HttpServletRequest request, HttpServletResponse response) {

		JobcardDTO jobcardDTO = new JobcardDTO();
		if (jobcardSelected != null) {
			jobcardDTO = jobcardService.findDTOById(Integer.valueOf(jobcardSelected));
		}

		Map<String, String> operations = new HashMap<String, String>();
		operations = commonService.getJobOperations();
		model.addAttribute("operations", operations);

		Map<String, String> customerMap = new HashMap<String, String>();
		customerMap = customerService.retreiveCustomerMap();
		model.addAttribute("customers", customerMap);

		Map<String, String> stateMap = new HashMap<String, String>();
		stateMap = commonService.getStatuses();
		model.addAttribute("states", stateMap);

		Map<String, String> poMap = new HashMap<String, String>();
		if (jobcardSelected != null) {
			poMap = commonService.findPOByCustId(jobcardDTO.getCustomer());
		}
		model.addAttribute("poList", poMap);

		Map<String, String> machineMap = new HashMap<String, String>();
		machineMap = commonService.getMachines();
		model.addAttribute("machines", machineMap);

		Map<String, String> empMap = new HashMap<String, String>();
		empMap = employeeService.getEmployees();
		model.addAttribute("employees", empMap);

		model.addAttribute("jobCardSelected", jobcardDTO);

		return "createjobcard";
	}

	@RequestMapping(value = { "createjobcard" }, method = RequestMethod.POST)
	public String postjobcard(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String create = request.getParameter("create");
		String jid = request.getParameter("jid");
		String name = request.getParameter("jname");
		String desc = request.getParameter("jdesc");
		String customerId = request.getParameter("customer");
		Customer customer = customerService.findById(Integer.valueOf(customerId));
		String po = request.getParameter("po");
		PurchaseOrder purchaseOrder = commonService.findPOById(po);
		String poItem = request.getParameter("poItem");
		
		String status = request.getParameter("status");
		String createdBy = request.getParameter("createdBy");
		String jobStart = request.getParameter("jobStart");
		String jobEnd = request.getParameter("jobEnd");
		String jobStartExisting = request.getParameter("jobStartExisting");
		String jobEndExisting = request.getParameter("jobEndExisting");
		int id = (jid == null || jid.length() == 0) ? 0 : Integer.valueOf(jid);
		if (create.equalsIgnoreCase("false") && id > 0) {
			jobStart = jobStartExisting;
			jobEnd = jobEndExisting;
		}
		String[] jobop = request.getParameterValues("jobop");
		String[] notes = request.getParameterValues("notes");
		String[] assignee = request.getParameterValues("assignee");
		String[] programmer = request.getParameterValues("programmer");
		String[] duration = request.getParameterValues("duration");
		String[] machine = request.getParameterValues("machine");
		String[] cost = request.getParameterValues("cost");
		String[] taskStatus = request.getParameterValues("taskStatus");
		Jobcard jobcardCreated = null;
		JobcardDTO jobcardDTO = null;

		try {
			jobcardCreated = jobcardService.registerJobcard(create, jid, name, desc, customer, purchaseOrder, poItem,status,
					createdBy, jobStart, jobEnd, jobop, notes, assignee, programmer, duration, machine, cost,
					taskStatus);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());

			int jobcardId = (jid == null || jid.length() == 0) ? 0 : Integer.valueOf(jid);
			if (create.equalsIgnoreCase("false") && jobcardId > 0) {
				jobcardDTO = jobcardService.findDTOById(jobcardId);
			} else {
				jobcardDTO = new JobcardDTO();
				if (jid != null && jid.length() > 0) {
					jobcardDTO.setId(Integer.valueOf(jid));
				}
				jobcardDTO.setName(name);
				jobcardDTO.setDesc(desc);
				jobcardDTO.setCustomer(customerId);
				jobcardDTO.setPo(po);
				jobcardDTO.setState(status);
				jobcardDTO.setCreatedBy(createdBy);
				jobcardDTO.setJobStart(jobStart);
				jobcardDTO.setJobEnd(jobEnd);
			}

			model.addAttribute("jobCardSelected", jobcardDTO);

		}

		if (jobcardCreated != null) {
			jobcardDTO = jobcardService.converJobcardToDto(jobcardCreated);
			model.addAttribute("jobCardSelected", jobcardDTO);
		}

		Map<String, String> operations = new HashMap<String, String>();
		operations = commonService.getJobOperations();
		model.addAttribute("operations", operations);

		Map<String, String> stateMap = new HashMap<String, String>();
		stateMap = commonService.getStatuses();
		model.addAttribute("states", stateMap);

		Map<String, String> machineMap = new HashMap<String, String>();
		machineMap = commonService.getMachines();
		model.addAttribute("machines", machineMap);

		Map<String, String> empMap = new HashMap<String, String>();
		empMap = employeeService.getEmployees();
		model.addAttribute("employees", empMap);

		Map<String, String> customerMap = new HashMap<String, String>();
		customerMap = customerService.retreiveCustomerMap();
		model.addAttribute("customers", customerMap);

		Map<String, String> poMap = new HashMap<String, String>();
		if (jobcardCreated != null) {
			poMap = commonService.findPOByCustId(jobcardDTO.getCustomer());
		} else {
			List<PurchaseOrder> purchaseOrders = commonService.retrieveAllPos(null);
			for (PurchaseOrder purchaseOrder2 : purchaseOrders) {
				poMap.put(String.valueOf(purchaseOrder2.getPid()), purchaseOrder2.getPoId());
			}
		}

		model.addAttribute("poList", poMap);

		return "createjobcard";
	}

	@RequestMapping(value = { "/searchjobcard" }, method = RequestMethod.GET)
	public String searchjobcard(Model model, HttpServletRequest request, HttpServletResponse response) {
		String jobcardSearched = request.getParameter("searchJobcardInput");
		List<JobcardDTO> jobcardDTOs = jobcardService.retrieveAllJobcardDTOs(jobcardSearched);
		model.addAttribute("jobcardList", jobcardDTOs);
		return "searchjobcard";
	}

	@RequestMapping(value = { "/filedownload/{regType}/{id}/{fname}.{ext}" }, method = RequestMethod.GET)
	public void downloadFile(Model model, @PathVariable("regType") String regType, @PathVariable("id") String id,
			@PathVariable("fname") String fname, @PathVariable("ext") String ext, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("in downloadFile " + regType + " , " + id + " , " + fname);
		String fileNameFromUI = fname + "." + ext;
		byte[] data = null;
		if (regType.equalsIgnoreCase("customer")) {
			data = customerService.findFile(Integer.valueOf(id), fileNameFromUI);
		} else if (regType.equalsIgnoreCase("employee")) {
			data = employeeService.findFile(Integer.valueOf(id), fileNameFromUI);
		} else if (regType.equalsIgnoreCase("po")) {
			data = commonService.findPOFile(Integer.valueOf(id), fileNameFromUI);
		}
		if (data != null) {
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileNameFromUI + "\""); // The
																											// Save
																											// As
																											// popup
																											// magic
																											// is
																											// done
																											// here.
																											// You
																											// can
																											// give
																											// it
																											// any
																											// filename
																											// you
																											// want,
																											// this
																											// only
																											// won't
																											// work
																											// in
																											// MSIE,
																											// it
																											// will
																											// use
																											// current
																											// request
																											// URL
																											// as
																											// filename
																											// instead.

			// Write file to response.
			OutputStream output;
			try {
				output = response.getOutputStream();
				output.write(data);
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@RequestMapping(value = { "/vreg" }, method = RequestMethod.GET)
	public String showvregPage(Model model,
			@RequestParam(value = "vendorSelected", required = false) String vendorSelected, HttpServletRequest request,
			HttpServletResponse response) {

		VendorDTO vendorDTO = new VendorDTO();
		if (vendorSelected != null) {
			vendorDTO = commonService.findVendorDTOById(Integer.valueOf(vendorSelected));
		}
		String vendorSearched = request.getParameter("searchVendorInput");

		List<VendorDTO> vendorDTOs = commonService.retrieveAllVendorDTO(vendorSearched);

		model.addAttribute("vendorSelected", vendorDTO);

		model.addAttribute("vendorList", vendorDTOs);

		return "vreg";
	}

	@RequestMapping(value = { "vreg" }, method = RequestMethod.POST)
	public String postvregPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		String create = request.getParameter("create");
		String vid = request.getParameter("vid");
		String vName = request.getParameter("vName");
		String vAddress = request.getParameter("vAddress");

		Vendor vendor = null;
		try {
			vendor = commonService.registerVendor(create, vid, vName, vAddress);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			VendorDTO vendorDTO = new VendorDTO();
			if (vid != null && vid.length() > 0) {
				vendorDTO.setVendorId(Integer.valueOf(vid));
			}
			vendorDTO.setVendorName(vName);
			vendorDTO.setAddress(vAddress);
			model.addAttribute("vendorSelected", vendorDTO);
		}

		if (vendor != null && vendor.getVendorId() > 0) {
			model.addAttribute("result", "sucess");
			model.addAttribute("venCreated", vendor.getVendorId());
			model.addAttribute("venCreatedName", vendor.getVendorName());
		}

		List<VendorDTO> vendorDTOs = commonService.retrieveAllVendorDTO(null);
		model.addAttribute("vendorList", vendorDTOs);

		return "vreg";
	}
	
	@RequestMapping(value = { "/makereg" }, method = RequestMethod.GET)
	public String showmakeregPage(Model model,
			@RequestParam(value = "makeSelected", required = false) String makeSelected, HttpServletRequest request,
			HttpServletResponse response) {

		MakeDTO makeDTO = new MakeDTO();
		if (makeSelected != null) {
			makeDTO = commonService.findMakeDTOById(Integer.valueOf(makeSelected));
		}
		String makeSearched = request.getParameter("searchMakeInput");

		List<MakeDTO> makeDTOs = commonService.retrieveAllMakeDTO(makeSearched);

		model.addAttribute("makeSelected", makeDTO);

		model.addAttribute("makeList", makeDTOs);

		return "makereg";
	}

	@RequestMapping(value = { "makereg" }, method = RequestMethod.POST)
	public String postmakeregPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		String create = request.getParameter("create");
		String makeid = request.getParameter("makeid");
		String makeName = request.getParameter("makeName");

		Make make = null;
		try {
			make = commonService.registerMake(create, makeid, makeName);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			MakeDTO makeDTO = new MakeDTO();
			if (makeid != null && makeid.length() > 0) {
				makeDTO.setId(Integer.valueOf(makeid));
			}
			makeDTO.setName(makeName);
			model.addAttribute("makeSelected", makeDTO);
		}

		if (make != null && make.getMakeId() > 0) {
			model.addAttribute("result", "sucess");
			model.addAttribute("makeCreated", make.getMakeId());
			model.addAttribute("makeCreatedName", make.getMakeName());
		}

		List<MakeDTO> makeDTOs = commonService.retrieveAllMakeDTO(null);

		model.addAttribute("makeList", makeDTOs);
		return "makereg";
	}
	
	
	@RequestMapping(value = { "/showrawm" }, method = RequestMethod.GET)
	public String showrm(Model model,
			@RequestParam(value = "rmSelected", required = false) String rmSelected,
			HttpServletRequest request, HttpServletResponse response) {
		
		RawMaterialDTO rawMaterialDTO =  new RawMaterialDTO();
		if (rmSelected != null && rmSelected.length() > 0) {
			rawMaterialDTO = commonService.findRmDTOById(Integer.valueOf(rmSelected));
		}
		model.addAttribute("rm", rawMaterialDTO);
		
		Map<String, String> vendorMap = new HashMap<String, String>();
		vendorMap = commonService.getVendors();
		model.addAttribute("vendors", vendorMap);

		return "addtorawm";
	}

	@RequestMapping(value = { "/searchrawm" }, method = RequestMethod.GET)
	public String searchrm(Model model, HttpServletRequest request, HttpServletResponse response) {
		String rmSearched = request.getParameter("searchRmInput");
		List<RawMaterialDTO> rawMaterialDTOs = commonService.retrieveAllRmDTO(rmSearched);
		model.addAttribute("rmList", rawMaterialDTOs);
		return "searchrawm";
	}

	@RequestMapping(value = { "/addtorawm" }, method = RequestMethod.POST)
	public String addtorm(Model model, HttpServletRequest request, HttpServletResponse response) {
	
		String create = request.getParameter("create");
		String rmid = request.getParameter("rmid");
		String rawmname = request.getParameter("rawmname");
		String vendor = request.getParameter("vendor");
		String length = request.getParameter("length");
		String width = request.getParameter("width");
		String thickness = request.getParameter("thickness");
		String authouredby = request.getParameter("authouredby");
		String quantity = request.getParameter("quantity");

		RawMaterialDTO rawMaterialDTO = new RawMaterialDTO();
		RawMaterial rawMaterialCreated = null;
		try {
			rawMaterialCreated = commonService.registerRawMaterial(create, rmid, rawmname, vendor, length, width, thickness, 
					authouredby, quantity);

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e.getMessage());
			if (rmid != null && rmid.length() > 0) {
				rawMaterialDTO.setId(Integer.valueOf(rmid));
			}
			rawMaterialDTO.setRawmname(rawmname);
			rawMaterialDTO.setVendor(vendor);
			rawMaterialDTO.setLength(length);
			rawMaterialDTO.setWidth(width);
			rawMaterialDTO.setThickness(thickness);
			rawMaterialDTO.setAuthouredby(authouredby);
			rawMaterialDTO.setQuantity(quantity);
			model.addAttribute("rmSelected", rawMaterialDTO);
		}

		if (rawMaterialCreated != null && rawMaterialCreated.getRawMaterialId() > 0) {
			model.addAttribute("result", "sucess");
			model.addAttribute("rmCreatedId", rawMaterialCreated.getRawMaterialId());
			model.addAttribute("rmCreatedCode", rawMaterialCreated.getDesciption());
		}
		
		Map<String, String> vendorMap = new HashMap<String, String>();
		vendorMap = commonService.getVendors();
		model.addAttribute("vendors", vendorMap);
		
		return "addtorawm";
	}
}
package com.esteeminfo.proauto.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dao.EmployeeDao;
import com.esteeminfo.proauto.dao.FileUploadDAO;
import com.esteeminfo.proauto.dto.EmployeeDTO;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.Role;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	final static Logger logger = Logger.getLogger(EmployeeServiceImpl.class);

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired(required=true)
	private EmployeeDao employeeDao ;

	@Autowired(required=true)
	private FileUploadDAO fileUploadDAO ;
	
	public Employee findById(int id) {
		return employeeDao.findById(id);
	}

	public Employee findByUser(String user) {
		return employeeDao.findByUser(user);
	}

	public List<Employee> retrieveAllEmployees(String employeeSearched) {
		return employeeDao.retrieveAllEmployees(employeeSearched);
	}

	public Employee registerEmployee(String create, String eid, String efirstName, String eLastName, String gender,
			String eQualification, String eExperience, String married, String eDesignation, String eDob, String eDoj,
			String eRole, String eUserId, String password, String ePhone, String eEmail, String ePassport,
			String eEmergencyContact, String eCAddress, String ePAddress, String eNotes, String eEmploymentType,
			String eSection,MultipartFile[] files,List<String> uploadedFiles) throws Exception {
		Employee empCreated = employeeDao.registerEmployee(create, eid, efirstName, eLastName, gender, eQualification, eExperience, married, eDesignation, eDob, eDoj, eRole, eUserId, password, ePhone, eEmail, ePassport, eEmergencyContact, eCAddress, ePAddress, eNotes, eEmploymentType, eSection);
		
		Set<FilesUpload> filesUploads = new HashSet<FilesUpload>();
		if(files!=null){
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				if(file.getOriginalFilename()!=null && file.getOriginalFilename().length()>0){
					logger.info("uploading file "+file.getOriginalFilename());
					try {
						byte[] bytes = file.getBytes();
						FilesUpload filesUpload =  fileUploadDAO.saveFile(file.getOriginalFilename(), bytes);
						filesUploads.add(filesUpload);
						
					} catch (Exception e) {
					}	
				}
				
			}
		}
		Set<FilesUpload> existingFiles = empCreated.getFilesUploads();
		Set<FilesUpload> existingFilesNew = new HashSet<FilesUpload>();
		if(existingFiles!=null){
			for(FilesUpload existingFile : existingFiles){
				if(uploadedFiles.contains(existingFile.getFileName())){
					existingFilesNew.add(existingFile);
				}
			}
		}
		filesUploads.addAll(existingFilesNew);
		empCreated = employeeDao.addFilesToEmployee(empCreated.getEmployeeId(), filesUploads);
		return empCreated;
	}

	public EmployeeDTO converEmployeeToDto(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		//if(employee.getEmployeeId()>0){
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
			if(employee.getFilesUploads()!=null && employee.getFilesUploads().size()>0){
				List<String> files =  new ArrayList<String>();
				for(FilesUpload filesUpload: employee.getFilesUploads()){
					files.add(filesUpload.getFileName());
				}
				employeeDTO.setFiles(files);
			}
		//}
		return employeeDTO;
	}

	public FilesUpload findFile(Integer eid, String fname) {
		Employee e = findById(eid);
		Set<FilesUpload> files = e.getFilesUploads();
		for(FilesUpload filesUpload : files){
			if(filesUpload.getFileName().equalsIgnoreCase(fname)){
				return filesUpload;
			}
		}
		return null;
	}
	
}

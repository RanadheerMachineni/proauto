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
import com.esteeminfo.proauto.dao.JobcardDao;
import com.esteeminfo.proauto.dto.EmployeeDTO;
import com.esteeminfo.proauto.dto.JobcardDTO;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.Jobcard;
import com.esteeminfo.proauto.entity.Role;

@Service("jobcardService")
@Transactional
public class JobcardServiceImpl implements JobcardService {

	final static Logger logger = Logger.getLogger(JobcardServiceImpl.class);

	public static SimpleDateFormat ui_date_format =  new SimpleDateFormat("MM/dd/yyyy");
	
	@Autowired(required=true)
	private JobcardDao jobcardDao ;

	public Jobcard findById(int id) {
		return jobcardDao.findById(id);
	}

	public List<Jobcard> retrieveAllJobcards(String jobcardSearched) {
		return jobcardDao.retrieveAllJobcards(jobcardSearched);
	}

	public Jobcard registerJobcard() throws Exception {
		return jobcardDao.registerJobcard();
	}

	public JobcardDTO converJobcardToDto(Jobcard jobcard) {
		return null;
	}
	
	
}

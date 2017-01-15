package com.esteeminfo.proauto.dao;

import java.util.List;
import java.util.Set;


import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.Jobcard;

public interface JobcardDao {

	Jobcard findById(int id);

	List<Jobcard> retrieveAllJobcards(String jobcardSearched);

	Jobcard registerJobcard();
	
}


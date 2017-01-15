package com.esteeminfo.proauto.service;

import java.util.List;

import com.esteeminfo.proauto.dto.JobcardDTO;
import com.esteeminfo.proauto.entity.Jobcard;

public interface JobcardService {

	Jobcard findById(int id);
	
	List<Jobcard> retrieveAllJobcards(String jobcardSearched);

	Jobcard registerJobcard() throws Exception;
	
	JobcardDTO converJobcardToDto(Jobcard jobcard);

}

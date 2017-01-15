package com.esteeminfo.proauto.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.esteeminfo.proauto.entity.Jobcard;

@Repository("jobcardDao")
public class JobcardDaoImpl extends AbstractDao implements JobcardDao {

	public Jobcard findById(int id) {
		return null;
	}

	public List<Jobcard> retrieveAllJobcards(String jobcardSearched) {
		return null;
	}

	public Jobcard registerJobcard() {
		return null;
	}
	
}

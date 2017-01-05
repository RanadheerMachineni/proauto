package com.esteeminfo.proauto.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.dto.MachineDTO;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.Machine;

public interface CommonService {

	Machine findMachineById(int id);
	
	List<Machine> retrieveAllMachines(String machineSearched);

	Machine registerMachine(String create, String mid, String mName, String mCode, String mAxle, String mCost) throws Exception;
	
	MachineDTO converMachineToDto(Machine machine);

}

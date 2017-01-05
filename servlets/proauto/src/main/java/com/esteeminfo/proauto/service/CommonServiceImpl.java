package com.esteeminfo.proauto.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esteeminfo.proauto.dao.CommonDAO;
import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.dto.MachineDTO;
import com.esteeminfo.proauto.entity.Contact;
import com.esteeminfo.proauto.entity.FilesUpload;
import com.esteeminfo.proauto.entity.Machine;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {

	final static Logger logger = Logger.getLogger(CommonServiceImpl.class);

	@Autowired(required=true)
	private CommonDAO commonDAO;
	
	public Machine findMachineById(int id) {
		return commonDAO.findMachineById(id);
	}

	public List<Machine> retrieveAllMachines(String machineSearched) {
		return commonDAO.retrieveAllMachines(machineSearched);
	}

	public MachineDTO converMachineToDto(Machine machine) {
		MachineDTO machineDTO =  new MachineDTO();
		machineDTO.setMachineId(String.valueOf(machine.getMachineId()));
		machineDTO.setName(machine.getMachineDesc());
		machineDTO.setCode(machine.getMachineCodeType());
		machineDTO.setAxle(machine.getMachineAxle());
		machineDTO.setCost(machine.getMachineCost());
		return machineDTO;
	}

	public Machine registerMachine(String create, String mid, String mName, String mCode, String mAxle, String mCost)
			throws Exception {
		return commonDAO.registerMachine(create, mid, mName, mCode, mAxle, mCost);
	}
}

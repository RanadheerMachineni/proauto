package com.esteeminfo.proauto.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.dto.CustomerDTO;
import com.esteeminfo.proauto.dto.EmployeeDTO;
import com.esteeminfo.proauto.dto.JobOpDTO;
import com.esteeminfo.proauto.dto.MachineDTO;
import com.esteeminfo.proauto.dto.MakeDTO;
import com.esteeminfo.proauto.dto.PoDTO;
import com.esteeminfo.proauto.dto.PurchaseDTO;
import com.esteeminfo.proauto.dto.RawMaterialDTO;
import com.esteeminfo.proauto.dto.VendorDTO;
import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.JobOperation;
import com.esteeminfo.proauto.entity.Machine;
import com.esteeminfo.proauto.entity.Make;
import com.esteeminfo.proauto.entity.Purchase;
import com.esteeminfo.proauto.entity.PurchaseOrder;
import com.esteeminfo.proauto.entity.RawMaterial;
import com.esteeminfo.proauto.entity.Vendor;

public interface CommonService {

	Machine findMachineById(int id);
	
	List<Machine> retrieveAllMachines(String machineSearched);

	Machine registerMachine(String create, String mid, String mName, String mCode, String mAxle, String mCost) throws Exception;
	
	MachineDTO converMachineToDto(Machine machine);

	PurchaseOrder findPOById(String poSelected);
	
	PoDTO findPoDTOById(int id);
	
	Map<String,String> findPOByCustId(String customer);

	List<PurchaseOrder> retrieveAllPos(String poSearched);
	
	List<PoDTO> retrieveAllPoDTOs(String poSearched);

	JobOpDTO converOperationToDto(JobOperation jobOperation);

	JobOperation findOperationById(Integer valueOf);

	List<JobOperation> retrieveAllOperations(String operationSearched);

	JobOperation registerJobOperation(String create, String oid, String oName, String oDescription);

	PurchaseOrder registerPO(String create, String pid, Customer customer, String poNumber, String poVersion, String poDate,
			String vnoSender, String poSender, String poSenderDetails, String senderEmail, String senderPhone,
			String senderFax, String notes, String totalValue, Map<String, List<String>> matMap, MultipartFile[] files, String removedFiles) throws Exception;

	Map<String, String> getJobOperations();

	Map<String, String> getStatuses();

	Map<String, String> getMachines();

	byte[] findPOFile(Integer valueOf, String fileNameFromUI);

	PurchaseDTO findPurchaseDTOById(Integer valueOf);

	List<PurchaseDTO> retrieveAllPurchaseDTO(String purchaseSearched);

	Purchase registerPurchase(String create, String parid, String particular, String code, String make, String unit,
			String desc, String type, String authouredby, String additems) throws Exception;

	Map<String, String> findJobsByPO(String po);

	Map<String, String> getInventoryItems();

	Map<String, String> findMakesOfTool(String tool);

	VendorDTO findVendorDTOById(Integer valueOf);

	List<VendorDTO> retrieveAllVendorDTO(String vendorSearched);

	Vendor registerVendor(String create, String vid, String vName, String vAddress);

	MakeDTO findMakeDTOById(Integer valueOf);

	List<MakeDTO> retrieveAllMakeDTO(String makeSearched);

	Make registerMake(String create, String makeid, String makeName);

	Map<String, String> retreiveMakeMap();

	Map<String, String> retreiveTypeMap();

	Map<String, String> retreiveUnitMap();

	RawMaterial registerRawMaterial(String create, String rmid, String rawmname, String vendor, String length,
			String width, String thickness, String authouredby, String quantity);

	List<RawMaterialDTO> retrieveAllRmDTO(String rmSearched);

	RawMaterialDTO findRmDTOById(Integer valueOf);

	Map<String, String> getVendors();

}

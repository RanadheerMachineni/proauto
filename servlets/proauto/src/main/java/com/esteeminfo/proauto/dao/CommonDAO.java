package com.esteeminfo.proauto.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.esteeminfo.proauto.entity.Customer;
import com.esteeminfo.proauto.entity.Employee;
import com.esteeminfo.proauto.entity.EmployeeFile;
import com.esteeminfo.proauto.entity.JobOperation;
import com.esteeminfo.proauto.entity.Jobcard;
import com.esteeminfo.proauto.entity.Machine;
import com.esteeminfo.proauto.entity.Make;
import com.esteeminfo.proauto.entity.PoFile;
import com.esteeminfo.proauto.entity.PoFileData;
import com.esteeminfo.proauto.entity.PoTool;
import com.esteeminfo.proauto.entity.Purchase;
import com.esteeminfo.proauto.entity.PurchaseHistory;
import com.esteeminfo.proauto.entity.PurchaseOrder;
import com.esteeminfo.proauto.entity.RawMaterial;
import com.esteeminfo.proauto.entity.RawMaterialHistory;
import com.esteeminfo.proauto.entity.Status;
import com.esteeminfo.proauto.entity.Tooltype;
import com.esteeminfo.proauto.entity.Unit;
import com.esteeminfo.proauto.entity.Vendor;

@Repository("commonDAO")
public class CommonDAO extends AbstractDao {

	public static SimpleDateFormat ui_date_format = new SimpleDateFormat("MM/dd/yyyy");

	public void loadRoleMap(Map<String, String> roleMap) {

	}

	public Machine findMachineById(int id) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery("select e from Machine e where e.machineId=:eid");
		q.setParameter("eid", id);
		List<Machine> result = q.getResultList();
		if (result == null || result.size() == 0) {
			return null;
		}
		Machine e = result.get(0);
		return e;
	}

	public List<Machine> retrieveAllMachines(String machineSearched) {
		EntityManager entityManager = getEntityManager();
		String query = "select e from Machine e";
		if (machineSearched != null && machineSearched.length() > 0) {
			query += " where e.machineDesc LIKE '" + machineSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<Machine> result = q.getResultList();
		return result;
	}

	public Machine registerMachine(String create, String mid, String mName, String mCode, String mAxle, String mCost)
			throws Exception {
		int machineId = (mid == null || mid.length() == 0) ? 0 : Integer.valueOf(mid);

		if (create.equalsIgnoreCase("false") && machineId > 0) {
			EntityManager entityManager = getEntityManager();
			Machine existingMachine = null;
			Query q = entityManager.createQuery("select e from Machine e where e.machineId=:eid");
			q.setParameter("eid", machineId);
			List<Machine> result = q.getResultList();
			if (result != null || result.size() > 0) {
				existingMachine = result.get(0);
			}

			existingMachine.setMachineDesc(mName);
			existingMachine.setMachineCodeType(mCode);
			existingMachine.setMachineAxle(mAxle);
			existingMachine.setMachineCost(mCost);
			entityManager.persist(existingMachine);
			return existingMachine;
		} else {
			EntityManager entityManager = getEntityManager();
			Machine machineCreated = new Machine();
			machineCreated.setMachineDesc(mName);
			machineCreated.setMachineCodeType(mCode);
			machineCreated.setMachineAxle(mAxle);
			machineCreated.setMachineCost(mCost);
			entityManager.persist(machineCreated);
			return machineCreated;
		}
	}

	public PurchaseOrder findPOById(int valueOf) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery("select e from PurchaseOrder e where e.pid=:pid");
		q.setParameter("pid", Integer.valueOf(valueOf));
		List<PurchaseOrder> result = q.getResultList();
		if (result == null || result.size() == 0) {
			return null;
		}
		PurchaseOrder e = result.get(0);
		return e;
	}

	public List<PurchaseOrder> retrieveAllPos(String poSearched) {
		EntityManager entityManager = getEntityManager();
		String query = "select e from PurchaseOrder e";
		if (poSearched != null && poSearched.length() > 0) {
			query += " where e.poId LIKE '" + poSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<PurchaseOrder> result = q.getResultList();
		return result;
	}

	private PurchaseOrder findPOByNumber(String poNumber) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery("select e from PurchaseOrder e where e.poId=:userId");
		q.setParameter("userId", poNumber);
		List<PurchaseOrder> result = q.getResultList();
		if (result == null || result.size() == 0) {
			return null;
		}
		return result.get(0);
	}

	public JobOperation findOperationById(Integer valueOf) {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createQuery("select e from JobOperation e where e.joId=:eid");
		q.setParameter("eid", valueOf);
		List<JobOperation> result = q.getResultList();
		if (result == null || result.size() == 0) {
			return null;
		}
		JobOperation e = result.get(0);
		return e;
	}

	public List<JobOperation> retrieveAllOperations(String operationSearched) {
		EntityManager entityManager = getEntityManager();
		String query = "select e from JobOperation e";
		if (operationSearched != null && operationSearched.length() > 0) {
			query += " where e.jobName LIKE '" + operationSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<JobOperation> result = q.getResultList();
		return result;
	}

	public JobOperation registerOperation(String create, String oid, String oName, String oDescription) {
		int joid = (oid == null || oid.length() == 0) ? 0 : Integer.valueOf(oid);

		if (create.equalsIgnoreCase("false") && joid > 0) {
			EntityManager entityManager = getEntityManager();
			JobOperation existingMachine = null;
			Query q = entityManager.createQuery("select e from JobOperation e where e.joId=:eid");
			q.setParameter("eid", joid);
			List<JobOperation> result = q.getResultList();
			if (result != null || result.size() > 0) {
				existingMachine = result.get(0);
			}
			existingMachine.setJobName(oName);
			existingMachine.setJobDesc(oDescription);
			entityManager.persist(existingMachine);
			return existingMachine;
		} else {
			EntityManager entityManager = getEntityManager();
			JobOperation machineCreated = new JobOperation();
			machineCreated.setJobName(oName);
			machineCreated.setJobDesc(oDescription);
			entityManager.persist(machineCreated);
			return machineCreated;
		}

	}

	public PurchaseOrder registerPO(String create, String pid, Customer customer, String poNumber, String poVersion,
			String poDate, String vnoSender, String poSender, String poSenderDetails, String senderEmail,
			String senderPhone, String senderFax, String notes, String totalValue, Map<String, List<String>> matMap,
			MultipartFile[] files, String removedFiles) throws Exception {
		int purchaseid = (pid == null || pid.length() == 0) ? 0 : Integer.valueOf(pid);

		if (poNumber != null && poNumber.length() > 0) {
			PurchaseOrder existingPOByNumber = findPOByNumber(poNumber);
			if (existingPOByNumber != null && (purchaseid == 0 || (existingPOByNumber.getPid() != purchaseid))) {
				throw new Exception("PurchaseOrder with given PO number already exist. Please select other PO number");
			}
		}
		java.util.Date javaDatePoDate = null;
		if (poDate != null) {
			javaDatePoDate = ui_date_format.parse(poDate);
		}
		PurchaseOrder purchaseOrder = null;
		if (create.equalsIgnoreCase("false") && purchaseid > 0) {
			purchaseOrder = findPOById(purchaseid);
			if (purchaseOrder.getJobcards() != null && purchaseOrder.getJobcards().size() > 0) {
				throw new Exception("Can not edit this PurchaseOrder as Jobcard/s are already prepared with this PO");
			}
			purchaseOrder.getPoTools().clear();
			List<String> removedFilesArray = new ArrayList<String>();
			removedFilesArray.addAll(Arrays.asList(removedFiles.split(",")));
			List<PoFile> listTobeRemoved = new ArrayList<PoFile>();

			for (String file : removedFilesArray) {
				for (PoFile eF : purchaseOrder.getPoFiles()) {
					if (eF.getFileName().equalsIgnoreCase(file)) {
						listTobeRemoved.add(eF);
					}
				}
			}
			for (PoFile rf : listTobeRemoved) {
				purchaseOrder.removePoFile(rf);
			}
			entityManager.persist(purchaseOrder);
		} else {
			purchaseOrder = new PurchaseOrder();
		}

		purchaseOrder.setPoId(poNumber);
		purchaseOrder.setPoVersion(poVersion);
		purchaseOrder.setPdate(javaDatePoDate);
		purchaseOrder.setVnoSender(vnoSender);
		purchaseOrder.setSenderContact(poSender);
		purchaseOrder.setSenderDetails(poSenderDetails);
		purchaseOrder.setSenderEmail(senderEmail);
		purchaseOrder.setSenderPhone(senderPhone);
		purchaseOrder.setSenderFax(senderFax);
		purchaseOrder.setNotes(notes);
		purchaseOrder.setTotalValue(totalValue);
		purchaseOrder.setCustomer(customer);
		entityManager.persist(purchaseOrder);

		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				if (file.getOriginalFilename() != null && file.getOriginalFilename().length() > 0) {
					try {
						PoFile poFile = new PoFile();
						poFile.setFileName(file.getOriginalFilename());
						poFile.setPurchaseOrder(purchaseOrder);

						entityManager.persist(poFile);

						byte[] bytes = file.getBytes();
						PoFileData poFileData = new PoFileData();
						poFileData.setFileData(bytes);
						poFileData.setPoFile(poFile);
						entityManager.persist(poFileData);

						purchaseOrder.addPoFile(poFile);

					} catch (Exception e) {
					}
				}

			}
		}

		Set<PoTool> poList = new HashSet<PoTool>();
		for (Entry<String, List<String>> eachEntry : matMap.entrySet()) {
			PoTool poTool = new PoTool();
			poTool.setPurchaseOrder(purchaseOrder);
			poTool.setMatNo(eachEntry.getKey());
			poTool.setMatDesc(eachEntry.getValue().get(0));
			poTool.setMatUnitprice(eachEntry.getValue().get(1));
			poTool.setMatQuantiy(eachEntry.getValue().get(2));
			poTool.setDiscount(eachEntry.getValue().get(3));
			poTool.setMatValue(eachEntry.getValue().get(4));
			poTool.setPurchaseOrder(purchaseOrder);
			entityManager.persist(poTool);
			poList.add(poTool);
		}

		if (purchaseOrder.getPoTools() != null) {
			purchaseOrder.getPoTools().addAll(poList);
		} else {
			purchaseOrder.setPoTools(poList);
		}
		entityManager.persist(purchaseOrder);
		return purchaseOrder;
	}

	public List<JobOperation> getJobOperations() {
		Query q = entityManager.createNamedQuery("JobOperation.findAll");
		return q.getResultList();
	}

	public List<Status> getStatuses() {
		Query q = entityManager.createNamedQuery("Status.findAll");
		return q.getResultList();
	}

	public List<Machine> getMachines() {
		Query q = entityManager.createNamedQuery("Machine.findAll");
		return q.getResultList();
	}

	public List<PurchaseOrder> findPosByCustomer(String customer) {
		EntityManager entityManager = getEntityManager();
		String query = "select e from PurchaseOrder e where e.customer=" + Integer.valueOf(customer);
		Query q = entityManager.createQuery(query);
		List<PurchaseOrder> result = q.getResultList();
		return result;
	}

	public List<String> findPOFileNames(int id) {
		List<String> list = null;
		Query q1 = entityManager.createNativeQuery("select file_name from po_files where pid=" + id);
		list = q1.getResultList();
		return list;
	}

	public byte[] findPoFileData(Integer pid, String fileName) {
		Query query = entityManager.createNativeQuery(
				"select pfd.* from po_files pof,po_file_data pfd where pof.upload_id=pfd.upload_id and pof.file_name='"
						+ fileName + "' and pof.pid=" + pid,
				PoFileData.class);
		PoFileData poFileData = (PoFileData) query.getSingleResult();
		return poFileData.getFileData();
	}

	public Purchase findPurchaseById(Integer valueOf) {
		EntityManager entityManager = getEntityManager();
		Purchase purchase = entityManager.find(Purchase.class, valueOf);
		return purchase;
	}

	public List<Purchase> retrieveAllPurchase(String purchaseSearched) {
		EntityManager entityManager = getEntityManager();
		String query = "SELECT p FROM Purchase p";
		if (purchaseSearched != null && purchaseSearched.length() > 0) {
			query += " where p.code LIKE '" + purchaseSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<Purchase> result = q.getResultList();
		return result;
	}

	public Purchase registerPurchase(String create, String parid, String particular, String code, String make,
			String unit, String desc, String type, String authouredby, String additems) throws Exception {
		int purchaseId = (parid == null || parid.length() == 0) ? 0 : Integer.valueOf(parid);
		Purchase purchase = null;
		if (code.length() > 0 && make.length() > 0) {
			 purchase=findPurchaseByCodeAndMake(code,make);
			 if (purchase!=null && (purchaseId==0 ||
			 (purchase.getParticularId() != purchaseId))) {
				 throw new Exception("Item exist with Code '"+code+"' and Make '"+make+"'. Please update existing item");
			 }
		}
		if (create.equalsIgnoreCase("false") && purchaseId > 0) {
			purchase = findPurchaseById(purchaseId);
			if (additems != null && additems.length() > 0) {
				purchase.setRepository(purchase.getRepository() + Integer.valueOf(additems));
			}
		} else {
			purchase = new Purchase();
			purchase.setDoc(new Date());
			if (additems != null && additems.length() > 0) {
				purchase.setRepository(Integer.valueOf(additems));
			}
		}
		purchase.setDou(new Date());
		purchase.setParticular(particular);
		purchase.setCode(code);
		purchase.setMake(getEntityManager().find(Make.class, Integer.valueOf(make)));
		purchase.setUnit(getEntityManager().find(Unit.class, Integer.valueOf(unit)));
		purchase.setDesciption(desc);
		purchase.setAuthouredby(authouredby);
		purchase.setTooltypeId(Integer.valueOf(type));
		entityManager.persist(purchase);

		if (additems != null && additems.length() > 0) {
			Set<PurchaseHistory> history = purchase.getPurchaseHistories();
			PurchaseHistory purchaseHistory = new PurchaseHistory();
			purchaseHistory.setPurchase(purchase);
			purchaseHistory.setAuthouredby(authouredby);
			purchaseHistory.setQuantity(Integer.valueOf(additems));
			purchaseHistory.setAdddate(new Date());
			purchaseHistory.setStatus("a");
			history.add(purchaseHistory);
			if (purchase.getPurchaseHistories() == null) {
				purchase.setPurchaseHistories(history);
			} else {
				purchase.getPurchaseHistories().addAll(history);
			}
			entityManager.persist(purchase);
		}

		return purchase;
	}

	private Purchase findPurchaseByCodeAndMake(String code, String make) {
		 EntityManager entityManager = getEntityManager();
		 Query q = entityManager.createQuery( "SELECT p FROM Purchase p where p.code=:code and p.make.makeId="+Integer.valueOf(make) );
		 q.setParameter("code", code);
		 List<Purchase> result = q.getResultList();
		 if(result == null || result.size() ==0){
		 return null;
		 }
		 return result.get(0);
	}

	public Vendor findVendorById(String valueOf) {
		EntityManager entityManager = getEntityManager();
		Vendor vendor = entityManager.find(Vendor.class, Integer.valueOf(valueOf));
		return vendor;
	}

	public List<Vendor> retrieveAllVendors(String vendorSearched) {
		EntityManager entityManager = getEntityManager();
		String query = "select v from Vendor v";
		if (vendorSearched != null && vendorSearched.length() > 0) {
			query += " where v.vendorName LIKE '" + vendorSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<Vendor> result = q.getResultList();
		return result;

	}

	public Vendor registerVendor(String create, String vid, String vName, String vAddress) {

		int vendorId = (vid == null || vid.length() == 0) ? 0 : Integer.valueOf(vid);

		Vendor vendor = null;
		if (create.equalsIgnoreCase("false") && vendorId > 0) {
			vendor = findVendorById(vid);
		}else{
			vendor =  new Vendor();
		}
		vendor.setVendorName(vName);
		vendor.setAddress(vAddress);
		entityManager.persist(vendor);
		return vendor;
	}

	public Make findMakeById(String valueOf) {
		EntityManager entityManager = getEntityManager();
		Make make = entityManager.find(Make.class, Integer.valueOf(valueOf));
		return make;
	}

	public Make registerMake(String create, String makeid, String makeName) {
		int mId = (makeid == null || makeid.length() == 0) ? 0 : Integer.valueOf(makeid);

		Make make = null;
		if (create.equalsIgnoreCase("false") && mId > 0) {
			make = findMakeById(makeid);
		}else{
			make =  new Make();
		}
		make.setMakeName(makeName);
		entityManager.persist(make);
		return make;
	}

	public List<Make> retrieveAllMakes(String makeSearched) {
		EntityManager entityManager = getEntityManager();
		String query = "select m from Make m";
		if (makeSearched != null && makeSearched.length() > 0) {
			query += " where m.makeName LIKE '" + makeSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<Make> result = q.getResultList();
		return result;

	}

	public List<Tooltype> retrieveAllToolType() {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createNamedQuery("Tooltype.findAll");
		List<Tooltype> result = q.getResultList();
		return result;
	}

	public List<Unit> retrieveAllUnits() {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createNamedQuery("Unit.findAll");
		List<Unit> result = q.getResultList();
		return result;
	}

	public RawMaterial registerRawMaterial(String create, String rmid, String rawmname, String vendor, String length,
			String width, String thickness, String authouredby, String quantity) {

		int rid = (rmid == null || rmid.length() == 0) ? 0 : Integer.valueOf(rmid);
		RawMaterial rawMaterial = null;
		if (create.equalsIgnoreCase("false") && rid > 0) {
			rawMaterial = findRawMaterialById(rmid);
			if (quantity != null && quantity.length() > 0) {
				rawMaterial.setNumberOfbars(rawMaterial.getNumberOfbars() + Integer.valueOf(quantity));
			}
		} else {
			rawMaterial = new RawMaterial();
			rawMaterial.setDoc(new Date());
			if (quantity != null && quantity.length() > 0) {
				rawMaterial.setNumberOfbars(Integer.valueOf(quantity));
			}
		}
		rawMaterial.setDou(new Date());
		rawMaterial.setDesciption(rawmname);
		rawMaterial.setHeight(Integer.valueOf(thickness));
		rawMaterial.setWidth(Integer.valueOf(width));
		rawMaterial.setLength(Integer.valueOf(length));
		rawMaterial.setVendorId(Integer.valueOf(vendor));

		entityManager.persist(rawMaterial);

		if (quantity != null && quantity.length() > 0) {
			Set<RawMaterialHistory> history = rawMaterial.getRawMaterialHistory();
			RawMaterialHistory rawMaterialHistory = new RawMaterialHistory();
			rawMaterialHistory.setRawMaterial(rawMaterial);
			rawMaterialHistory.setAuthouredby(authouredby);
			rawMaterialHistory.setQuantity(Integer.valueOf(quantity));
			rawMaterialHistory.setAdddate(new Date());
			rawMaterialHistory.setStatus("a");
			history.add(rawMaterialHistory);
			if (rawMaterial.getRawMaterialHistory() == null) {
				rawMaterial.setRawMaterialHistory(history);
			} else {
				rawMaterial.getRawMaterialHistory().addAll(history);
			}
			entityManager.persist(rawMaterial);
		}
		return rawMaterial;
	}

	public List<RawMaterial> retrieveAllRms(String rmSearched) {

		EntityManager entityManager = getEntityManager();
		String query = "SELECT rm FROM RawMaterial rm";
		if (rmSearched != null && rmSearched.length() > 0) {
			query += " where rm.desciption LIKE '" + rmSearched + "%'";
		}
		Query q = entityManager.createQuery(query);
		List<RawMaterial> result = q.getResultList();
		return result;
	
	}

	public RawMaterial findRawMaterialById(String valueOf) {
		EntityManager entityManager = getEntityManager();
		RawMaterial rawMaterial = entityManager.find(RawMaterial.class, Integer.valueOf(valueOf));
		return rawMaterial;
	}

	public List<Vendor> getVendors() {
		EntityManager entityManager = getEntityManager();
		Query q = entityManager.createNamedQuery("Vendor.findAll");
		List<Vendor> result = q.getResultList();
		return result;
	}

}
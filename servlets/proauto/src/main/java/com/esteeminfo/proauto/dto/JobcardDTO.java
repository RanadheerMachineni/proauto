package com.esteeminfo.proauto.dto;

import java.util.List;

public class JobcardDTO {
	private int id;
	private String name;
	private String desc;
	private String customer;
	private String po;
	private String state;
	private String createdBy;
	private String jobStart;
	private String jobEnd;
	List<PoItemDTO> poItems;
	List<JobcardTaskDTO> tasks;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getJobStart() {
		return jobStart;
	}
	public void setJobStart(String jobStart) {
		this.jobStart = jobStart;
	}
	public String getJobEnd() {
		return jobEnd;
	}
	public void setJobEnd(String jobEnd) {
		this.jobEnd = jobEnd;
	}
	public List<JobcardTaskDTO> getTasks() {
		return tasks;
	}
	public void setTasks(List<JobcardTaskDTO> tasks) {
		this.tasks = tasks;
	}
	public List<PoItemDTO> getPoItems() {
		return poItems;
	}
	public void setPoItems(List<PoItemDTO> poItems) {
		this.poItems = poItems;
	}

}

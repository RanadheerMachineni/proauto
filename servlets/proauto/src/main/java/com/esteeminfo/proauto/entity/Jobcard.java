package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the jobcard database table.
 * 
 */
@Entity
@NamedQuery(name="Jobcard.findAll", query="SELECT j FROM Jobcard j")
public class Jobcard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="jobcard_id")
	private int jobcardId;

	@Temporal(TemporalType.DATE)
	@Column(name="create_date")
	private Date createDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="jobcard_desc")
	private String jobcardDesc;

	@Column(name="jobcard_name")
	private String jobcardName;

	@ManyToOne
	@JoinColumn(name="pid")
	private PurchaseOrder purchaseOrder;
	
	//bi-directional many-to-one association to JobcardTask,other side of owner uses mapped by
	@OneToMany(mappedBy="jobcard",fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
	private List<JobcardTask> jobcardTasks;

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="status")
	private Status statusBean;
	
	@OneToMany(mappedBy="jobcard",fetch=FetchType.LAZY)
	private List<MachineUsage> machineUsages;
	
	public Jobcard() {
	}

	public int getJobcardId() {
		return this.jobcardId;
	}

	public void setJobcardId(int jobcardId) {
		this.jobcardId = jobcardId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getJobcardDesc() {
		return this.jobcardDesc;
	}

	public void setJobcardDesc(String jobcardDesc) {
		this.jobcardDesc = jobcardDesc;
	}

	public String getJobcardName() {
		return this.jobcardName;
	}

	public void setJobcardName(String jobcardName) {
		this.jobcardName = jobcardName;
	}

	public List<JobcardTask> getJobcardTasks() {
		return this.jobcardTasks;
	}

	public void setJobcardTasks(List<JobcardTask> jobcardTasks) {
		this.jobcardTasks = jobcardTasks;
	}

	public JobcardTask addJobcardTask(JobcardTask jobcardTask) {
		getJobcardTasks().add(jobcardTask);
		jobcardTask.setJobcard(this);

		return jobcardTask;
	}

	public JobcardTask removeJobcardTask(JobcardTask jobcardTask) {
		getJobcardTasks().remove(jobcardTask);
		jobcardTask.setJobcard(null);

		return jobcardTask;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PurchaseOrder getPurchaseOrder() {
		return this.purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	
	public Status getStatusBean() {
		return this.statusBean;
	}

	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public List<MachineUsage> getMachineUsages() {
		return this.machineUsages;
	}

	public void setMachineUsages(List<MachineUsage> machineUsages) {
		this.machineUsages = machineUsages;
	}

	public MachineUsage addMachineUsage(MachineUsage machineUsage) {
		getMachineUsages().add(machineUsage);
		machineUsage.setJobcard(this);

		return machineUsage;
	}

	public MachineUsage removeMachineUsage(MachineUsage machineUsage) {
		getMachineUsages().remove(machineUsage);
		machineUsage.setJobcard(null);

		return machineUsage;
	}
}
package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


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

	@Column(name="created_by")
	private String createdBy;

	@Column(name="customer_id")
	private int customerId;

	@Column(name="jobcard_desc")
	private String jobcardDesc;

	@Column(name="jobcard_name")
	private String jobcardName;

	private int pid;

	private String status;

	//bi-directional many-to-one association to JobcardTask
	@OneToMany(mappedBy="jobcard")
	private Set<JobcardTask> jobcardTasks;

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
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

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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

	public int getPid() {
		return this.pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<JobcardTask> getJobcardTasks() {
		return this.jobcardTasks;
	}

	public void setJobcardTasks(Set<JobcardTask> jobcardTasks) {
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

}
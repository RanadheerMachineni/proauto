package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the machine_usage database table.
 * 
 */
@Entity
@Table(name="machine_usage")
@NamedQuery(name="MachineUsage.findAll", query="SELECT m FROM MachineUsage m")
public class MachineUsage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="machine_usage_id")
	private int machineUsageId;

	@Column(name="actual_time")
	private String actualTime;

	private String shift;

	@Temporal(TemporalType.DATE)
	@Column(name="use_date")
	private Date useDate;

	//bi-directional many-to-one association to PurchaseHistory
	@OneToMany(mappedBy="machineUsage",fetch=FetchType.LAZY)
	private Set<PurchaseHistory> purchaseHistories;
	
	//bi-directional many-to-one association to Machine
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine machine;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	//bi-directional many-to-one association to PurchaseOrder
	@ManyToOne
	@JoinColumn(name="pid")
	private PurchaseOrder purchaseOrder;

	//bi-directional many-to-one association to Jobcard
	@ManyToOne
	@JoinColumn(name="jobcard_id")
	private Jobcard jobcard;

	//bi-directional many-to-one association to JobcardTask
	@ManyToOne
	@JoinColumn(name="task_id")
	private JobcardTask jobcardTask;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="assignee")
	private Employee assignee;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="programmer")
	private Employee programmer;

	public MachineUsage() {
	}

	public int getMachineUsageId() {
		return this.machineUsageId;
	}

	public void setMachineUsageId(int machineUsageId) {
		this.machineUsageId = machineUsageId;
	}

	public String getActualTime() {
		return this.actualTime;
	}

	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}

	public String getShift() {
		return this.shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public Date getUseDate() {
		return this.useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public Machine getMachine() {
		return this.machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Customer getCustomer() {
		return this.customer;
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

	public Jobcard getJobcard() {
		return this.jobcard;
	}

	public void setJobcard(Jobcard jobcard) {
		this.jobcard = jobcard;
	}

	public JobcardTask getJobcardTask() {
		return this.jobcardTask;
	}

	public void setJobcardTask(JobcardTask jobcardTask) {
		this.jobcardTask = jobcardTask;
	}

	public Employee getAssignee() {
		return assignee;
	}

	public void setAssignee(Employee assignee) {
		this.assignee = assignee;
	}

	public Employee getProgrammer() {
		return programmer;
	}

	public void setProgrammer(Employee programmer) {
		this.programmer = programmer;
	}
	public Set<PurchaseHistory> getPurchaseHistories() {
		return this.purchaseHistories;
	}

	public void setPurchaseHistories(Set<PurchaseHistory> purchaseHistories) {
		this.purchaseHistories = purchaseHistories;
	}

	public PurchaseHistory addPurchaseHistory(PurchaseHistory purchaseHistory) {
		getPurchaseHistories().add(purchaseHistory);
		purchaseHistory.setMachineUsage(this);

		return purchaseHistory;
	}

	public PurchaseHistory removePurchaseHistory(PurchaseHistory purchaseHistory) {
		getPurchaseHistories().remove(purchaseHistory);
		purchaseHistory.setMachineUsage(null);

		return purchaseHistory;
	}
	
	
}
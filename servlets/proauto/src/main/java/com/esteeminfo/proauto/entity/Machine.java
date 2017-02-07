package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the machines database table.
 * 
 */
@Entity
@Table(name="machines")
@NamedQuery(name="Machine.findAll", query="SELECT m FROM Machine m")
public class Machine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="machine_id")
	private int machineId;

	@Column(name="machine_axle")
	private String machineAxle;

	@Column(name="machine_code_type")
	private String machineCodeType;

	@Column(name="machine_cost")
	private String machineCost;

	@Column(name="machine_desc")
	private String machineDesc;

	//bi-directional many-to-one association to JobcardTask
	@OneToMany(mappedBy="machine",fetch=FetchType.LAZY)
	private Set<JobcardTask> jobcardTasks;
	
	//bi-directional many-to-one association to MachineUsage
	@OneToMany(mappedBy="machine",fetch=FetchType.LAZY)
	private Set<MachineUsage> machineUsages;
	
	public Machine() {
	}

	public int getMachineId() {
		return this.machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public String getMachineAxle() {
		return this.machineAxle;
	}

	public void setMachineAxle(String machineAxle) {
		this.machineAxle = machineAxle;
	}

	public String getMachineCodeType() {
		return this.machineCodeType;
	}

	public void setMachineCodeType(String machineCodeType) {
		this.machineCodeType = machineCodeType;
	}

	public String getMachineCost() {
		return this.machineCost;
	}

	public void setMachineCost(String machineCost) {
		this.machineCost = machineCost;
	}

	public String getMachineDesc() {
		return this.machineDesc;
	}

	public void setMachineDesc(String machineDesc) {
		this.machineDesc = machineDesc;
	}
	
	public Set<JobcardTask> getJobcardTasks() {
		return this.jobcardTasks;
	}

	public void setJobcardTasks(Set<JobcardTask> jobcardTasks) {
		this.jobcardTasks = jobcardTasks;
	}

	public JobcardTask addJobcardTask(JobcardTask jobcardTask) {
		getJobcardTasks().add(jobcardTask);
		jobcardTask.setMachine(this);
		return jobcardTask;
	}

	public JobcardTask removeJobcardTask(JobcardTask jobcardTask) {
		getJobcardTasks().remove(jobcardTask);
		jobcardTask.setMachine(null);

		return jobcardTask;
	}

	public Set<MachineUsage> getMachineUsages() {
		return this.machineUsages;
	}

	public void setMachineUsages(Set<MachineUsage> machineUsages) {
		this.machineUsages = machineUsages;
	}

	public MachineUsage addMachineUsage(MachineUsage machineUsage) {
		getMachineUsages().add(machineUsage);
		machineUsage.setMachine(this);

		return machineUsage;
	}

	public MachineUsage removeMachineUsage(MachineUsage machineUsage) {
		getMachineUsages().remove(machineUsage);
		machineUsage.setMachine(null);

		return machineUsage;
	}
}
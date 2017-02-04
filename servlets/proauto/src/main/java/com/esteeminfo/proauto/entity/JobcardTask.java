package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the jobcard_task database table.
 * 
 */
@Entity
@Table(name="jobcard_task")
@NamedQuery(name="JobcardTask.findAll", query="SELECT j FROM JobcardTask j")
public class JobcardTask implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id")
	private int taskId;

	private String assignee;

	private String cost;

	@Column(name="end_time")
	private String endTime;

	@Column(name="jo_id")
	private int joId;

	@Column(name="machine_id")
	private int machineId;

	private String notes;

	@Column(name="start_time")
	private String startTime;

	@Column(name="task_order")
	private int taskOrder;
	
	@Column(name="time_taken")
	private String timeTaken;

	//bi-directional many-to-one association to Jobcard,owner side, having ref of other table
	@ManyToOne
	@JoinColumn(name="jobcard_id")
	private Jobcard jobcard;

	@ManyToOne
	@JoinColumn(name="status")
	private Status statusBean;
	
	public JobcardTask() {
	}

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getAssignee() {
		return this.assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getCost() {
		return this.cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public int getJoId() {
		return this.joId;
	}

	public void setJoId(int joId) {
		this.joId = joId;
	}

	public int getMachineId() {
		return this.machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}


	public String getTimeTaken() {
		return this.timeTaken;
	}

	public void setTimeTaken(String timeTaken) {
		this.timeTaken = timeTaken;
	}

	public Jobcard getJobcard() {
		return this.jobcard;
	}

	public void setJobcard(Jobcard jobcard) {
		this.jobcard = jobcard;
	}

	public int getTaskOrder() {
		return this.taskOrder;
	}

	public void setTaskOrder(int taskOrder) {
		this.taskOrder = taskOrder;
	}

	public Status getStatusBean() {
		return this.statusBean;
	}

	public void setStatusBean(Status statusBean) {
		this.statusBean = statusBean;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

}
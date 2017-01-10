package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the job_operation database table.
 * 
 */
@Entity
@Table(name="job_operation")
@NamedQuery(name="JobOperation.findAll", query="SELECT j FROM JobOperation j")
public class JobOperation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="jo_id")
	private int joId;

	@Column(name="job_desc")
	private String jobDesc;

	@Column(name="job_name")
	private String jobName;

	public JobOperation() {
	}

	public int getJoId() {
		return this.joId;
	}

	public void setJoId(int joId) {
		this.joId = joId;
	}

	public String getJobDesc() {
		return this.jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

}
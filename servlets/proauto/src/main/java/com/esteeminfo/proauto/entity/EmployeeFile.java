package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;


/**
 * The persistent class for the employee_files database table.
 * 
 */
@Entity
@Table(name="employee_files")
@NamedQuery(name="EmployeeFile.findAll", query="SELECT e FROM EmployeeFile e")
public class EmployeeFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="upload_id")
	private int uploadId;

	@Column(name="file_name")
	private String fileName;

	//bi-directional many-to-one association to Employee
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private Employee employee;

	//bi-directional one-to-one association to EmpFileData
	@OneToOne(mappedBy="employeeFile",cascade=CascadeType.ALL,orphanRemoval=true)
	private EmpFileData empFileData;

	public EmployeeFile() {
	}

	public int getUploadId() {
		return this.uploadId;
	}

	public void setUploadId(int uploadId) {
		this.uploadId = uploadId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmpFileData getEmpFileData() {
		return this.empFileData;
	}

	public void setEmpFileData(EmpFileData empFileData) {
		this.empFileData = empFileData;
	}

}
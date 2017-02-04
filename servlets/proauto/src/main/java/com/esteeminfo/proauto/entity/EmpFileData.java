package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;


/**
 * The persistent class for the emp_file_data database table.
 * 
 */
@Entity
@Table(name="emp_file_data")
@NamedQuery(name="EmpFileData.findAll", query="SELECT e FROM EmpFileData e")
public class EmpFileData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="file_data_id")
	private int fileDataId;

	@Lob
	@Column(name="file_data")
	private byte[] fileData;

	//bi-directional one-to-one association to EmployeeFile
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="upload_id")
	private EmployeeFile employeeFile;

	public EmpFileData() {
	}

	public int getFileDataId() {
		return this.fileDataId;
	}

	public void setFileDataId(int fileDataId) {
		this.fileDataId = fileDataId;
	}

	public byte[] getFileData() {
		return this.fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public EmployeeFile getEmployeeFile() {
		return this.employeeFile;
	}

	public void setEmployeeFile(EmployeeFile employeeFile) {
		this.employeeFile = employeeFile;
	}

}
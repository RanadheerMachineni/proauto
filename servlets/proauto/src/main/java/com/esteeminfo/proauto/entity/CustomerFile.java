package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customer_files database table.
 * 
 */
@Entity
@Table(name="customer_files")
@NamedQuery(name="CustomerFile.findAll", query="SELECT c FROM CustomerFile c")
public class CustomerFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="upload_id")
	private int uploadId;

	@Column(name="file_name")
	private String fileName;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	//bi-directional one-to-one association to CusFileData
//	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL,orphanRemoval=true)
//	@JoinColumn(name="upload_id", referencedColumnName="upload_id")
	
	@OneToOne(mappedBy="customerFile",cascade=CascadeType.ALL,orphanRemoval=true)
	private CusFileData cusFileData;

	public CustomerFile() {
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

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CusFileData getCusFileData() {
		return this.cusFileData;
	}

	public void setCusFileData(CusFileData cusFileData) {
		this.cusFileData = cusFileData;
	}

}
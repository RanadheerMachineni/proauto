package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the po_files database table.
 * 
 */
@Entity
@Table(name="po_files")
@NamedQuery(name="PoFile.findAll", query="SELECT p FROM PoFile p")
public class PoFile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="upload_id")
	private int uploadId;

	@Column(name="file_name")
	private String fileName;

	//bi-directional many-to-one association to PurchaseOrder
	@ManyToOne
	@JoinColumn(name="pid")
	private PurchaseOrder purchaseOrder;

	//bi-directional one-to-one association to PoFileData
	@OneToOne(fetch=FetchType.LAZY,mappedBy="poFile",cascade=CascadeType.ALL,orphanRemoval=true)
	private PoFileData poFileData;

	public PoFile() {
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

	public PurchaseOrder getPurchaseOrder() {
		return this.purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public PoFileData getPoFileData() {
		return this.poFileData;
	}

	public void setPoFileData(PoFileData poFileData) {
		this.poFileData = poFileData;
	}

}
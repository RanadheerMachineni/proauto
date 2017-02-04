package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the po_file_data database table.
 * 
 */
@Entity
@Table(name="po_file_data")
@NamedQuery(name="PoFileData.findAll", query="SELECT p FROM PoFileData p")
public class PoFileData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="file_data_id")
	private int fileDataId;

	@Lob
	@Column(name="file_data")
	private byte[] fileData;

	//bi-directional one-to-one association to PoFile
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="upload_id")
	private PoFile poFile;

	public PoFileData() {
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

	public PoFile getPoFile() {
		return this.poFile;
	}

	public void setPoFile(PoFile poFile) {
		this.poFile = poFile;
	}

}
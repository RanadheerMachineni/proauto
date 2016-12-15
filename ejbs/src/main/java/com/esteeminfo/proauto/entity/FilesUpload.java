package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the files_upload database table.
 * 
 */
@Entity
@Table(name="files_upload")
@NamedQuery(name="FilesUpload.findAll", query="SELECT f FROM FilesUpload f")
public class FilesUpload implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="upload_id")
	private int uploadId;

	@Lob
	@Column(name="file_data")
	private byte[] fileData;

	@Column(name="file_name")
	private String fileName;

	public FilesUpload() {
	}

	public int getUploadId() {
		return this.uploadId;
	}

	public void setUploadId(int uploadId) {
		this.uploadId = uploadId;
	}

	public byte[] getFileData() {
		return this.fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
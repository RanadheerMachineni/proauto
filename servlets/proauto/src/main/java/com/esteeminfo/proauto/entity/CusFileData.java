package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.type.descriptor.sql.LobTypeMappings;


/**
 * The persistent class for the cus_file_data database table.
 * 
 */
@Entity
@Table(name="cus_file_data")
@NamedQuery(name="CusFileData.findAll", query="SELECT c FROM CusFileData c")
public class CusFileData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="file_data_id")
	private int fileDataId;

	@Lob
	@Column(name="file_data")
	private byte[] fileData;

	//bi-directional one-to-one association to CustomerFile
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="upload_id")
	private CustomerFile customerFile;

	public CusFileData() {
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

	public CustomerFile getCustomerFile() {
		return this.customerFile;
	}

	public void setCustomerFile(CustomerFile customerFile) {
		this.customerFile = customerFile;
	}

}
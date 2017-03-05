package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the raw_material_history database table.
 * 
 */
@Entity
@Table(name="raw_material_history")
@NamedQuery(name="RawMaterialHistory.findAll", query="SELECT r FROM RawMaterialHistory r")
public class RawMaterialHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="raw_material_history_id")
	private int rawMaterialHistoryId;

	@Temporal(TemporalType.DATE)
	private Date adddate;

	private String authouredby;
	
	//bi-directional many-to-one association to Purchase
	@ManyToOne
	@JoinColumn(name="raw_material_id")
	private RawMaterial rawMaterial;
	

	//bi-directional many-to-one association to MachineUsage
	@ManyToOne
	@JoinColumn(name="machine_usage_id")
	private MachineUsage machineUsage;
	
	private int quantity;

	private String status;

	public RawMaterialHistory() {
	}

	public int getRawMaterialHistoryId() {
		return this.rawMaterialHistoryId;
	}

	public void setRawMaterialHistoryId(int rawMaterialHistoryId) {
		this.rawMaterialHistoryId = rawMaterialHistoryId;
	}

	public Date getAdddate() {
		return this.adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}

	public String getAuthouredby() {
		return this.authouredby;
	}

	public void setAuthouredby(String authouredby) {
		this.authouredby = authouredby;
	}

	public MachineUsage getMachineUsage() {
		return this.machineUsage;
	}

	public void setMachineUsage(MachineUsage machineUsage) {
		this.machineUsage = machineUsage;
	}
	
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.getRawMaterialHistoryId();
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
        if (!(o instanceof RawMaterialHistory)) return false;
        RawMaterialHistory other = (RawMaterialHistory) o;
	    return this.getRawMaterialHistoryId() == other.getRawMaterialHistoryId();
	}

	public RawMaterial getRawMaterial() {
		return rawMaterial;
	}

	public void setRawMaterial(RawMaterial rawMaterial) {
		this.rawMaterial = rawMaterial;
	}
}
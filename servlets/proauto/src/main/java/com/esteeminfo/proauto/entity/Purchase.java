package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the purchase database table.
 * 
 */
@Entity
@NamedQuery(name="Purchase.findAll", query="SELECT p FROM Purchase p")
public class Purchase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="particular_id")
	private int particularId;

	private String code;

	private String desciption;

	@Temporal(TemporalType.DATE)
	private Date doc;

	@Temporal(TemporalType.DATE)
	private Date dou;

	private String make;

	private String particular;

	@Column(name="tooltype_id")
	private int tooltypeId;

	private String unit;

	//bi-directional many-to-one association to MachineUsage
	@OneToMany(mappedBy="purchase",fetch=FetchType.LAZY)
	private List<MachineUsage> machineUsages;

	public Purchase() {
	}

	public int getParticularId() {
		return this.particularId;
	}

	public void setParticularId(int particularId) {
		this.particularId = particularId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesciption() {
		return this.desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Date getDoc() {
		return this.doc;
	}

	public void setDoc(Date doc) {
		this.doc = doc;
	}

	public Date getDou() {
		return this.dou;
	}

	public void setDou(Date dou) {
		this.dou = dou;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getParticular() {
		return this.particular;
	}

	public void setParticular(String particular) {
		this.particular = particular;
	}

	public int getTooltypeId() {
		return this.tooltypeId;
	}

	public void setTooltypeId(int tooltypeId) {
		this.tooltypeId = tooltypeId;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<MachineUsage> getMachineUsages() {
		return this.machineUsages;
	}

	public void setMachineUsages(List<MachineUsage> machineUsages) {
		this.machineUsages = machineUsages;
	}

	public MachineUsage addMachineUsage(MachineUsage machineUsage) {
		getMachineUsages().add(machineUsage);
		machineUsage.setPurchase(this);

		return machineUsage;
	}

	public MachineUsage removeMachineUsage(MachineUsage machineUsage) {
		getMachineUsages().remove(machineUsage);
		machineUsage.setPurchase(null);

		return machineUsage;
	}

}
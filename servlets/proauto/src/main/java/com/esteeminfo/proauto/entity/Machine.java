package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the machines database table.
 * 
 */
@Entity
@Table(name="machines")
@NamedQuery(name="Machine.findAll", query="SELECT m FROM Machine m")
public class Machine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="machine_id")
	private int machineId;

	@Column(name="machine_axle")
	private String machineAxle;

	@Column(name="machine_code_type")
	private String machineCodeType;

	@Column(name="machine_cost")
	private String machineCost;

	@Column(name="machine_desc")
	private String machineDesc;

	public Machine() {
	}

	public int getMachineId() {
		return this.machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}

	public String getMachineAxle() {
		return this.machineAxle;
	}

	public void setMachineAxle(String machineAxle) {
		this.machineAxle = machineAxle;
	}

	public String getMachineCodeType() {
		return this.machineCodeType;
	}

	public void setMachineCodeType(String machineCodeType) {
		this.machineCodeType = machineCodeType;
	}

	public String getMachineCost() {
		return this.machineCost;
	}

	public void setMachineCost(String machineCost) {
		this.machineCost = machineCost;
	}

	public String getMachineDesc() {
		return this.machineDesc;
	}

	public void setMachineDesc(String machineDesc) {
		this.machineDesc = machineDesc;
	}

}
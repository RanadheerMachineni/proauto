package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tooltype database table.
 * 
 */
@Entity
@NamedQuery(name="Tooltype.findAll", query="SELECT t FROM Tooltype t")
public class Tooltype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tooltype_id")
	private int tooltypeId;

	private String description;

	private String name;

	public Tooltype() {
	}

	public int getTooltypeId() {
		return this.tooltypeId;
	}

	public void setTooltypeId(int tooltypeId) {
		this.tooltypeId = tooltypeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
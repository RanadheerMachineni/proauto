package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the employee_role database table.
 * 
 */
@Entity
@Table(name="employee_role")
@NamedQuery(name="EmployeeRole.findAll", query="SELECT e FROM EmployeeRole e")
public class EmployeeRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmployeeRolePK id;


	public EmployeeRole() {
	}

	public EmployeeRolePK getId() {
		return this.id;
	}

	public void setId(EmployeeRolePK id) {
		this.id = id;
	}

}
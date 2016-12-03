package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the employee_role database table.
 * 
 */
@Embeddable
public class EmployeeRolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id", insertable=false, updatable=false)
	private String userId;

	@Column(name="role_id", insertable=false, updatable=false)
	private String roleId;

	public EmployeeRolePK() {
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return this.roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmployeeRolePK)) {
			return false;
		}
		EmployeeRolePK castOther = (EmployeeRolePK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.roleId.equals(castOther.roleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.roleId.hashCode();
		
		return hash;
	}
}
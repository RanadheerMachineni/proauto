package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the purchase_history database table.
 * 
 */
@Entity
@Table(name="purchase_history")
@NamedQuery(name="PurchaseHistory.findAll", query="SELECT p FROM PurchaseHistory p")
public class PurchaseHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="purchase_history_id")
	private int purchaseHistoryId;

	@Temporal(TemporalType.DATE)
	private Date adddate;

	private String authouredby;

	@Column(name="particular_id")
	private int particularId;

	private int quantity;

	public PurchaseHistory() {
	}

	public int getPurchaseHistoryId() {
		return this.purchaseHistoryId;
	}

	public void setPurchaseHistoryId(int purchaseHistoryId) {
		this.purchaseHistoryId = purchaseHistoryId;
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

	public int getParticularId() {
		return this.particularId;
	}

	public void setParticularId(int particularId) {
		this.particularId = particularId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
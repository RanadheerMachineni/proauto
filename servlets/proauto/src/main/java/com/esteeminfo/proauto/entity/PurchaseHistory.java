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
public class PurchaseHistory implements Serializable, Comparable<PurchaseHistory> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="purchase_history_id")
	private int purchaseHistoryId;

	@Temporal(TemporalType.DATE)
	private Date adddate;

	private String authouredby;

	//bi-directional many-to-one association to Purchase
	@ManyToOne
	@JoinColumn(name="particular_id")
	private Purchase purchase;

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

	public Purchase getPurchase() {
		return this.purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int compareTo(PurchaseHistory o) {
		return o.getAdddate().compareTo(this.getAdddate());
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.getPurchaseHistoryId();
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
        if (!(o instanceof PurchaseHistory)) return false;
	    PurchaseHistory other = (PurchaseHistory) o;
	    return this.getPurchaseHistoryId() == other.getPurchaseHistoryId();
	}

}
package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the make database table.
 * 
 */
@Entity
@NamedQuery(name="Make.findAll", query="SELECT m FROM Make m")
public class Make implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="make_id")
	private int makeId;

	@Column(name="make_name")
	private String makeName;

	//bi-directional many-to-one association to Purchase
	@OneToMany(mappedBy="make", fetch = FetchType.LAZY)
	private List<Purchase> purchases;

	public Make() {
	}

	public int getMakeId() {
		return this.makeId;
	}

	public void setMakeId(int makeId) {
		this.makeId = makeId;
	}

	public String getMakeName() {
		return this.makeName;
	}

	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}

	public List<Purchase> getPurchases() {
		return this.purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public Purchase addPurchas(Purchase purchas) {
		getPurchases().add(purchas);
		purchas.setMake(this);

		return purchas;
	}

	public Purchase removePurchas(Purchase purchas) {
		getPurchases().remove(purchas);
		purchas.setMake(null);

		return purchas;
	}

}
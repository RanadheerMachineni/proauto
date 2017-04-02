package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



/**
 * The persistent class for the po_tool database table.
 * 
 */
@Entity
@Table(name="po_tool")
@NamedQuery(name="PoTool.findAll", query="SELECT p FROM PoTool p")
public class PoTool implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pt_id")
	private int ptId;

	private String discount;

	@Column(name="mat_desc")
	private String matDesc;

	@Column(name="mat_no")
	private String matNo;

	@Column(name="mat_quantiy")
	private String matQuantiy;

	@Column(name="mat_unitprice")
	private String matUnitprice;

	@Column(name="mat_value")
	private String matValue;

	//bi-directional many-to-one association to PurchaseOrder
	@ManyToOne
	@JoinColumn(name="pid")
	private PurchaseOrder purchaseOrder;

	//bi-directional one-to-one association to Jobcard
	@OneToOne(mappedBy="poTool")
	private Jobcard jobcard;
	
	public PoTool() {
	}

	public int getPtId() {
		return this.ptId;
	}

	public void setPtId(int ptId) {
		this.ptId = ptId;
	}

	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getMatDesc() {
		return this.matDesc;
	}

	public void setMatDesc(String matDesc) {
		this.matDesc = matDesc;
	}

	public String getMatNo() {
		return this.matNo;
	}

	public void setMatNo(String matNo) {
		this.matNo = matNo;
	}

	public String getMatQuantiy() {
		return this.matQuantiy;
	}

	public void setMatQuantiy(String matQuantiy) {
		this.matQuantiy = matQuantiy;
	}

	public String getMatUnitprice() {
		return this.matUnitprice;
	}

	public void setMatUnitprice(String matUnitprice) {
		this.matUnitprice = matUnitprice;
	}

	public String getMatValue() {
		return this.matValue;
	}

	public void setMatValue(String matValue) {
		this.matValue = matValue;
	}

	public PurchaseOrder getPurchaseOrder() {
		return this.purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public Jobcard getJobcard() {
		return this.jobcard;
	}

	public void setJobcard(Jobcard jobcard) {
		this.jobcard = jobcard;
	}
}
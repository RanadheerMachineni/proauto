package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="customer_id")
	private int customerId;

	private String address;

	private String city;

	@Temporal(TemporalType.DATE)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="customer_name")
	private String customerName;

	@Column(name="email_eight")
	private String emailEight;

	@Column(name="email_five")
	private String emailFive;

	@Column(name="email_four")
	private String emailFour;

	@Column(name="email_nine")
	private String emailNine;

	@Column(name="email_one")
	private String emailOne;

	@Column(name="email_seven")
	private String emailSeven;

	@Column(name="email_six")
	private String emailSix;

	@Column(name="email_ten")
	private String emailTen;

	@Column(name="email_three")
	private String emailThree;

	@Column(name="email_two")
	private String emailTwo;

	@Column(name="name_eight")
	private String nameEight;

	@Column(name="name_five")
	private String nameFive;

	@Column(name="name_four")
	private String nameFour;

	@Column(name="name_nine")
	private String nameNine;

	@Column(name="name_one")
	private String nameOne;

	@Column(name="name_seven")
	private String nameSeven;

	@Column(name="name_six")
	private String nameSix;

	@Column(name="name_ten")
	private String nameTen;

	@Column(name="name_three")
	private String nameThree;

	@Column(name="name_two")
	private String nameTwo;

	@Column(name="phone_eight")
	private String phoneEight;

	@Column(name="phone_five")
	private String phoneFive;

	@Column(name="phone_four")
	private String phoneFour;

	@Column(name="phone_nine")
	private String phoneNine;

	@Column(name="phone_one")
	private String phoneOne;

	@Column(name="phone_seven")
	private String phoneSeven;

	@Column(name="phone_six")
	private String phoneSix;

	@Column(name="phone_ten")
	private String phoneTen;

	@Column(name="phone_three")
	private String phoneThree;

	@Column(name="phone_two")
	private String phoneTwo;

	private String state;

	@Column(name="zip_code")
	private String zipCode;

	public Customer() {
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmailEight() {
		return this.emailEight;
	}

	public void setEmailEight(String emailEight) {
		this.emailEight = emailEight;
	}

	public String getEmailFive() {
		return this.emailFive;
	}

	public void setEmailFive(String emailFive) {
		this.emailFive = emailFive;
	}

	public String getEmailFour() {
		return this.emailFour;
	}

	public void setEmailFour(String emailFour) {
		this.emailFour = emailFour;
	}

	public String getEmailNine() {
		return this.emailNine;
	}

	public void setEmailNine(String emailNine) {
		this.emailNine = emailNine;
	}

	public String getEmailOne() {
		return this.emailOne;
	}

	public void setEmailOne(String emailOne) {
		this.emailOne = emailOne;
	}

	public String getEmailSeven() {
		return this.emailSeven;
	}

	public void setEmailSeven(String emailSeven) {
		this.emailSeven = emailSeven;
	}

	public String getEmailSix() {
		return this.emailSix;
	}

	public void setEmailSix(String emailSix) {
		this.emailSix = emailSix;
	}

	public String getEmailTen() {
		return this.emailTen;
	}

	public void setEmailTen(String emailTen) {
		this.emailTen = emailTen;
	}

	public String getEmailThree() {
		return this.emailThree;
	}

	public void setEmailThree(String emailThree) {
		this.emailThree = emailThree;
	}

	public String getEmailTwo() {
		return this.emailTwo;
	}

	public void setEmailTwo(String emailTwo) {
		this.emailTwo = emailTwo;
	}

	public String getNameEight() {
		return this.nameEight;
	}

	public void setNameEight(String nameEight) {
		this.nameEight = nameEight;
	}

	public String getNameFive() {
		return this.nameFive;
	}

	public void setNameFive(String nameFive) {
		this.nameFive = nameFive;
	}

	public String getNameFour() {
		return this.nameFour;
	}

	public void setNameFour(String nameFour) {
		this.nameFour = nameFour;
	}

	public String getNameNine() {
		return this.nameNine;
	}

	public void setNameNine(String nameNine) {
		this.nameNine = nameNine;
	}

	public String getNameOne() {
		return this.nameOne;
	}

	public void setNameOne(String nameOne) {
		this.nameOne = nameOne;
	}

	public String getNameSeven() {
		return this.nameSeven;
	}

	public void setNameSeven(String nameSeven) {
		this.nameSeven = nameSeven;
	}

	public String getNameSix() {
		return this.nameSix;
	}

	public void setNameSix(String nameSix) {
		this.nameSix = nameSix;
	}

	public String getNameTen() {
		return this.nameTen;
	}

	public void setNameTen(String nameTen) {
		this.nameTen = nameTen;
	}

	public String getNameThree() {
		return this.nameThree;
	}

	public void setNameThree(String nameThree) {
		this.nameThree = nameThree;
	}

	public String getNameTwo() {
		return this.nameTwo;
	}

	public void setNameTwo(String nameTwo) {
		this.nameTwo = nameTwo;
	}

	public String getPhoneEight() {
		return this.phoneEight;
	}

	public void setPhoneEight(String phoneEight) {
		this.phoneEight = phoneEight;
	}

	public String getPhoneFive() {
		return this.phoneFive;
	}

	public void setPhoneFive(String phoneFive) {
		this.phoneFive = phoneFive;
	}

	public String getPhoneFour() {
		return this.phoneFour;
	}

	public void setPhoneFour(String phoneFour) {
		this.phoneFour = phoneFour;
	}

	public String getPhoneNine() {
		return this.phoneNine;
	}

	public void setPhoneNine(String phoneNine) {
		this.phoneNine = phoneNine;
	}

	public String getPhoneOne() {
		return this.phoneOne;
	}

	public void setPhoneOne(String phoneOne) {
		this.phoneOne = phoneOne;
	}

	public String getPhoneSeven() {
		return this.phoneSeven;
	}

	public void setPhoneSeven(String phoneSeven) {
		this.phoneSeven = phoneSeven;
	}

	public String getPhoneSix() {
		return this.phoneSix;
	}

	public void setPhoneSix(String phoneSix) {
		this.phoneSix = phoneSix;
	}

	public String getPhoneTen() {
		return this.phoneTen;
	}

	public void setPhoneTen(String phoneTen) {
		this.phoneTen = phoneTen;
	}

	public String getPhoneThree() {
		return this.phoneThree;
	}

	public void setPhoneThree(String phoneThree) {
		this.phoneThree = phoneThree;
	}

	public String getPhoneTwo() {
		return this.phoneTwo;
	}

	public void setPhoneTwo(String phoneTwo) {
		this.phoneTwo = phoneTwo;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
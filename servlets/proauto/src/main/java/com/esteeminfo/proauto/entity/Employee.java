package com.esteeminfo.proauto.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the employee database table.
 * 
 */
@Entity
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id")
	private int employeeId;

	@Column(name="city_ca")
	private String cityCa;

	@Column(name="city_pa")
	private String cityPa;

	@Temporal(TemporalType.DATE)
	@Column(name="create_date")
	private Date createDate;

	@Column(name="current_address")
	private String currentAddress;

	private String designation;

	@Temporal(TemporalType.DATE)
	private Date dob;

	@Temporal(TemporalType.DATE)
	private Date doj;

	@Temporal(TemporalType.DATE)
	private Date dot;

	private String email;

	@Column(name="emergency_contact")
	private String emergencyContact;

	@Column(name="employement_type")
	private String employementType;

	private String experience;

	@Column(name="first_name")
	private String firstName;

	private String gender;

	@Column(name="last_name")
	private String lastName;

	private String married;

	private String notes;

	private String passport;

	private String password;

	@Column(name="permanent_address")
	private String permanentAddress;

	private String phone;

	private String qualification;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;

	//bi-directional many-to-one association to Section
	@ManyToOne
	@JoinColumn(name="section_id")
	private Section section;

	@Column(name="state_ca")
	private String stateCa;

	@Column(name="state_pa")
	private String statePa;

	private String status;

	@Column(name="user_id")
	private String userId;

	@Column(name="zip_code_ca")
	private String zipCodeCa;

	@Column(name="zip_code_pa")
	private String zipCodePa;
	
	//bi-directional many-to-one association to JobcardTask
	@OneToMany(mappedBy="assignee",fetch=FetchType.LAZY)
	private Set<JobcardTask> assigneeTasks;

	//bi-directional many-to-one association to JobcardTask
	@OneToMany(mappedBy="programmer",fetch=FetchType.LAZY)
	private Set<JobcardTask> programmerTasks;
	


	//bi-directional many-to-one association to MachineUsage
	@OneToMany(mappedBy="assignee",fetch=FetchType.LAZY)
	private Set<MachineUsage> assigneeMachineUsage;

	//bi-directional many-to-one association to MachineUsage
	@OneToMany(mappedBy="programmer",fetch=FetchType.LAZY)
	private Set<MachineUsage> programmerMachineUsage;
	
	//bi-directional many-to-many association to Role
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="employee_role"
		, joinColumns={
			@JoinColumn(name="employee_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id")
			}
		)
	private Set<Role> roles;
	
	//bi-directional many-to-one association to EmployeeFile
	@OneToMany(mappedBy="employee",cascade=CascadeType.ALL,orphanRemoval=true)
	private Set<EmployeeFile> employeeFiles;

	public Employee() {
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getCityCa() {
		return this.cityCa;
	}

	public void setCityCa(String cityCa) {
		this.cityCa = cityCa;
	}

	public String getCityPa() {
		return this.cityPa;
	}

	public void setCityPa(String cityPa) {
		this.cityPa = cityPa;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCurrentAddress() {
		return this.currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return this.doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public Date getDot() {
		return this.dot;
	}

	public void setDot(Date dot) {
		this.dot = dot;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmergencyContact() {
		return this.emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getExperience() {
		return this.experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMarried() {
		return this.married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPassport() {
		return this.passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermanentAddress() {
		return this.permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getStateCa() {
		return this.stateCa;
	}

	public void setStateCa(String stateCa) {
		this.stateCa = stateCa;
	}

	public String getStatePa() {
		return this.statePa;
	}

	public void setStatePa(String statePa) {
		this.statePa = statePa;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getZipCodeCa() {
		return this.zipCodeCa;
	}

	public void setZipCodeCa(String zipCodeCa) {
		this.zipCodeCa = zipCodeCa;
	}

	public String getZipCodePa() {
		return this.zipCodePa;
	}

	public void setZipCodePa(String zipCodePa) {
		this.zipCodePa = zipCodePa;
	}
	
	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Section getSection() {
		return this.section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getEmployementType() {
		return this.employementType;
	}

	public void setEmployementType(String employementType) {
		this.employementType = employementType;
	}
	
	public Set<EmployeeFile> getEmployeeFiles() {
		return this.employeeFiles;
	}

	public void setEmployeeFiles(Set<EmployeeFile> employeeFiles) {
		this.employeeFiles = employeeFiles;
	}

	public EmployeeFile addEmployeeFile(EmployeeFile employeeFile) {
		getEmployeeFiles().add(employeeFile);
		employeeFile.setEmployee(this);

		return employeeFile;
	}

	public EmployeeFile removeEmployeeFile(EmployeeFile employeeFile) {
		getEmployeeFiles().remove(employeeFile);
		employeeFile.setEmployee(null);

		return employeeFile;
	}

	public Set<JobcardTask> getAssigneeTasks() {
		return assigneeTasks;
	}

	public void setAssigneeTasks(Set<JobcardTask> assigneeTasks) {
		this.assigneeTasks = assigneeTasks;
	}

	public JobcardTask addAssigneeTasks(JobcardTask jobcardTasks) {
		getAssigneeTasks().add(jobcardTasks);
		jobcardTasks.setAssignee(this);
		return jobcardTasks;
	}

	public JobcardTask removeAssigneeTasks(JobcardTask jobcardTasks) {
		getAssigneeTasks().remove(jobcardTasks);
		jobcardTasks.setAssignee(null);
		return jobcardTasks;
	}

	public Set<JobcardTask> getProgrammerTasks() {
		return programmerTasks;
	}

	public void setProgrammerTasks(Set<JobcardTask> programmerTasks) {
		this.programmerTasks = programmerTasks;
	}

	public JobcardTask addProgrammerTasks(JobcardTask jobcardTasks) {
		getProgrammerTasks().add(jobcardTasks);
		jobcardTasks.setProgrammer(this);
		return jobcardTasks;
	}

	public JobcardTask removeProgrammerTasks(JobcardTask jobcardTasks) {
		getProgrammerTasks().remove(jobcardTasks);
		jobcardTasks.setProgrammer(null);
		return jobcardTasks;
	}

	public Set<MachineUsage> getAssigneeMachineUsage() {
		return assigneeMachineUsage;
	}

	public void setAssigneeMachineUsage(Set<MachineUsage> assigneeMachineUsage) {
		this.assigneeMachineUsage = assigneeMachineUsage;
	}
	
	public MachineUsage addAssigneeMachineUsage(MachineUsage assigneeMachineUsage) {
		getAssigneeMachineUsage().add(assigneeMachineUsage);
		assigneeMachineUsage.setAssignee(this);
		return assigneeMachineUsage;
	}

	public MachineUsage removeAssigneeMachineUsage(MachineUsage assigneeMachineUsage) {
		getAssigneeMachineUsage().remove(assigneeMachineUsage);
		assigneeMachineUsage.setAssignee(null);
		return assigneeMachineUsage;
	}

	public Set<MachineUsage> getProgrammerMachineUsage() {
		return programmerMachineUsage;
	}

	public void setProgrammerMachineUsage(Set<MachineUsage> programmerMachineUsage) {
		this.programmerMachineUsage = programmerMachineUsage;
	}
	
	public MachineUsage addProgrammerMachineUsage(MachineUsage programmerMachineUsage) {
		getProgrammerMachineUsage().add(programmerMachineUsage);
		programmerMachineUsage.setProgrammer(this);
		return programmerMachineUsage;
	}

	public MachineUsage removeProgrammerMachineUsage(MachineUsage programmerMachineUsage) {
		getProgrammerMachineUsage().remove(programmerMachineUsage);
		programmerMachineUsage.setProgrammer(null);
		return programmerMachineUsage;
	}
}
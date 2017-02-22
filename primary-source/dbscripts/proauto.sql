max_allowed_packet=10M
Drop user proauto CASCADE;
CREATE USER 'proauto'@'localhost' IDENTIFIED BY 'proauto';
GRANT ALL PRIVILEGES ON * . * TO 'proauto'@'localhost';
FLUSH PRIVILEGES;

create database proauto_db;
grant all privileges on proauto_db.* to 'proauto'@'localhost'

drop table roles;
CREATE TABLE roles
(
	role_id char(50) NOT NULL,
	name char(50) NOT NULL,
	role_desc char(50),
	CONSTRAINT roles_pk PRIMARY KEY (role_id)
);
insert into roles(role_id,name) values('ROLE_admin', 'Administrator');
insert into roles(role_id,name) values('ROLE_jobcard','JobCard User');
insert into roles(role_id,name) values('ROLE_costing','Costing User');
insert into roles(role_id,name) values('ROLE_dms','Dms User');
insert into roles(role_id,name) values('ROLE_norole','- Not user');

drop table section;
CREATE TABLE section
(
	section_id char(50) NOT NULL,
	section_desc char(50),
	CONSTRAINT section_pk PRIMARY KEY (section_id)
);
insert into section(section_id,section_desc) values('Section1', 'Section1');
insert into section(section_id,section_desc) values('Section2', 'Section2');

drop table department;
CREATE TABLE department
(
	department_id char(50) NOT NULL,
	department_desc char(50),
	CONSTRAINT department_pk PRIMARY KEY (department_id)
);
insert into department(department_id,department_desc) values('dept1', 'dept1');
insert into department(department_id,department_desc) values('dept2', 'dept2');

drop table employee;
CREATE TABLE employee
(
	employee_id int AUTO_INCREMENT NOT NULL,
	first_name char(50) NOT NULL,
	last_name char(50) NOT NULL,
	gender CHAR(1) NOT NULL,
	designation char(50),
	dob DATE,
	doj date,
	dot date,
	qualification char(50),
	experience char(50),
	married CHAR(1),                 
	passport char(50),
	emergency_contact char(50),
	user_id char(50),
	password char(50),
	phone char(50),
	email char(50),
	current_address char(255),
	permanent_address char(255),
	city_ca char(50),
	state_ca char(25),
	zip_code_ca char(10),
	city_pa char(50),
	state_pa char(25),
	zip_code_pa char(10),
	create_date DATE,
	status CHAR(1) NOT NULL DEFAULT 'a',
	notes char(255),
	department_id char(50) NOT NULL DEFAULT 'dept1',
	section_id char(50) NOT NULL DEFAULT 'Section1',
	employement_type CHAR(1) NOT NULL DEFAULT 'p',
	CONSTRAINT pk_employee primary key (employee_id),
	CONSTRAINT uk_user_id UNIQUE (user_id),
	CONSTRAINT first_name UNIQUE (first_name),
	CONSTRAINT fk_dept_id FOREIGN KEY (department_id) REFERENCES department(department_id),
	CONSTRAINT fk_sec_id FOREIGN KEY (section_id) REFERENCES section(section_id)
);

insert into employee(first_name,last_name,gender,user_id,password,dob,doj) values('admin','admin','m','admin','admin','2016-11-01','2016-11-01');
insert into employee(first_name,last_name,gender,user_id,password,dob,doj) values('dms','dms','m','dms','dms','2016-11-01','2016-11-01');
insert into employee(first_name,last_name,gender,user_id,password,dob,doj) values('costing','costing','f','costing','costing','2016-11-01','2016-11-01');
insert into employee(first_name,last_name,gender,user_id,password,dob,doj) values('jobcard','jobcard','f','jobcard','jobcard','2016-11-01','2016-11-01');


drop table employee_role;
CREATE TABLE employee_role
(
	employee_id int NOT NULL,
	role_id char(50) NOT NULL,
	constraint employee_role_pk primary key (employee_id , role_id),
	CONSTRAINT fk_er_employee_id FOREIGN KEY (employee_id) REFERENCES employee(employee_id),
	CONSTRAINT fk_er_role FOREIGN KEY (role_id) REFERENCES roles(role_id)

);

insert into employee_role(employee_id,role_id) values(1,'ROLE_admin');


CREATE TABLE emp_file_data (
 	file_data_id  int AUTO_INCREMENT NOT NULL,
 	upload_id int NOT NULL,
    file_data longblob,
	CONSTRAINT pk_emp_file_data primary key (file_data_id),
	CONSTRAINT fk_emp_file_data_upload_id FOREIGN KEY (upload_id) REFERENCES employee_files(upload_id)
)

CREATE TABLE employee_files
(
	upload_id int AUTO_INCREMENT NOT NULL,
    file_name varchar(128) DEFAULT NULL,
	employee_id int NOT NULL,
	CONSTRAINT pk_employee_files primary key (upload_id),
	CONSTRAINT fk_ef_employee_id FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
);

drop table customer;
CREATE TABLE customer
( 
	customer_id int AUTO_INCREMENT NOT NULL,
	customer_name char(50) NOT NULL,
	address char(255),
	city char(50),
	state char(25),
	zip_code char(10),
	create_date DATE,
	CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);

CREATE TABLE customer_files
(
	upload_id int AUTO_INCREMENT NOT NULL,
    file_name varchar(128) DEFAULT NULL,
	customer_id int NOT NULL,
	CONSTRAINT pk_customer_files primary key (upload_id),
	CONSTRAINT fk_cf_customer_id FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE cus_file_data (
 	file_data_id  int AUTO_INCREMENT NOT NULL,
 	upload_id int NOT NULL,
    file_data longblob,
	CONSTRAINT pk_cus_file_data primary key (file_data_id),
	CONSTRAINT fk_cus_file_data_upload_id FOREIGN KEY (upload_id) REFERENCES customer_files(upload_id)
)

drop table purchase_order;
CREATE TABLE purchase_order
(
	pid int AUTO_INCREMENT NOT NULL,
	customer_id int NOT NULL,
	po_id char(100) NOT NULL,
	po_version char(10),
	pdate DATE,
	vno_sender char(50),
	sender_contact char(50),
	sender_details char(50),
	sender_email char(50),
	sender_phone char(50),
	sender_fax char(50),
	notes char(200),
	total_value char(50),
    CONSTRAINT po_pk PRIMARY KEY (pid),
   	CONSTRAINT uk_po_id UNIQUE (po_id),
   	CONSTRAINT fk_po_customer_id FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);


CREATE TABLE po_files
(
	upload_id int AUTO_INCREMENT NOT NULL,
    file_name varchar(128) DEFAULT NULL,
	pid int NOT NULL,
	CONSTRAINT pk_po_files primary key (upload_id),
	CONSTRAINT fk_pf_pid FOREIGN KEY (pid) REFERENCES purchase_order(pid)
);

CREATE TABLE po_file_data (
 	file_data_id  int AUTO_INCREMENT NOT NULL,
 	upload_id int NOT NULL,
    file_data longblob,
	CONSTRAINT pk_po_file_data primary key (file_data_id),
	CONSTRAINT fk_po_file_data_upload_id FOREIGN KEY (upload_id) REFERENCES po_files(upload_id)
)


/*drop table customer_raw_material;
CREATE TABLE customer_raw_material
(
	number_ofbars int NOT NUll,
	height int NOT NUll,
	width int NOT NUll,
	length int NOT NUll,
    create_date DATE NOT NUll,
	customer_id int NOT NULL,
	CONSTRAINT fk_rm_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
)

drop table raw_material;
CREATE TABLE raw_material
(
	number_ofbars int NOT NUll,
	height int NOT NUll,
	width int NOT NUll,
	length int NOT NUll,
    create_date DATE NOT NUll,
	vendor_id int NOT NULL,
	customer_id int NULL,
	CONSTRAINT fk_rm_vendor FOREIGN KEY (vendor_id) REFERENCES vendor(vendor_id)
)*/


drop table vendor;
CREATE TABLE vendor
( 
	vendor_id int AUTO_INCREMENT NOT NULL,
	vendor_name char(50) NOT NULL,
	address char(255),
	city char(50),
	state char(25),
	zip_code char(10),
	create_date DATE,
    CONSTRAINT vendor_pk PRIMARY KEY (vendor_id)
);

drop table contact;
CREATE TABLE contact
( 
	contact_id int AUTO_INCREMENT NOT NULL,
	name char(100) NOT NULL,
    phone char(100) NOT NULL,
    email char(100) NOT NULL,
	fax char(100),
	notes char(100),
    CONSTRAINT contact_pk PRIMARY KEY (contact_id)
);

drop table customer_contacts;
CREATE TABLE customer_contacts
(
	customer_id int NOT NULL,
	contact_id int NOT NULL,
	constraint customer_contacts_pk primary key (customer_id , contact_id),
	CONSTRAINT fk_cc_customer_id FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
	CONSTRAINT fk_cc_contact_id FOREIGN KEY (contact_id) REFERENCES contact(contact_id)
);



drop table po_tool;
CREATE TABLE po_tool
(
	pt_id int AUTO_INCREMENT NOT NULL,
	pid int NOT NULL,
	mat_no char(50) NOT NULL,
	mat_desc char(50),
	mat_unitprice char(50),
	mat_quantiy char(50),
	discount char(50),
	mat_value char(50),
    CONSTRAINT po_tool_pk PRIMARY KEY (pt_id),
   	CONSTRAINT fk_po_tool_pid FOREIGN KEY (pid) REFERENCES purchase_order(pid)
);


drop table machines;
CREATE TABLE machines
(
	machine_id int AUTO_INCREMENT NOT NULL,
	machine_desc char(50),
	machine_cost char(50),
	machine_code_type char(50),
	machine_axle char(50),
    CONSTRAINT machine_pk PRIMARY KEY (machine_id)
)

drop table job_operation;
CREATE TABLE job_operation
(
	jo_id int AUTO_INCREMENT NOT NULL,
	job_name char(50),
	job_desc char(200),
    CONSTRAINT job_operation_pk PRIMARY KEY (jo_id)
)

drop table status;
CREATE TABLE status
(
	status_id int NOT NULL,
	status char(50) NOT NULL,
	constraint status_pk primary key (status_id)
);
insert into status(status_id,status) values(1, 'New');
insert into status(status_id,status) values(2, 'In Progress');
insert into status(status_id,status) values(3, 'Pending');
insert into status(status_id,status) values(4, 'Aborted');
insert into status(status_id,status) values(5, 'Completed');

drop table jobcard;
CREATE TABLE jobcard
(
	jobcard_id int AUTO_INCREMENT NOT NULL,
	jobcard_name char(50) NOT NULL,
	jobcard_desc char(100),
	customer_id int NOT NULL,
	pid int NOT NULL,
	created_by char(50),
	create_date DATE,
	end_date DATE,
	status int,
	constraint jobcard_pk primary key (jobcard_id),
   	CONSTRAINT fk_jobcard_customer_id FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
   	CONSTRAINT fk_jobcard_pid FOREIGN KEY (pid) REFERENCES purchase_order(pid),
   	CONSTRAINT fk_jobcard_status FOREIGN KEY (status) REFERENCES status(status_id)
);

drop table jobcard_task;
CREATE TABLE jobcard_task
(
	task_id int AUTO_INCREMENT NOT NULL,
	jobcard_id int not null,
	jo_id int not null,
	status int,
	assignee int,
	programmer int,
	notes char(100),
	cost char(20),
	machine_id int,
	time_taken char(20),
	start_time char(20),
	end_time char(20),
	task_order int,
	constraint task_id_pk primary key (task_id),
   	CONSTRAINT fk_jobcard_task_assignee FOREIGN KEY (assignee) REFERENCES employee(employee_id),
   	CONSTRAINT fk_jobcard_task_programmer FOREIGN KEY (programmer) REFERENCES employee(employee_id),
   	CONSTRAINT fk_jobcard_task_jobcard_id FOREIGN KEY (jobcard_id) REFERENCES jobcard(jobcard_id),
   	CONSTRAINT fk_jobcard_task_jo_id FOREIGN KEY (jo_id) REFERENCES job_operation(jo_id),
	CONSTRAINT fk_jobcard_task_machine_id FOREIGN KEY (machine_id) REFERENCES machines(machine_id),
   	CONSTRAINT fk_jobcard_task_status FOREIGN KEY (status) REFERENCES status(status_id)
);

create table tooltype(
	tooltype_id int AUTO_INCREMENT NOT NULL,
	name char(50),
	description char(200),
    CONSTRAINT tooltype_pk PRIMARY KEY (tooltype_id)
);

create table make(
	make_id int AUTO_INCREMENT NOT NULL,
	make_name char(50) NOT NULL,
    CONSTRAINT make_pk PRIMARY KEY (make_id)
);

create table purchase
(
	particular_id int AUTO_INCREMENT NOT NULL,
	particular char(255) NOT NULL,
	code char(100) NOT NULL,
	make_id int NOT NULL,
	quantity int NOT NULL,
	unit char(20),
	doc DATE,
	dou date,
	authouredby char(50),
	desciption char(255),
	tooltype_id int,
	repository int,
	constraint purchase_pk primary key (particular_id),
   	CONSTRAINT fk_purchase_tooltype_id FOREIGN KEY (tooltype_id) REFERENCES tooltype(tooltype_id),
   	CONSTRAINT fk_purchase_make FOREIGN KEY (make_id) REFERENCES make(make_id),
   	CONSTRAINT uk_purchase_code_make UNIQUE (code,make_id)
);

create table machine_usage
(
	machine_usage_id int AUTO_INCREMENT NOT NULL,
	machine_id int not null,
	customer_id int,
	pid int,
	jobcard_id int,
	task_id int,
	assignee int,
	programmer int,
	use_date DATE,
	shift char(50),
	actual_time char(20),
	desciption char(255),
	constraint machine_usage_pk primary key (machine_usage_id),
	CONSTRAINT fk_mu_machine_id FOREIGN KEY (machine_id) REFERENCES machines(machine_id),
	CONSTRAINT fk_mu_customer_id FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
   	CONSTRAINT fk_mu_pid FOREIGN KEY (pid) REFERENCES purchase_order(pid),
   	CONSTRAINT fk_mu_jobcard FOREIGN KEY (jobcard_id) REFERENCES jobcard(jobcard_id),
   	CONSTRAINT fk_mu_task FOREIGN KEY (task_id) REFERENCES jobcard_task(task_id),
   	CONSTRAINT fk_machine_usage_task_assignee FOREIGN KEY (assignee) REFERENCES employee(employee_id),
   	CONSTRAINT fk_machine_usage_programmer FOREIGN KEY (programmer) REFERENCES employee(employee_id)
);

create table purchase_history(
	purchase_history_id int AUTO_INCREMENT NOT NULL,
	particular_id int,
	adddate date,
	authouredby char(50),
	quantity int,
	machine_usage_id int,
	status CHAR(1),
    CONSTRAINT purchase_history_pk PRIMARY KEY (purchase_history_id),
   	CONSTRAINT fk_ph_particular_id FOREIGN KEY (particular_id) REFERENCES purchase(particular_id),
	CONSTRAINT fk_ph_machine_usage_id FOREIGN KEY (machine_usage_id) REFERENCES machine_usage(machine_usage_id)
);




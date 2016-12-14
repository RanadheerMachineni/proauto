Drop user proauto CASCADE;
CREATE USER 'proauto'@'localhost' IDENTIFIED BY 'proauto';
GRANT ALL PRIVILEGES ON * . * TO 'proauto'@'localhost';
FLUSH PRIVILEGES;

create database proauto_db;
grant all privileges on proauto_db.* to 'proauto'@'localhost'

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
	
	 name_one char(100) NOT NULL,
    phone_one char(100) NOT NULL,
    email_one char(100) NOT NULL,
    
    name_two char(100),
    phone_two char(100),
    email_two char(100),
    
    name_three char(100),
    phone_three char(100),
    email_three char(100),

    name_four char(100),
    phone_four char(100),
    email_four char(100),

    name_five char(100),
    phone_five char(100),
    email_five char(100),

    name_six char(100),
    phone_six char(100),
    email_six char(100),

    name_seven char(100),
    phone_seven char(100),
    email_seven char(100),

   	name_eight char(100),
    phone_eight char(100),
    email_eight char(100),

    name_nine char(100),
    phone_nine char(100),
    email_nine char(100),

    name_ten char(100),
    phone_ten char(100),
    email_ten char(100),
	CONSTRAINT customer_pk PRIMARY KEY (customer_id)
);

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
	
    name_one char(100) NOT NULL,
    phone_one char(100) NOT NULL,
    email_one char(100) NOT NULL,
    
    name_two char(100),
    phone_two char(100),
    email_two char(100),
    
    name_three char(100),
    phone_three char(100),
    email_three char(100),

    name_four char(100),
    phone_four char(100),
    email_four char(100),

    name_five char(100),
    phone_five char(100),
    email_five char(100),

    name_six char(100),
    phone_six char(100),
    email_six char(100),

    name_seven char(100),
    phone_seven char(100),
    email_seven char(100),

   	name_eight char(100),
    phone_eight char(100),
    email_eight char(100),

    name_nine char(100),
    phone_nine char(100),
    email_nine char(100),

    name_ten char(100),
    phone_ten char(100),
    email_ten char(100),

    CONSTRAINT vendor_pk PRIMARY KEY (vendor_id)
);

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
	qualification char(50),
	experience char(50),
	married CHAR(1),                 
	passport char(50),
	emergency_contact char(50),
	user_id char(50),
	password char(50),
	current_address char(255),
	permanent_address char(255),
	city char(50),
	phone char(50),
	email char(50),
	state char(25),
	zip_code char(10),
	create_date DATE,
	notes char(255),
	CONSTRAINT pk_employee primary key (employee_id),
	CONSTRAINT uk_user_id UNIQUE (user_id)
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

CREATE TABLE employee_role
(
	user_id char(50) NOT NULL,
	role_id char(50) NOT NULL,
	constraint employee_role_pk primary key (user_id , role_id),
	CONSTRAINT fk_er_user_id FOREIGN KEY (user_id) REFERENCES employee(user_id),
	CONSTRAINT fk_er_role FOREIGN KEY (role_id) REFERENCES roles(role_id)

);

insert into employee_role(user_id,role_id) values('admin','ROLE_admin');


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
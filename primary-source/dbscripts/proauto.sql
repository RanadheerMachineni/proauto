Drop user proauto CASCADE;
CREATE USER 'proauto'@'localhost' IDENTIFIED BY 'proauto';
GRANT ALL PRIVILEGES ON * . * TO 'proauto'@'localhost';
FLUSH PRIVILEGES;

create database proauto_db;
grant all privileges on proauto_db.* to 'proauto'@'localhost'

drop table customers;
CREATE TABLE customers
( 
	customer_id int AUTO_INCREMENT NOT NULL,
	customer_name char(50) NOT NULL,
	address char(50),
	city char(50),
	state char(25),
	zip_code char(10),
	create_date DATE,
	CONSTRAINT customers_pk PRIMARY KEY (customer_id)
);

drop table raw_material;
CREATE TABLE raw_material
(
	number_ofbars int NOT NUll,
	height int NOT NUll,
	width int NOT NUll,
	length int NOT NUll,
    create_date DATE NOT NUll,
	customer_id int NOT NULL,
	CONSTRAINT fk_rm_customer FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
)

drop table employee_roles;
CREATE TABLE employee_roles
(
	role_id int NOT NUll,
	role char(50) NOT NULL,
	role_desc char(50) NOT NULL,
	CONSTRAINT employee_roles_pk PRIMARY KEY (role_id),
	CONSTRAINT employee_roles_uk UNIQUE (role)
)
insert into employee_roles(role_id,role,role_desc) values(1,'admin', 'Administrator');
insert into employee_roles(role_id,role,role_desc) values(2,'jobcard','JobCard User');
insert into employee_roles(role_id,role,role_desc) values(3,'costing','Costing User');
insert into employee_roles(role_id,role,role_desc) values(4,'dms','dms User');

drop table employee;
CREATE TABLE employee
(
	employee_id int AUTO_INCREMENT NOT NULL,
	employee_name char(50) NOT NULL,
	password char(50) NOT NULL,
	role char(50) NOT NULL,
	address char(50),
	city char(50),
	state char(25),
	zip_code char(10),
	create_date DATE,
	primary key (employee_id),
	CONSTRAINT fk_employee_role FOREIGN KEY (role) REFERENCES employee_roles(role)
)
insert into employee(employee_name,role,password) values('admin', 'admin','admin');
insert into employee(employee_name,role,password) values('jobcard', 'jobcard','jobcard');
insert into employee(employee_name,role,password) values('costing', 'costing','costing');
insert into employee(employee_name,role,password) values('dms', 'dms','dms');

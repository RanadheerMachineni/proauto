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

drop table raw_material;
CREATE TABLE raw_material
(
	number_ofbars int NOT NUll,
	height int NOT NUll,
	width int NOT NUll,
	length int NOT NUll,
    create_date DATE NOT NUll,
	customer_id int NOT NULL,
	CONSTRAINT fk_rm_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
)

drop table roles;
CREATE TABLE roles
(
	role_id int NOT NUll,
	role char(50) NOT NULL,
	role_desc char(50) NOT NULL,
	CONSTRAINT roles_pk PRIMARY KEY (role_id),
	CONSTRAINT roles_uk UNIQUE (role)
);
insert into roles(role_id,role,role_desc) values(1,'ROLE_admin', 'Administrator');
insert into roles(role_id,role,role_desc) values(2,'ROLE_jobcard','JobCard User');
insert into roles(role_id,role,role_desc) values(3,'ROLE_costing','Costing User');
insert into roles(role_id,role,role_desc) values(4,'ROLE_dms','dms User');

drop table employee;
CREATE TABLE employee
(
	employee_id int AUTO_INCREMENT NOT NULL,
	employee_name char(50) NOT NULL,
	address char(50),
	city char(50),
	state char(25),
	zip_code char(10),
	create_date DATE,
	CONSTRAINT employee_pk primary key (employee_id)
)

insert into employee(employee_name) values('admin');
insert into employee(employee_name) values('jobcard');
insert into employee(employee_name) values('costing');
insert into employee(employee_name) values('dms');


drop table users;
CREATE TABLE users
(
	user_id int AUTO_INCREMENT NOT NULL,
	user_name char(50) NOT NULL,
	password char(50) NOT NULL,
	employee_id int NOT NULL,
	CONSTRAINT users_pk primary key (user_id),
	CONSTRAINT users_user_name_uk UNIQUE (user_name),
	CONSTRAINT users_employee_id_fk FOREIGN KEY (employee_id) REFERENCES employee(employee_id)
)

insert into users(user_name,password,employee_id) values('admin','admin', 1);
insert into users(user_name,password,employee_id) values('jobcard','jobcard', 2);
insert into users(user_name,password,employee_id) values('costing','costing', 3);
insert into users(user_name,password,employee_id) values('dms','dms', 4);

drop table user_role;
CREATE TABLE user_role
(
	user_name char(50) NOT NULL,
	role char(50) NOT NULL,
	CONSTRAINT user_role_user_name_fk FOREIGN KEY (user_name) REFERENCES users(user_name),
	CONSTRAINT user_role_role_fk FOREIGN KEY (role) REFERENCES roles(role)

);
insert into user_role(user_name , role) values('admin', 'ROLE_admin');
insert into user_role(user_name , role) values('jobcard', 'ROLE_jobcard');
insert into user_role(user_name , role) values('costing', 'ROLE_costing');
insert into user_role(user_name , role) values('dms', 'ROLE_dms');


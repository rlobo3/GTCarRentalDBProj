create table employee(
	username varchar(20),
	pass varchar(20) not null,
	primary key (username));
	
create table administrator(
	username varchar(20),
	pass varchar(20) not null,
	primary key (username));
	
create table driving_plan(
	dptype varchar(20),
	month_pay int(5),
	discount int(2),
	annual_fees int(10),
	primary key(dptype));
	
create table credit_card(
	cardno int(20),
	name_on_card varchar(50) not null,
	cvv int(3) not null,
	expire_date date not null,
	billing_addr varchar(50) not null,
	primary key(cardno));
	
create table member(
	username varchar(20),
	pass varchar(20) not null,
	fname varchar(20) not null,
	mi	varchar(1),
	lname varchar(20) not null,
	email varchar(20),
	phone int(15) not null,
	dptype varchar(20) not null,
	cardno int(20) not null,
	primary key (username)
	foreign key (dptype, driving_plan)
	foreign key (cardno, credit_card));
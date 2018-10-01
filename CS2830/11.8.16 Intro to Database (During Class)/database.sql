-- [Terminology]
--  Database Management System: software running on a server that gives us database capabilities.
--  Database: a collection of data organized into tables.
--  Database Client: a tool that allows us to communicate with the DBMS.

--  Notes:
--    - Each database can hold multiple tables and each DBMS can manage multiple databases
--    - Within a database, each table must have a unique name
--    - We interact with the DBMS using Structure Query Language (SQL)

-- [Prerequisites]
-- 1. Run through "Checklist For Starting Your EC2 Instance" on Canvas 
--		https://missouri.instructure.com/courses/1663/pages/checklist-for-starting-your-ec2-instance?module_item_id=140940


-- [Create and connect to your MySQL database]
-- 1. To create a new MySQL database, ssh into your amazon instance and run 'sudo service mysqld start' 
--		in the command line (look at prerequisites) 
-- 2. Once the database is created, Go to EC2 Dashboard under the "Description" tab of the running 
--		EC2 will list a hostname
-- 3. Download and install a database client like MySQL Workbench: http://dev.mysql.com/downloads/workbench/
-- 4. Use the credentials from EC2 to add a new entry for your database
-- 			* Connection Name: EC2
-- 			* Connection Method: Standard TCP/IP over SSH
--		Parameters
--			* SSH Hostname: (EC2 Public DNS:22) 
--			* SSH Username: ec2-user
--			* SSH Key File: "locate your PEM file"
--			* MySQL Hostname: localhost
--			* MySQL Server Port: 3306
--			* Username: root
-- 5. To create a database, use the following command below, syntax: CREATE DATABASE dbName; 

SHOW DATABASES;

CREATE DATABASE CS2830;

SHOW DATABASES;

USE CS2830;

show tables;

CREATE TABLE foodStock (
	id INT,
    name varchar(50),
    quantity INT,
    cost float
);

DESCRIBE foodStock;
-- table names are case sensitive
INSERT INTO foodstock VALUES (1, "Bitterballen", 12, 10);

INSERT INTO foodStock VALUES (1, "Bitterballen", 12, 10);

INSERT INTO foodStock VALUES (2, "Poffertjes", 1, 3);

INSERT INTO foodStock VALUES (3, "Ribs", 1, 16.50);

INSERT INTO foodStock VALUES (4, "Sushi", 2, 20);

select * from foodStock;

INSERT INTO foodStock VALUES ('Curry', 1, 20);

INSERT INTO foodStock (name, quantity, cost) VALUES ('Curry', 1, 20);

select count(*) from foodStock;

insert into foodStock values (5, 'Curry', 1, 20);

select count(*) from foodStock;

insert into foodStock values(5, 'Herbs', 4, 20);

select * from foodStock;

create table foodstock (
	id int primary key auto_increment,
    name varchar(50) not null unique,
    quantity int unsigned not null,
    cost float unsigned not null
    );

show tables;

insert into foodstock (name, quantity, cost) values ('bitterballen', 12, 10);

select * from foodstock;
-- havent specified id but it auto-increments

insert into foodstock (name, quantity, cost) values ("Pottertjes", 20, 3);
insert into foodstock (name, quantity, cost) values ("Ribs", 1, 16.50);
INSERT INTO foodstock (name, quantity, cost) values ("Sushi", 2, 20);
insert into foodstock (name, quantity, cost) values ("Herbs", 1, 20);

select * from foodstock;


delete from foodstock where id=5;

insert into foodstock (name, quantity, cost) values ("Herbs", 1, 20);

select name from foodstock;

select name, quantity from foodstock;

select name, quantity, cost from foodstock where cost>10;
    
-- update, make sure to set where clause w/ update

update foodstock set quantity=30 where id=1;

-- alter

alter table foodstock add test int default null;

describe foodstock;
-- this is bad, only protects in mysql, in command line, no safe update
update foodstock set test=1;

select * from foodstock;
-- good practice to default null if altering
alter table foodstock add purchaseDate datetime default null;

desc foodstock;

update foodstock set purchaseDate = now(), quantity = 30 where id=2;

-- delete
delete from foodstock where id=1;

show tables;
-- deletes both table and records inside table
drop table foodStock;
-- doesn't work
select * from foodStock;


create database demo;

use demo;

SELECT * from Employee;

drop table Employee;

create table Employee (
    id varchar(10) primary key,
    name varchar(50)
);
create database mydatabase;

use mydatabase;

create table employee(
    id int primary key,
    name varchar(255),
    job_title varchar(255),
    salary bigint
)

insert into employee(id, name, job_title, salary)
values
(3, 'Div', 'java developer', 60000),
(2, 'Devansh', 'full stack developer', 50000);

select * from employee
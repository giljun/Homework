desc emp;
select job from emp;
select deptno, avg(sal)
from emp
group by deptno;

select deptno, avg(sal)
from emp
where job=upper('clerk')
group by deptno;

desc dept;
select * from dept;

select loc, count(dname) from dept
group by loc;

desc departments;
desc employees;
desc locations;
select * from regions;
select * from countries;
select * from departments;
select * from locations;
select country_id, count(country_name)
from countries
group by country_id;

select location_id, count(department_name)
from departments
group by location_id;

select * from locations
where location_id=1700;
select * from departments
where location_id=1700;

select count(*) from departments
where location_id=1700;

select department_id, avg(salary) as asal
from employees
group by department_id
having asal>=2000
order by asal desc;


select * from employees;
select department_id, job_id, count(*)
from employees
group by department_id,job_id;

select job_id,avg(salary) , count(*) 
from employees
group by job_id;

select job_id,avg(salary) ,
count(*) as cmt
from employees
group by job_id
having cmt >=2;

select department_id,avg(salary) 
from employees
where job_id like '%CLERK'
group by department_id;

select * from employees;
select * from departments;

select employee_id,
 concat(first_name,' ',last_name) as name,
 department_id, department_name
 from employees, departments;

 select e.employee_id,
 concat(e.first_name,' ',e.last_name) as name,
 d.department_id, d.department_name
 from employees e   join departments d
 on e.department_id=d.department_id;
select e.employee_id,
 concat(e.first_name,' ',e.last_name) as name,
 d.department_id, d.department_name
 from employees e   , departments d
 where e.department_id=d.department_id;
 
 
select * from employees;
select * from departments;
select * from locations;

select e.first_name, d.department_name, l.city
from employees e 
join departments d on e.department_id=d.department_id
join locations l on d.location_id=l.location_id;

select e.first_name, d.department_name, l.city
from employees e , departments d ,locations l 
where e.department_id=d.department_id and 
      d.location_id=l.location_id;


select e.first_name, d.department_name, l.city
from employees e 
join departments d on e.department_id=d.department_id
join locations l on d.location_id=l.location_id
where l.city='Toronto';

select e.first_name, d.department_name, l.city
from employees e , departments d ,locations l 
where e.department_id=d.department_id and 
      d.location_id=l.location_id and
      l.city='Toronto';

select * from employees
where (department_id=90 or  department_id=100);
select * from employees
where department_id in(90,100);

select * from employees
where department_id in(select department_id from employees
where first_name ='Steven');
select * from employees
where department_id in(select department_id from employees
where first_name ='Steven') and first_name != 'Steven';



select department_id from employees
where first_name ='Steven';

select * from employees;
select * from employees where first_name='Steven' and last_name='King';
select * from employees where first_name='Steven';

select * from employees 
where job_id=(select job_id from employees 
where first_name='Valli' and last_name='Pataballa');
select * from employees 
where job_id=(select job_id from employees 
where first_name='Valli' and last_name='Pataballa')
order by salary desc;

select job_id from employees 
where first_name='Valli' and last_name='Pataballa';

select * from employees;


select avg(salary) from employees;
select * from employees 
where salary <(select avg(salary) from employees)
order by salary;


select  min(avgsal) from (
	select job_id, avg(salary) as avgsal from employees 
    group by job_id
) t;
select  min(avgsal) from (
	select job_id, avg(salary) as avgsal from employees 
    group by job_id
) t  ;
select  * from (
	select job_id, avg(salary) as avgsal from employees 
    group by job_id
	) t  where avgsal = (select  min(avgsal) from (
	select job_id, avg(salary) as avgsal from employees 
    group by job_id
) t);

select  job_id,avg(salary) from  employees 
group by job_id 
having avg(salary)=(
	select  min(avgsal) from (
		select job_id, avg(salary) as avgsal from employees 
		group by job_id
	) t 
);
select * from bookbook;
select * from bookcustomer;
select * from bookitem;
SELECT * FROM booklend;
desc bookitem;

insert into bookcustomer(cname, cpwd, cphone)
values('박상호', 3333, '010-345-12345');

select * from booklend;
desc booklend;

SELECT NOW(), date_add(SYSDATE(),interval  7 day)  FROM DUAL;
INSERT INTO booklend(LENDSDATE, LENDEDATE, CNUM)
VALUES(SYSDATE(), date_add(SYSDATE(),interval  7 day), 4 );

SELECT MAX(LENDNUM) FROM booklend;

desc bookitem;

INSERT INTO BOOKBOOK (ISBN,TITLE,AUTHOR,PUBLISHER,PUBDATE,PRICE)
VALUES('9788992939003','MySQL','이성욱','위키북스','2015-03-04',40000);
INSERT INTO BOOKBOOK (ISBN,TITLE,AUTHOR,PUBLISHER,PUBDATE,PRICE)
VALUES('9788992939004','MSSQL','이성국','한빛','2015-05-04',30000);
INSERT INTO BOOKBOOK (ISBN,TITLE,AUTHOR,PUBLISHER,PUBDATE,PRICE)
VALUES('9788992939005','자바200','조효은','정보','2018-05-04',28000);


INSERT INTO bookitem(isbn,LENDNUM,lendrdate)
values("9788992939005", 7,SYSDATE());
INSERT INTO bookitem(isbn,LENDNUM,lendrdate)
values("9788992939006", 7,SYSDATE());

SELECT * FROM BOOKITEM;
SELECT * FROM BOOKLEND;

SELECT * FROM BOOKITEM
WHERE LENDNUM=7;

SELECT * FROM BOOKBOOK
WHERE ISBN IN (SELECT ISBN FROM BOOKITEM WHERE LENDNUM=7);

INSERT INTO BOOKBOOK (ISBN,TITLE,AUTHOR,PUBLISHER,PUBDATE,PRICE)
VALUES('9788992939010','MySQL1','이성욱','위키북스','2015-03-04',40000);
INSERT INTO BOOKBOOK (ISBN,TITLE,AUTHOR,PUBLISHER,PUBDATE,PRICE)
VALUES('9788992939009','MSSQL2','이성국','한빛','2015-05-04',30000);
INSERT INTO BOOKBOOK (ISBN,TITLE,AUTHOR,PUBLISHER,PUBDATE,PRICE)
VALUES('9788992939008','자바700','조효은','정보','2018-05-04',28000);

insert into bookcustomer(cname, cpwd, cphone)
values('박길준', 5555, '010-325-1224');

select * FROM bookcustomer;

SELECT * FROM BOOKLEND;

select * from bookcustomer;
select * from bookbook;
select * from booklend;
select * from bookitem;
select * from bookbook
where isbn in (select isbn from bookitem where lendnum=13);

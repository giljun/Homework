/* 2019. 04. 10 DB_HOMEWORK */
/* Query 1 */
select * from emp
where hiredate like '2014%';

/* Query 2 */
select * from emp
where ename like '%S%';

/* Query 3 */
select * from emp
where comm is not null;

/* Query 4 */
select avg(sal), max(sal), min(sal), count(*) from emp
group by deptno;

/* Query 5 */
select ename, deptno, sal, (sal*12+(sal*1.5)) as income from emp
where deptno=30;

/* Query 6 */
select ename, sal, floor(sal*0.15) from emp
where sal>=2000;

/* Query 7 */
select avg(sal) as average_sal, sum(sal), max(sal), min(sal) from emp
group by deptno order by average_sal desc;
# HAVING子句的用途
# •查询每个部门中，1982年以后入职的员工超过2个人的部门编号
SELECT deptno
FROM t_emp
WHERE hiredate >= '1982-01-01'
GROUP BY deptno
HAVING COUNT(*) >= 2
ORDER BY deptno asc;


# HAVING子句的特殊用法
# •按照数字1分组，MysQL会依据SELECT子句中的列进行分组，HAVING子句也可以正常使用
SELECT deptno, COUNT(*)
FROM t_emp
GROUP BY 1; #按照第一列分组

SELECT deptno, COUNT(*)
FROM t_emp
GROUP BY 1 HAVING deptno IN (1, 2);

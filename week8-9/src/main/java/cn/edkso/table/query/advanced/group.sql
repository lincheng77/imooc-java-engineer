

/**
  对SELECT子句的要求查询语句中如果含有GROUP BY子句，那么SELECT子句中的内容就必须要遵守规定:
    -SELECT子句中可以包括聚合函数，或者GROUPBY子句的分组列，其余内容均不可以出现在SELECT子句中
 */
# 正确
SELECT deptno, COUNT(*), AVG(sal)
FROM t_emp
GROUP BY deptno;
# 错误（查询结果sal是不对的）
SELECT deptno, COUNT(*), AVG(sal), sal
FROM t_emp
GROUP BY deptno;


#对分组结果集再次做汇总计算
SELECT deptno,
       COUNT(*),
       AVG(sal),
       MAX(sal),
       MIN(sal)
FROM t_emp
GROUP BY deptno
WITH ROLLUP;


#GROUP CONCAT函数
#•GROUP_CONCAT函数可以把分组查询中的某个字段拼接成一个字符串
SELECT deptno, GROUP_CONCAT(ename), COUNT(*)
FROM t_emp
WHERE sal >= 1
GROUP BY deptno;


#各种子句的执行顺序查询语句中，GROUP BY子句应该第几个执行?
# FROM -> WHERE -> GROUP BY -> SELECT -> ORDER BY -> LIMIT

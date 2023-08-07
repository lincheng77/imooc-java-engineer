CREATE TABLE t_dept(
    deptno INT UNSIGNED PRIMARY KEY ,
    dname VARCHAR(20) NOT NULL UNIQUE ,
    tel CHAR(4) UNIQUE
);

CREATE TABLE t_emp(
  empno INT UNSIGNED PRIMARY KEY ,
  ename VARCHAR(20) NOT NULL ,
  sex ENUM('男','女') NOT NULL ,
  deptno INT UNSIGNED NOT NULL ,
  hiredate DATE NOT NULL ,
  FOREIGN KEY (deptno) REFERENCES t_dept(deptno)
);



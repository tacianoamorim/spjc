-- DEFINE O BANCO DE DADOS "COMPANY"
CREATE SCHEMA COMPANY;
USE COMPANY;

-- COMMANDOS DE SQL DDL PARA CRIAÇÃO DAS TABELAS

CREATE TABLE department (
  dname        varchar(25) not null,
  dnumber      integer(4),
  mgrssn       char(9) not null, 
  mgrstartdate date,
  primary key (dnumber),
  key (dname)  
);

CREATE TABLE employee (
  fname    varchar(15) not null, 
  minit    varchar(1),
  lname    varchar(15) not null,
  ssn      char(9),
  bdate    date,
  address  varchar(50),
  sex      enum ('M', 'F'),
  salary   decimal(10,2),
  superssn char(9),
  dno      integer(4),
  primary key (ssn),
  foreign key (superssn) references employee(ssn),
  CONSTRAINT FK_EMP_DEPTO foreign key (dno) references department(dnumber)
);

CREATE TABLE project (
  pname      varchar(25) not null,
  pnumber    integer(4),
  plocation  varchar(15),
  dnum       integer(4) not null,
  primary key (pnumber),
  unique (pname),
  foreign key (dnum) references department(dnumber)
);

CREATE TABLE dept_locations (
  dnumber   integer(4),
  dlocation varchar(15), 
  primary key (dnumber,dlocation),
  foreign key (dnumber) references department(dnumber)
);

CREATE TABLE dependent (
  essn           char(9),
  dependent_name varchar(15),
  sex            char,
  bdate          date,
  relationship   varchar(8),
  primary key (essn,dependent_name),
  CONSTRAINT FK_EMP_DEP foreign key (essn) references employee(ssn) 
     ON DELETE CASCADE ON UPDATE CASCADE;
);

CREATE TABLE works_on (
  essn   char(9),
  pno    integer(4),
  hours  decimal(4,1),
  primary key (essn,pno),
  foreign key (essn) references employee(ssn),
  foreign key (pno) references project(pnumber)
);


-- POVOA AS TABELAS A PARTIR DE ARQUIVOS NO FORMATO .CSV (arquvos *.dat)

-- desabilita a checagem de FOREIGN KEY durante a carga
SET FOREIGN_KEY_CHECKS = 0;

-- Mude o caminho para sua pasta local 

LOAD DATA LOCAL INFILE "A:\\temp\\Elmasri\\ch4\\company\\department.dat"
INTO TABLE department
FIELDS ENCLOSED BY "\"" TERMINATED BY ","
;

LOAD DATA LOCAL INFILE "A:\\temp\\Elmasri\\ch4\\company\\project.dat"
INTO TABLE project
FIELDS ENCLOSED BY "\"" TERMINATED BY ","
;

LOAD DATA LOCAL INFILE "A:\\temp\\Elmasri\\ch4\\company\\dloc.dat" 
INTO TABLE dept_locations 
FIELDS ENCLOSED BY "\"" TERMINATED BY ","
;

LOAD DATA LOCAL INFILE "A:\\temp\\Elmasri\\ch4\\company\\employee.dat" 
INTO TABLE employee 
FIELDS ENCLOSED BY "\"" TERMINATED by ","
;

LOAD DATA LOCAL INFILE "A:\\temp\\Elmasri\\ch4\\company\\dependent.dat" 
INTO TABLE dependent 
FIELDS ENCLOSED BY "\"" TERMINATED BY ","
;

LOAD DATA LOCAL INFILE "A:\\temp\\Elmasri\\ch4\\company\\worksOn.dat" 
INTO TABLE works_on
FIELDS ENCLOSED BY "\"" TERMINATED BY ","
;

-- habilita de novo a checagem de restrições de chaves primárias
SET FOREIGN_KEY_CHECKS = 1;




--###################################
-- Consultas SQL 
-- Autor: Prof. Rinaldo Lima
-- Banco de Dados (BC3) - UFRPE
--###################################

use company;

-- ****************** COMANDOS DIVERSOS ********************

-- Listar a definição da tabela de Empregados

SHOW TABLES;

DESCRIBE EMPLOYEE;

SELECT now() FROM DUAL;

-- funções de data
SELECT 
  CURRENT_DATE(), CURRENT_TIME(), CURRENT_TIMESTAMP(), CURRENT_USER()
FROM DUAL;


-- ****************** COMANDOS DML ********************

-- CONSULTA 1:
-- List a data de nasc. e o endereço do empregado 'John Smith'

SELECT Bdate, Address
FROM EMPLOYEE
WHERE 
  Fname='John'AND 
  Lname='Smith';


-- CONSULTA 2:
-- Liste o nome e o endereço de todos os empregados que trabalham para o departamento de pesquisa
SELECT fname, lname, address
FROM EMPLOYEE, DEPARTMENT
WHERE 
   dname = 'Research' AND 
   Dnumber = Dno;
   
-- CONSULTA 3:
-- Liste o nome e o endereço de todos os empregados que trabalham para o departamento de pesquisa
-- usando a sintaxe de JOIN explicito do SQL 92
SELECT fname, lname, address
FROM 
  EMPLOYEE JOIN DEPARTMENT 
     ON dno = dnumber
WHERE 
   dname = 'Research';
   
-- CONSULTA 4:
-- Liste o nome e o endereço de todos os empregados que trabalham para o departamento de pesquisa
-- usando a sintaxe de JOIN explicito do SQL 92
-- Usando aliases para os nomes de tabelas
SELECT E.fname, E.lname, E.address
FROM 
  EMPLOYEE E JOIN DEPARTMENT D
     ON E.dno = D.dnumber
WHERE 
   D.dname = 'Research';
      

-- CONSULTA 5   - Aliases para tabelas
-- Para todo empregado, listar seu nome e o nome do seu supervisor imediato
SELECT E.Fname, E.Lname, S.Fname, S.Lname
FROM EMPLOYEE AS E, EMPLOYEE AS S
WHERE 
  E.Superssn = S.Ssn;

-- CONSULTA 6   - Aliases para tabelas e nomes de colunas
-- Para todo empregado, listar seu nome e o nome do seu supervisor imediato
SELECT 
  E.Fname as Nome_Emp, 
  E.Lname as Sobrenome_Emp , 
  S.Fname as Nome_Supervisor, 
  S.Lname as Sobrenome_Supervisor
FROM EMPLOYEE AS E, EMPLOYEE AS S
WHERE 
  E.Superssn = S.Ssn;
 
 
-- CONSULTA 7   - Aliases para tabelas e nomes de colunas
-- Para todo empregado, listar seu nome e o nome do seu supervisor imediato
SELECT 
  concat(E.Fname, ' ', E.Lname) as Nome_Emp , 
  concat(S.Fname, ' ', S.Lname) as Nome_Supervisor
FROM 
   EMPLOYEE AS E, EMPLOYEE AS S
WHERE 
  E.superssn = S.ssn;
  
    
-- CONSULTA 8
-- Mostre o salários de todos os empregados trabalhando no projeto 'ProductX' 
-- com 10% de aumento
SELECT E.Fname, E.Lname, E.SALARY Salario_Atual, 1.1 * E.Salary AS Novo_Salario
FROM EMPLOYEE AS E, WORKS_ON AS W, PROJECT AS P
WHERE 
 E.Ssn = W.Essn AND 
 W.Pno = P.Pnumber AND 
 P.Pname = 'ProductX';


-- CONSULTA 9
-- liste o nome dos empregados com salário entre 30000 e 40000 do departamento de Software
SELECT  concat(Fname, ' ', Lname) as Nome_Emp 
FROM EMPLOYEE
WHERE 
  (Salary BETWEEN 30000 AND 40000) AND 
   Dno = 6;
   
   
--  Selecione todos os empregados e todas as combinações de empregados e departamentos
-- CONSULTA 10
SELECT * FROM employee;

SELECT  * FROM employee, department;
     
     
-- CONSULTA 11
-- Gere o relatório de pessoal contendo o nome dos empregados e os projetos nos quais eles
-- trabalham, ordernados pelo departamento e, dentro de cada departamento, ordenado alfabeticamente 
-- pelo nome e sobrenome dos empregados

SELECT D.Dname Nome_Depto, P.Pname NomeProj, E.Lname SobreNome_Emp, E.Fname Nome_Emp
FROM DEPARTMENT D, EMPLOYEE E, WORKS_ON W, PROJECT P
WHERE 
  D.Dnumber = E.Dno AND 
  E.Ssn = W.Essn AND 
  W.Pno = P.Pnumber
ORDER BY D.Dname, E.Lname, E.Fname;


-- CONSULTA 12
-- Gere o relatório de pessoal, para o departamento de pesquisa, contendo o nome dos empregados e os projetos nos quais eles
-- trabalham, ordernados pelo departamento e, dentro de cada departamento, ordenado alfabeticamente 
-- pelo nome e sobrenome dos empregados

SELECT D.Dname Nome_Depto, P.Pname NomeProj, E.Lname SobreNome_Emp, E.Fname Nome_Emp
FROM DEPARTMENT D, EMPLOYEE E, WORKS_ON W, PROJECT P
WHERE 
  D.Dnumber = E.Dno AND 
  E.Ssn = W.Essn AND 
  D.dname = 'Research' AND
  W.Pno = P.Pnumber
ORDER BY D.Dname, E.Lname, E.Fname;


-- CONSULTA 13
-- Exemplo anterior, mas com a Sintaxe de junção do SQL 92 
SELECT D.Dname Nome_Depto, P.Pname NomeProj, E.Lname SobreNome_Emp, E.Fname Nome_Emp
FROM
   DEPARTMENT D JOIN EMPLOYEE E 
      ON D.Dnumber = E.Dno  
   JOIN WORKS_ON W
	  ON E.ssn = W.essn  
   JOIN PROJECT P 
     ON W.pno = P.Pnumber
ORDER BY D.Dname, E.Lname, E.Fname;


-- CONSULTA 14
-- Exemplo anterior, mas apenas para o Projetos de pesquisa
SELECT D.Dname Nome_Depto, P.Pname NomeProj, E.Lname SobreNome_Emp, E.Fname Nome_Emp
FROM
   DEPARTMENT D JOIN EMPLOYEE E 
      ON D.Dnumber = E.Dno  
   JOIN WORKS_ON W
	  ON E.ssn = W.essn  
   JOIN PROJECT P 
     ON W.pno = P.Pnumber
WHERE
   D.dname = 'Research'     
ORDER BY D.Dname, E.Lname, E.Fname;


-- CONSULTA 15
-- Selecione todos os salarios de todos os empregados ordenados
-- admite repetições
SELECT ALL salary
from EMPLOYEE
order by 1;


-- CONSULTA 16
-- Selecione todos os distintos salarios
SELECT DISTINCT salary 
from EMPLOYEE
order by 1;


-- CONSULTA 17
-- Gere a lista de todos os números de projetos que envolvam o empregado 
-- "Smith" e que seja um empregado do departamento ou gerente do departamento que 
-- controla o projeto

(SELECT DISTINCT Pnumber
from EMPLOYEE E, department D, PROJECT P
WHERE  
   D.mgrssn = E.ssn and
   D.dnumber = P.dnum AND
   E.lname = "Smith"
)

UNION

(SELECT DISTINCT Pnumber
from EMPLOYEE E, WORKS_ON W, PROJECT P
WHERE  
   W.essn = e.ssn AND
   W.pno = P.pnumber AND
   E.lname = "Smith"

) ;

-- CONSULTA 18
--  Funções de casamento de padrões e operadores aritméticos
-- Selecione nome dos empregados que moram em algum endereço de Houston
SELECT Fname, Lname
FROM EMPLOYEE E
WHERE
  e.address  LIKE "%Houston%";
  
-- CONSULTA 19
-- Encontre  todos os empregados que nasceram durante os anos 50
SELECT Fname, Lname
FROM EMPLOYEE E
WHERE
  e.bdate  LIKE '__5%';
  
  
-- ********* OPERAÇÕES DIU (DELETE, INSERT, UPDATE) *********
-- todos as colunas na mesma ordem que aparecem na tabela
INSERT INTO EMPLOYEE 
VALUES ('Richard', 'K', 'Martini', '66666666', '1952-12-30', '98, Forest Italy', 'M', 37000, '333333300', 4);


-- só as colunas obrigatórias
INSERT INTO EMPLOYEE (Fname, Lname, ssn, Dno)
VALUES ('Richard_2', 'Martini_2', '66666667', 5);

-- inserindo dados numa tabela a partir de outra
DROP TABLE works_on_info;
CREATE TABLE WORKS_ON_INFO (
  Emp_name varchar(15),
  Proj_name varchar(15),
  hours_per_week decimal (3,1)
);

   
-- não é possivel gerar a tabela. 
-- use instrução sql ALTER TABLE resolver isso

ALTER TABLE WORKS_ON_INFO 
  MODIFY column Proj_Name varchar(30);
  

INSERT INTO WORKS_ON_INFO (EMP_NAME, PROj_name, hours_per_week)
SELECT
  e.lname, p.pname, w.hours
FROM 
  PROJECT P, WORKS_ON W, EMPLOYEE E
WHERE
  P.pnumber = w.pno AND
  W.essn = E.ssn;
  

DELETE FROM works_on_info 
WHERE
  Emp_name = "Wong";
  

UPDATE works_on_info
SET hours_per_week = 20
WHERE
  Emp_name = 'Walls'
  








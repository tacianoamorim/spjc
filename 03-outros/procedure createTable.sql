delimiter //
DROP PROCEDURE IF EXISTS COMPANY.createTable //

create procedure COMPANY.createTable()
begin
	create table if not exists COMPANY.LOG_AUDIT_GERENTE ( 
	 sequencial integer AUTO_INCREMENT NOT NULL, 
	 empid integer,
	 user varchar(40), 
	 emp_fullname varchar(200),
	 old_salary decimal, 
	 new_salary decimal, 
	 percentual_aumento decimal, 
	 dataAlteracao Date,
	 primary key (sequencial)
	);

end; //

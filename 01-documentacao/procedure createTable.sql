create procedure createTable
begin
	use company;
	create table if not exists LOG_AUDIT_GERENTE ( 
	 sequencial integer AUTO_INCREMENT NOT NULL, 
	 empid integer,
	 user varchar(40), 
	 emp_fullname varchar(200),
	 old_salary decimal, 
	 new_salary decimal, 
	 percentual_aumento decimal, 
	 dataAlteracao Date,
	 primary key (sequencial),
	 foreign key (ssn) references employee(empid)
	);

end; 
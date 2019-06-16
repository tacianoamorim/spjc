delimiter :
create function COMPANY.calculaMediaSalario() 
returns double
deterministic
begin
	declare media double;
	set media= 0;
    
    select avg(salary) INTO media 
    from COMPANY.employee;
    
	return media;

end :
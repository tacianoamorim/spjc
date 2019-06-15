delimiter :
create function salarioMedio(id integer) 
returns decimal
deterministic
begin
	
	declare valor decimal;
	if old.dno is not null then
		update deptsal
		set totalsalary= totalsalary - old.salary
		where dnumber= old.dno;
	end if;
	
	if new.dno is not null then
		update deptsal
		set totalsalary= totalsalary - new.salary
		where dnumber= new.dno;
	end if;
end :

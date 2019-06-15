delimiter :
create trigger update_salary
after insert on employee
for each row
begin

	if new.dno is not null then
		update deptsal
		set totalsalary= totalsalary - new.salary
		where dnumber= new.dno;
	end if;
end :
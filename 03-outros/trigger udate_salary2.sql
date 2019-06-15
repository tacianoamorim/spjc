delimiter :
create trigger update_salary2
after update on employee
for each row
begin

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

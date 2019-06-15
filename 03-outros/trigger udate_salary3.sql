delimiter :
create trigger update_salary3
before delete on employee
for each row
begin
	if (old.dno is not null) then
		update deptsal
		set totalsalary= totalsalary - old.salary
		where dnumber= old.dno;
	end if;
end :
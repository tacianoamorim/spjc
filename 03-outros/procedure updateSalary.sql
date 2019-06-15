delimiter //
DROP PROCEDURE IF EXISTS COMPANY.updateSalary //

create procedure COMPANY.updateSalary ()
begin 

	declare done int default 0;
    declare current_dnum int;
    declare dnumcur cursor for select dnumber from COMPANY.depsal;
    declare continue handler for not found set done= 1;
    
    open dnumcur;
    
    repeat
		fetch dnumcur into current_dnum;
		update COMPANY.deptsal
		set totalsalary = (select sum(salary) from COMPANY.employee  where dno= current_dnum)
		where dnumber= current_dnum;
	until done
    end repeat;	
    
    close dnumcur;
    
end; //

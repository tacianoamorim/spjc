delimiter //
DROP PROCEDURE IF EXISTS COMPANY.updateSalary //

create procedure COMPANY.updateSalary (IN param1 int)
begin 
    update COMPANY.deptsal
    set totalsalary = (select sum(salary) from COMPANY.employee  where dno= param1)
    where dnumber= param1;
    
end; //

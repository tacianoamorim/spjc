delimiter //
DROP PROCEDURE IF EXISTS COMPANY.giveRaise //

create procedure COMPANY.giveRaise (in amount double)
begin 

	declare done int default 0;
    declare eid int;
    declare sal int;
    declare emprec cursor for select id, salary from COMPANY.employee;
    declare continue handler for not found set done= 1;
    
    open emprec;
    
    repeat
		fetch emprec into eid, sal;
		update COMPANY.employee
		set salary = sal + round(sal * amount)
		where id= eid;
	until done
    end repeat;	
    
    close emprec;
    
end; //

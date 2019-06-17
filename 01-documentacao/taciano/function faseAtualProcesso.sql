delimiter //
DROP FUNCTION IF EXISTS DBSPJC.faseAtualProcesso //

delimiter :
create function DBSPJC.faseAtualProcesso(npu VARCHAR(20)) 
returns int
deterministic
begin
	declare fase double;
	set fase= 0;
    select avg(salary) INTO media 
    from COMPANY.employee;
    select *
	from DBSPJC.ProcessoFase pf
	where pf.processo= '00051564620198170002'
	AND id = (
	select MAX(id)
	from DBSPJC.ProcessoFase
	where processo= pf.processo
	)    
	return fase;

end :



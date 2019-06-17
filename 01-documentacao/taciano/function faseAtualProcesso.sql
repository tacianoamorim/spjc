delimiter //
DROP FUNCTION IF EXISTS DBSPJC.faseAtualProcesso //

delimiter :
create function DBSPJC.faseAtualProcesso(npu VARCHAR(20)) 
returns int
deterministic
begin
	declare codigoFase double;
	set codigoFase= 0;
    
    select id INTO codigoFase
	from DBSPJC.ProcessoFase pf
	where pf.processo= npu
		and pf.faseAtual= 1;
	   
	return codigoFase;

end :

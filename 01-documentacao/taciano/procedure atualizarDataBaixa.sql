delimiter //
DROP PROCEDURE IF EXISTS DBSPJC.atualizarDataBaixa //

create procedure DBSPJC.atualizarDataBaixa(IN pNpu varchar(20))
begin 
	declare done int default 0;
    
    declare nNpu1 varchar(20);
    declare nFase1 int;
    declare nFaseAtual1 bool;
    
    declare dproccur cursor for select processo, fase, faseAtual from DBSPJC.ProcessoFase where npu= pNpu;
    declare continue handler for not found set done= 1;
    
    open dproccur;
    
    repeat
		fetch dproccur into nNpu1, nFase1, nFaseAtual1;
		if (nFase1 = 11 && nFaseAtual1 = true) then
			update DBSPJC.processo
			set dataBaixa = now(), tipoBaixa= 'J'
			where npu= pNpu;
		end if;         
	until done
    end repeat;	
    
    close dproccur;
    
end; //


call DBSPJC.atualizarDataBaixa('00051564620198170002');
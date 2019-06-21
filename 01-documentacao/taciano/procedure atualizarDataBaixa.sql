delimiter //
DROP PROCEDURE IF EXISTS DBSPJC.atualizarDataBaixa //

create procedure DBSPJC.atualizarDataBaixa(IN pNpu varchar(20))
begin 
	declare done int default 0;
    
    declare nNpu1 varchar(20);
    declare nFase1 int;
    declare nFaseAtual1 bool;
    
    declare dproccur cursor for select fase, faseAtual from DBSPJC.ProcessoFase where processo= pNpu;
    declare continue handler for not found set done= 1;
    
    open dproccur;

    repeat
		# A ultima fase do processo e codigo 11 - Finalizado
        # E- Execução realizada.
		fetch dproccur into nFase1, nFaseAtual1;
		if (nFase1 = 11 && nFaseAtual1 = true) then
			UPDATE DBSPJC.Processo SET dataBaixa = curdate(), tipoBaixa= 'E' WHERE npu= pNpu;
		end if;         
	until done
    end repeat;	
    
    close dproccur;
    
end; //

# call DBSPJC.atualizarDataBaixa('00051564620198170002');
# UPDATE DBSPJC.Processo set dataBaixa = curdate(), tipoBaixa= 'E' where npu= '00051564620198170002';
# UPDATE DBSPJC.Processo set dataBaixa = NULL, tipoBaixa= NULL where npu= '00051564620198170002';
# select * from DBSPJC.Processo where npu= '00051564620198170002'
# select fase, faseAtual from DBSPJC.ProcessoFase where processo= '00051564620198170002';
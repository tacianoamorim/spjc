#3. Gerar a primeira AUDIENCIA quando se cadastra um novo PROCESSO
delimiter //
	drop procedure if exists dbspjc.primeira_audiencia // 
CREATE PROCEDURE dbspjc.primeira_audiencia (in id_processo varchar(20))
BEGIN
	declare done int default 0;
    
	declare nnpu varchar(20);
	declare idaud int;
	DECLARE processoaud cursor for SELECT audiencia,aud  FROM dbspjc.processo where processo = id_processo;
	open processoaud;
repeat
 fetch processoaud into idaud,nnpu;
    	if (nnpu = 1) then
			UPDATE dbspjc.audiencia SET npu = 1  WHERE npu= id_processo;
		end if;         
	until done
end repeat;	
    close processoaud;
END //

#call dbspjc.primeira_audiencia('00051564620198170002');
# UPDATE dbspjc.processo set npu where npu= '00051564620198170002';
# UPDATE DBSPJC.Processo set npu = NULL,  where npu= '00051564620198170002';
# select * from DBSPJC.Processo where npu= '00051564620198170002'
# select audiencia, aud from DBSPJC.Processo where processo= '00051564620198170002';


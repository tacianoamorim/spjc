delimiter //
DROP FUNCTION IF EXISTS DBSPJC.buscarPautaProxima //

create function DBSPJC.buscarPautaProxima(dataRef VARCHAR(10)) 
returns int
deterministic
begin
	declare idPautaProxima int;
    declare dataIgual date;
    declare dataMaiorProxima date;
    declare dataMenorProxima date;
    
	select dataAgendamento into dataIgual from Pauta where dataAgendamento = dataRef;
	select MAX(dataAgendamento) into dataMenorProxima from Pauta where dataAgendamento < dataRef;
	select MIN(dataAgendamento) into dataMaiorProxima from Pauta where dataAgendamento > dataRef;    
    
    if ( dataIgual = dataRef ) then
		select id into idPautaProxima from Pauta where dataAgendamento = dataRef;
        
    elseif ( dataMenorProxima < dataRef ) then
		select id into idPautaProxima from Pauta where dataAgendamento < dataRef;
    else 
		select id into idPautaProxima from Pauta where dataAgendamento > dataRef; 
    end if;
 	   
	return idPautaProxima;

end; //

 SELECT DBSPJC.buscarPautaProxima('2019-07-10');
 
 
 
 
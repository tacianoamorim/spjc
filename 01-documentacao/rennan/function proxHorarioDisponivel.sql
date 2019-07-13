delimiter //
DROP FUNCTION IF EXISTS DBSPJC.proxHorarioDisponivel //

create function DBSPJC.proxHorarioDisponivel(juizado int, dataPauta VARCHAR(10)) 
returns int
begin
	declare horaRet int;
    set horaRet= 13;
    
    SELECT max(hora) INTO horaRet 
    FROM DBSPJC.Pauta p
		INNER JOIN DBSPJC.Audiencia a ON a.pauta= p.id
	WHERE p.juizado= juizado and p.dataAgendamento= dataPauta;
    	   
	
       
	if ( horaRet < 19) then
		set horaRet= (horaRet + 1);
    end if;
    
	return horaRet;

end; //

# SELECT * FROM DBSPJC.Pauta p 	INNER JOIN DBSPJC.Audiencia a ON a.pauta= p.id
# select DBSPJC.proxHorarioDisponivel(2, '2019-06-10');
delimiter //
DROP TRIGGER IF EXISTS DBSPJC.audienciaInsert; //

create trigger DBSPJC.audienciaInsert
before insert on DBSPJC.Audiencia
for each row
begin
	update DBSPJC.Pauta
	set qtdeProcesso= ( qtdeProcesso + 1 )
	where id = new.pauta;
end; //
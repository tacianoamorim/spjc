delimiter //
DROP TRIGGER IF EXISTS DBSPJC.audienciaDelete; //

create trigger DBSPJC.audienciaDelete
after delete on DBSPJC.Audiencia
for each row
begin
    declare qtde int;
    select qtdeProcesso INTO qtde from DBSPJC.Pauta where id= old.pauta;

	if ( qtde >= 1 ) then
		update DBSPJC.Pauta
		set qtdeProcesso= ( qtdeProcesso - 1 )
		where id = old.pauta;
	end if;

end; //
#3. para atribuir uma sala disponível a uma AUDIENCIA

delimiter //
DROP TRIGGER IF EXISTS atribuiaudiencia; //
create trigger atribuiaudiencia
before insert on dbspjc.audiencia
for each row
begin

	if (esta_vaga = 0) then
	update dbspjc.audiencia
	set audiencia.id=  sala.id 
	where id ;

end if;
end; //

call atribuiaudiencia;
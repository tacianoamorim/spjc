delimiter //
DROP VIEW IF EXISTS DBSPJC.listarServidores; //

CREATE VIEW DBSPJC.listarServidores AS

SELECT s.cpf, s.nome AS 'nomeServidor', s.dataInicio, 
	(CASE s.tipoServidor
		WHEN 'A' THEN 'Analista Judiciário'
        WHEN 'T' THEN 'Tecnico Judiciário'
		ELSE 'Conciliador'
	END) AS 'tipoServidor',
  
	j.id AS 'idJuizado',
	j.nome AS 'nomeJuizado'
FROM DBSPJC.Servidor s
	INNER JOIN DBSPJC.Juizado j ON j.id= s.juizado

# SELECT * FROM DBSPJC.listarServidores WHERE dataInicio BETWEEN '2000-09-01' AND '2000-09-01'

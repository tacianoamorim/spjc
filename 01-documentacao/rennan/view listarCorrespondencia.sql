delimiter //
DROP VIEW IF EXISTS DBSPJC.listarCorrespondencia; //

CREATE VIEW DBSPJC.listarCorrespondencia AS

SELECT s.cpf, s.nome AS 'nomeServidor', 
		t.id, t.nome AS 'nomeTestemunha',
        crt.texto, crt.dataHoraEnvio, crt.numeroAR
FROM DBSPJC.Servidor s
	INNER JOIN DBSPJC.ComunicacaoTestemunha ct ON ct.servidor= s.cpf
    INNER JOIN DBSPJC.Testemunha t ON t.id = ct.testemunha
    INNER JOIN DBSPJC.CorrespondenciaTestemunha crt ON crt.comunicacaoTestemunha= ct.id

# SELECT * FROM DBSPJC.listarCorrespondencia WHERE dataHoraEnvio BETWEEN '2000-09-01 00:00:00' AND '2000-09-01 23:59:00'

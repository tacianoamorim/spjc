#Gerar relatório de "Processos" envolvendo PROCESSO, AUDIÊNCIA, PAUTA, JUIZADO, PARTE E REPRESENTANTE 
#em um ano/semestre fornecido como parâmetro de entrada
delimiter //
DROP VIEW IF EXISTS DBSPJC.juizadoPautaAudiencia; //

CREATE VIEW DBSPJC.juizadoPautaAudiencia AS
SELECT j.nome, pt.dataAgendamento, au.hora, au.processo, 
	CONCAT( DATE_FORMAT(pt.dataAgendamento, '%d/%m/%Y'), ' ', 
			DATE_FORMAT(au.hora, '%H:%i') ) AS dataHora
FROM DBSPJC.Pauta pt
	INNER JOIN DBSPJC.Audiencia au ON au.pauta= pt.id
    INNER JOIN DBSPJC.Processo prc ON prc.npu= au.processo
    INNER JOIN DBSPJC.Juizado j ON j.id = prc.juizado; //
#select * from DBSPJC.juizadoPautaAudiencia
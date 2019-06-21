#Gerar relatório de "Processos" envolvendo PROCESSO, AUDIÊNCIA, PAUTA, JUIZADO, PARTE E REPRESENTANTE 
#em um ano/semestre fornecido como parâmetro de entrada
delimiter //
DROP VIEW IF EXISTS DBSPJC.partasAudiencias; //

CREATE VIEW DBSPJC.partasAudiencias AS
SELECT pt.id, pt.estadoPauta, pt.dataAgendamento,au.hora, au.processo 
FROM DBSPJC.Pauta pt
	INNER JOIN DBSPJC.Audiencia au ON au.pauta= pt.id; //
    
 
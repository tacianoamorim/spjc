#Gerar relatório de "Processos" envolvendo PROCESSO, AUDIÊNCIA, PAUTA, JUIZADO, PARTE E REPRESENTANTE 
#em um ano/semestre fornecido como parâmetro de entrada
USE DBSPJC;

CREATE VIEW view_name AS
SELECT pt.id, pt.estadoPauta, pt.dataAgendamento,au.hora, au.processo 
FROM DBSPJC.Pauta pt
	INNER JOIN DBSPJC.Audiencia au ON au.pauta= pt.id;
    
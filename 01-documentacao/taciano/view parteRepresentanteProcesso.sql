#Gerar relatório de "Processos" envolvendo PROCESSO, AUDIÊNCIA, PAUTA, JUIZADO, PARTE E REPRESENTANTE 
#em um ano/semestre fornecido como parâmetro de entrada
delimiter //
DROP VIEW IF EXISTS DBSPJC.parteRepresentanteProcesso; //

CREATE VIEW DBSPJC.parteRepresentanteProcesso AS

SELECT pp.processo, 
	(CASE p.polo
		WHEN 'A' THEN 'Ativo'
		ELSE 'Passivo'
	END) AS 'polo',
	'P' AS 'Tipo', CONCAT(pf.nome, ' (Parte)') AS 'nomePTR' 
FROM DBSPJC.ProcessoParte pp
	INNER JOIN DBSPJC.Parte p ON p.id= pp.parte
    INNER JOIN DBSPJC.ParteFisica pf ON pf.parte = p.id
UNION

SELECT ppj.processo, 
	(CASE pj.polo
		WHEN 'A' THEN 'Ativo'
		ELSE 'Passivo'
	END) AS 'polo',
	'P' AS 'Tipo', CONCAT(pjr.razaoSocial,' (Parte)') AS 'nomePTR' 
FROM DBSPJC.ProcessoParte ppj
	INNER JOIN DBSPJC.Parte pj ON pj.id= ppj.parte
    INNER JOIN DBSPJC.ParteJuridica pjr ON pjr.parte = pj.id
UNION

SELECT pr.processo,	
	(CASE r.polo
		WHEN 'A' THEN 'Ativo'
		ELSE 'Passivo'
	END) AS 'polo',
	'R' AS 'Tipo', CONCAT(r.nome, ' (Representante)') AS 'nomePTR' 
FROM DBSPJC.ProcessoRepresentante pr
	INNER JOIN DBSPJC.Representante r ON r.cpf= pr.representante
UNION

SELECT pt.processo, 
	(CASE t.polo
		WHEN 'A' THEN 'Ativo'
		ELSE 'Passivo'
	END) AS polo,
	'T' AS 'Tipo', CONCAT(t.nome, ' (Testemunha)') AS 'nomePTR' 
FROM DBSPJC.ProcessoTestemunha pt
	INNER JOIN DBSPJC.Representante t ON t.cpf= pt.testemunha
//
    
#select * from DBSPJC.parteRepresentanteProcesso
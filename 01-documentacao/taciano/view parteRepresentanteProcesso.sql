#Gerar relatório de "Processos" envolvendo PROCESSO, AUDIÊNCIA, PAUTA, JUIZADO, PARTE E REPRESENTANTE 
#em um ano/semestre fornecido como parâmetro de entrada
delimiter //
DROP VIEW IF EXISTS DBSPJC.parteRepresentanteProcesso; //

CREATE VIEW DBSPJC.parteRepresentanteProcesso AS

SELECT pp.processo, p.polo, 'P' AS 'Tipo', pf.nome AS 'nome' 
FROM DBSPJC.ProcessoParte pp
	INNER JOIN DBSPJC.Parte p ON p.id= pp.parte
    INNER JOIN DBSPJC.ParteFisica pf ON pf.parte = p.id
UNION

SELECT ppj.processo, pj.polo, 'P' AS 'Tipo', pjr.razaoSocial AS 'nome'
FROM DBSPJC.ProcessoParte ppj
	INNER JOIN DBSPJC.Parte pj ON pj.id= ppj.parte
    INNER JOIN DBSPJC.ParteJuridica pjr ON pjr.parte = pj.id
UNION

SELECT pr.processo, r.polo, 'R' AS 'Tipo', r.nome AS 'nome'
FROM DBSPJC.ProcessoRepresentante pr
	INNER JOIN DBSPJC.Representante r ON r.cpf= pr.representante
UNION

SELECT pt.processo, t.polo, 'T' AS 'Tipo', t.nome AS 'nome' 
FROM DBSPJC.ProcessoTestemunha pt
	INNER JOIN DBSPJC.Representante t ON t.cpf= pt.testemunha
//
    
#select * from DBSPJC.parteRepresentanteProcesso
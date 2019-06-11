# Listar todas os envolvidos no processo
SELECT p.npu, pf.nome, pt.email, 'Parte', pt.polo
FROM Processo p 
	INNER JOIN ProcessoParte pp ON p.npu= pp.processo
	INNER JOIN Parte pt ON pt.id= pp.parte
	INNER JOIN ParteFisica pf ON pf.parte= pt.id
WHERE p.npu= '00051564620198170002'
UNION
SELECT p.npu, pj.razaosocial, pt.email, 'Parte', pt.polo
FROM Processo p 
	INNER JOIN ProcessoParte pp ON p.npu= pp.processo
	INNER JOIN Parte pt ON pt.id= pp.parte
	INNER JOIN ParteJuridica pj ON pj.parte= pt.id	
WHERE p.npu= '00051564620198170002'
UNION
SELECT p.npu, t.nome, t.email, 'Testemunha', t.polo
FROM Processo p 
	INNER JOIN ProcessoTestemunha pt ON p.npu= pt.processo
	INNER JOIN Testemunha t ON t.id= pt.testemunha 
WHERE p.npu= '00051564620198170002'
UNION
SELECT p.npu, r.nome, r.email, 'Representante', r.polo
FROM Processo p 
	INNER JOIN ProcessoRepresentante pr ON p.npu= pr.processo
	INNER JOIN Representante r ON r.cpf= pr.representante 
WHERE p.npu= '00051564620198170002'	
ORDER BY 5, 1

# Listar todas as audiencias com estado a realizar
SELECT p.dataAgendamento, m.nome AS 'Magistrado', prc.npu 
FROM Pauta p
	INNER JOIN Audiencia a  ON a.pauta= p.id
	INNER JOIN Processo prc ON a.processo= prc.npu
	INNER JOIN Magistrado m ON m.cpf= a.magistrado
WHERE p.estadoPauta= 1
ORDER BY p.dataAgendamento, m.nome, 

# Listar todas as parcelas de uma execucao de um processo
SELECT prc.npu, r.numeroParcela, r.dataVencimento, Format(r.valor,2) AS 'ValorDivida'
FROM Processo p
	INNER JOIN Execucao e  ON e.processo= p.npu
	INNER JOIN Recebimento r ON r.execucao= e.id
	INNER JOIN Processo prc ON prc.npu= e.processo
WHERE prc.npu= '00051564620198170002'
ORDER BY prc.npu, r.numeroParcela

# Soma todas as parcelas de uma execucao de um processo
SELECT prc.npu, Format(e.ValorTotalDivida,2) AS 'Valor da divida', Format(sum(r.valor),2) AS 'Valor total parcelado'
FROM Processo p
	INNER JOIN Execucao e  ON e.processo= p.npu
	INNER JOIN Recebimento r ON r.execucao= e.id
	INNER JOIN Processo prc ON prc.npu= e.processo
WHERE prc.npu= '00051564620198170002'
GROUP BY prc.npu, e.ValorTotalDivida
	
	
	
	
	
	
	
	
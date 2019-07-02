# CREATE USER
CREATE USER 'ADMINISTRADOR'@'%' IDENTIFIED By 'ADM_123';
CREATE USER 'MAGISTRADO'@'%' IDENTIFIED By 'MAG_123';
CREATE USER 'ANALISTA_JUDICIARIO'@'%' IDENTIFIED By 'ANA_123';
CREATE USER 'TECNICO_JUDICIARIO'@'%' IDENTIFIED By 'TEC_123';
CREATE USER 'CONCILIADOR'@'%' IDENTIFIED By 'CON_123';
CREATE USER 'REPRESENTANTE'@'%' IDENTIFIED By 'REP_123';

# GRANT ADMINISTRADOR
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON *.* TO 'ADMINISTRADOR'@'%';

# GRANT MAGISTRADO
GRANT SELECT, EXECUTE ON *.* TO 'MAGISTRADO'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON *.* TO 'MAGISTRADO'@'%';
INSERT INTO Magistrado M 
INSERT INTO Endereco (cep, cidade, estado, bairro, rua) VALUES
INSERT INTO Documento (id,magistrado,servidor,representante,processo,documentoprincipal,tipodocumento,datacriacao,texto) VALUES 


# GRANT ANALISTA_JUDICIARIO
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON *.* TO 'ANALISTA_JUDICIARIO'@'%';
INSERT INTO Endereco (cep, cidade, estado, bairro, rua) VALUES
INSERT INTO Servidor (cpf,dataFim,dataInicio,endereco,juizado,matricula,nome,numeroEndereco,senha,tipoServidor) VALUES 
INSERT INTO CursoEspecializacao (id,servidor,dataConclusao,nivel,nome,cargaHoraria) VALUE 
INSERT INTO Parte (id, email,nemeroEndereco,polo,telefone) VALUES          
INSERT INTO ParteFisica (parte,dataNascimento,documentoIdentificacao,estadoCivil,nome,nomeMae,nomePai,tipoDocumentoIdentificacao) VALUES 
INSERT INTO ParteJuridica (parte,cnpj,razaoSocial) VALUES 
INSERT INTO Execucao (id,processo,dataRegistro,numeroParcelas,valorTotalDivida) VALUES 
INSERT INTO Recebimento (id,execucao,dataVencimento,numeroParcela,observacao,valor) VALUES 
INSERT INTO Representante (cpf,nome,email,matricula,numeroEndereco,oab,polo,senha,telefone,tipoRepresentante,endereco) VALUES 
INSERT INTO Pauta (id,dataAgendamento,estadoPauta,qtdeProcesso) VALUES 
INSERT INTO Testemunha(id,nome, email, numeroEndereco, polo, telefone, dataNascimento ) VALUES 
INSERT INTO ProcessoTestemunha (processo,testemunha) VALUES 
INSERT INTO Documento (id,magistrado,servidor,representante,processo,documentoprincipal,tipodocumento,datacriacao,texto) VALUES 
INSERT INTO ComunicacaoParte (id, parte,servidor) VALUES 
INSERT INTO ComunicacaoTestemunha (id, testemunha,servidor) VALUES 
INSERT INTO CorrespondenciaParte (id, comunicacaoParte, texto, dataHoraEnvio, tipoComunicacao, numeroAR, dataHoraRecebimento) VALUES 
INSERT INTO CorrespondenciaTestemunha (id, comunicacaoTestemunha, texto, dataHoraEnvio, tipoComunicacao, numeroAR, dataHoraRecebimento) VALUES 


# GRANT TECNICO_JUDICIARIO
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON *.* TO 'TECNICO_JUDICIARIO'@'%';
INSERT INTO Endereco (cep, cidade, estado, bairro, rua) VALUES
INSERT INTO Servidor (cpf,dataFim,dataInicio,endereco,juizado,matricula,nome,numeroEndereco,senha,tipoServidor) VALUES 
INSERT INTO Execucao (id,processo,dataRegistro,numeroParcelas,valorTotalDivida) VALUES 
INSERT INTO Recebimento (id,execucao,dataVencimento,numeroParcela,observacao,valor) VALUES 
INSERT INTO Movimentacao (id, processo, servidor, tipoMovimentacao, dataHota) VALUES 
INSERT INTO Documento (id,magistrado,servidor,representante,processo,documentoprincipal,tipodocumento,datacriacao,texto) VALUES 


# GRANT CONCILIADOR
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON *.* TO 'CONCILIADOR'@'%';
INSERT INTO Endereco (cep, cidade, estado, bairro, rua) VALUES
INSERT INTO Servidor (cpf,dataFim,dataInicio,endereco,juizado,matricula,nome,numeroEndereco,senha,tipoServidor) VALUES 
INSERT INTO Documento (id,magistrado,servidor,representante,processo,documentoprincipal,tipodocumento,datacriacao,texto) VALUES 

# GRANT REPRESENTANTE
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON *.* TO 'REPRESENTANTE'@'%';
INSERT INTO Parte (id, email,nemeroEndereco,polo,telefone) VALUES          
INSERT INTO ParteFisica (parte,dataNascimento,documentoIdentificacao,estadoCivil,nome,nomeMae,nomePai,tipoDocumentoIdentificacao) VALUES 
INSERT INTO ParteJuridica (parte,cnpj,razaoSocial) VALUES 
INSERT INTO Processo (npu, dataAjuizamento, juizado, observacao) VALUES
INSERT INTO Representante (cpf,nome,email,matricula,numeroEndereco,oab,polo,senha,telefone,tipoRepresentante,endereco) VALUES 
INSERT INTO Testemunha(id,nome, email, numeroEndereco, polo, telefone, dataNascimento ) VALUES 
INSERT INTO ProcessoTestemunha (processo,testemunha) VALUES 
INSERT INTO ProcessoFeito (processo, feito) VALUES 
INSERT INTO ProcessoParte (processo,parte) VALUES 
INSERT INTO Documento (id,magistrado,servidor,representante,processo,documentoprincipal,tipodocumento,datacriacao,texto) VALUES 


# SELECT * FROM mysql.user;








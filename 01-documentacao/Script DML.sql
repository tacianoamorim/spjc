SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;

USE DBSPJC;

INSERT INTO TipoMovimentacao (id, nome) VALUES
(1, 'Concluso para despacho'),
(2, 'Concluso para decisão'),
(3, 'Concluso para sentença'),
(4, 'Finalizar PAJ'),
(5, 'Inclusão em pauta'),
(6, 'Petição'),
(7, 'Mero expediente - Despacho'),
(8, 'Julgamento'),
(9, 'Distribuição');

INSERT INTO TipoDocumento (id, nome) VALUES
(1, 'Documento comprobatório'),
(2, 'Petição'),
(3, 'Sentença'),
(4, 'Decisão'),
(5, 'Despacho'),
(6, 'Termo de Conciliação');

INSERT INTO EstadoPauta (id, nome) VALUES
(1, 'A realizar'),
(2, 'Realizada'),
(3, 'Adiada'),
(4, 'Antecipada'),
(5, 'Remarcada'),
(6, 'Cancelada'),
(7, 'Suspensa e Bloqueada');

INSERT INTO EstadoAudiencia (id, nome) VALUES
(1, 'A realizar'),
(2, 'Realizada'),
(3, 'Adiada'),
(4, 'Antecipada'),
(5, 'Remarcada'),
(6, 'Cancelada'),
(7, 'Suspensa e Bloqueada');

INSERT INTO Fase (id, nome) VALUES
(1, 'Conciliatória'),
(2, 'Instrutória'),
(3, 'Encerramento'),
(4, 'Recursal'),
(5, 'Execução - Andamento'),
(6, 'Execução - Suspensão'),
(7, 'Execução - Diligência'),
(8, 'Execução - Encerramento'),
(9, 'Execução - Retorno à Origem'),
(10, 'Vinculações');

INSERT INTO Feito (id, nome) VALUES
(1, 'Cobrança de Dívidas'),
(2, 'Colisão de Veículos'),
(3, 'Indenização por Atos Ilícitos'),
(4, 'Relações de Consumo'),
(5, 'Relação de Cartão de Crédito'),
(6, 'Relação de Seguro de'),
(7, 'Saúde, Relação de Serviços de Educação'),
(8, 'Reivindicação de Bens Móveis e Semoventes'),
(9, 'Indenização por Danos Morais'),
(10, 'Cobrança de Taxas Condominiais'),
(11, 'Relação de Vizinhança'),
(12, 'Ação de Despejo para Uso Próprio'),
(13, 'Ação Possessória'),
(14, 'Cumprimento de Sentença'),
(15, 'Execução de Título Extrajudicial');

INSERT INTO Endereco (cep, cidade, pais, estado, bairro, rua) VALUES
(50920135, 'Recife', 'Brasil', 'PE', 'Jardim São Paulo', 'Av. Liberdade'),
(50120240, 'Recife', 'Brasil', 'PE', 'Ibura', 'Rua Aberta'),
(50000000, 'Recife', 'Brasil', 'PE', 'Recife Antigo', 'Rua um'),
(50400110, 'Recife', 'Brasil', 'PE', 'Boa Viagem', 'Av. da Praia'),
(51240420, 'Recife', 'Brasil', 'PE', 'Parnamirim', 'Av. Leda');

INSERT INTO Pauta (id,dataAgendamento,estadoPauta) VALUES 
(1, '2019-06-10', 1),
(2, '2019-06-11', 1),
(3, '2019-06-12', 1),
(4, '2019-06-13', 1),
(5, '2019-06-14', 1),
(6, '2019-07-11', 1),
(7, '2019-08-10', 1);

INSERT INTO Magistrado (cpf,endereco,matricula,nome,numeroEndereco,senha) VALUES 
('71874846073', 50120240, 1893647, 'Paulo Andre Ferreira', '45','123456'),
('09457374848', 50000000, 1827383, 'Maria Jose Barros', '23c','123456'),
('74837585484', 50400110, 1893647, 'Pedro Alcatara', '79','123456'),
('00495348457', 50120240, 1893647, 'Bruno Silva', '345','123456'),
('74745757578', 51240420, 1893647, 'Thiago Mello Arruda', '6789','123456');

INSERT INTO Servidor (cpf,dataFim,dataInicio,endereco,juizado,matricula,nome,numeroEndereco,senha,tipoServidor) VALUES 
('98764736373', '2021-06-11', '2019-06-10', 51240420, NULL, 1843556,'Florentina de Jesus','4567','12345678','A'),
('00394575873', '2021-07-10', '2019-06-10', 50400110, NULL, 1867890,'Paula Fernandes','45','12345678','T'),
('02198374484', '2021-01-12', '2019-06-10', 50000000, NULL, 1835645,'Moises Dias','345','12345678','T'),
('03576768574', '2025-10-10', '2019-06-10', 50000000, NULL, 2030393,'Lucas Paes','1','18936475','C'),
('84637673636', '2021-06-10', '2019-06-10', 50920135, NULL, 1976667,'Abraao Lima','72','12345678','A'),
('63764644456', '2022-06-09', '2019-06-10', 51240420, NULL, 1345667,'Judas Tadeu','24c','123456','T'),
('46473646353', '2023-05-02', '2019-06-10', 50400110, NULL, 1678221,'Joao Batista','1783','123456789','T'),
('74857574774', '2023-09-01', '2019-06-10', 50920135, NULL, 1283746,'Marcos Silva','1','18936475','C');

INSERT INTO  CursoEspecializacao (id,servidor,dataConclusao,nivel,nome) VALUE 
(1, '84637673636', '2017-06-09', 'superior', 'Contabilidade'), 
(2, '84637673636', '2010-06-09', 'superior', 'Administracao'), 
(3, '98764736373', '2003-11-01', 'superior', 'Contabilidade'); 

INSERT INTO Juizado (id,dataFim,dataInicio,endereco,magistrado,nome,numeroEndereco,servidorChefe,telefone) VALUES
(1, '2020-06-10','2019-06-10',50000000,'71874846073','Juizados Civeis 1G','45C','98764736373','081 99991-8752'),
(2, '2020-06-10','2019-06-10',50000000,'09457374848','Juizados Civeis 2G','45C','84637673636','081 99991-6475');

UPDATE Servidor SET juizado=1 WHERE cpf= '98764736373';
UPDATE Servidor SET juizado=1 WHERE cpf= '00394575873';
UPDATE Servidor SET juizado=1 WHERE cpf= '02198374484';
UPDATE Servidor SET juizado=1 WHERE cpf= '03576768574';
UPDATE Servidor SET juizado=2 WHERE cpf= '84637673636';
UPDATE Servidor SET juizado=2 WHERE cpf= '63764644456';
UPDATE Servidor SET juizado=2 WHERE cpf= '46473646353';

INSERT INTO Parte (id, email,nemeroEndereco,polo,telefone) VALUES          
(1, 'pauloandre@gmail.com', '151C', 'A', '5555-85856'), 
(2, 'administracao@casarua.com', '151C', 'P', '5555-85856'), 
(3, 'parte3@gmail.com', '45B',  'P', '7777-77777'),
(4, 'adm@devcard.com', '151C', 'P', '5555-85856'), 
(5, 'parte2@gmail.com', '452',  'A', '9999-99999'), 
(6, 'adm@Masnet.com', '45B',  'P', '7777-77777'),
(7, 'adm@devcard.com', '151C', 'P', '5555-85856'), 
(8, 'parte2@gmail.com', '452',  'A', '9999-99999'), 
(9, 'adm@Teci9.com', '45B',  'P', '7777-77777');

INSERT INTO ParteFisica (parte,dataNascimento,documentoIdentificacao,estadoCivil,nome,nomeMae,nomePai,tipoDocumentoIdentificacao) VALUES 
(1,'1970-06-09', '5552145','S','mario gabriel','Maria aparecida','jose severino', 'rg'), 
(3,'1999-06-01', '5552321','C','joão lucas','Maria madalena','lucas silva', 'rg'), 
(5,'1968-05-09', '1562145','V','Ana maria','ana cristina','felipe silva', 'rg'), 
(7,'2000-01-09', '9091214','S','Larissa dantas','Maria aparecida','cosmo jose', 'rg');

INSERT INTO ParteJuridica (parte,cnpj,razaoSocial) VALUES 
(2, '77649276000120', 'Casa da rua LTDA'),
(4, '96587546000546', 'DevCard LTDA'),
(6, '54568954000145', 'Masnet'),
(9, '21031458796214', 'Teci9 LTDA');

INSERT INTO Processo (npu, dataAjuizamento, juizado, observacao) VALUES
('00106948920198170001', '2018-06-09', 1, 'OBS1'),
('00123948920198170001', '2017-06-09', 1, 'OBS2'),
('00145656920198170001', '2017-12-10', 1, 'OBS3'),
('00012254520198170001', '2015-06-09', 1, 'OBS4'),
('00118845920198170001', '2017-06-12', 1, 'OBS5'),
('00226925620198170001', '2018-12-09', 1, 'OBS6'),
('00005687520198170001', '2018-06-11', 1, 'OBS7'),
('00536456520198170002', '2018-09-12', 2, 'OBS11'),
('00005548620198170002', '2018-05-01', 2, 'OBS22'),
('00548648220198170002', '2018-04-02', 2, 'OBS33'),
('00051564620198170002', '2018-03-03', 2, 'OBS44'),
('00004646820198170002', '2018-02-04', 2, 'OBS55'),
('00445554520198170002', '2018-01-05', 2, 'OBS67');

INSERT INTO Execucao (id,processo,dataRegistro,numeroParcelas,valorTotalDivida) VALUES 
(1,'00051564620198170002', '2021-06-10', 3, 1500.00),
(2,'00106948920198170001', '2021-06-10', 2, 12000.00);

INSERT INTO Recebimento (id,execucao,dataVencimento,numeroParcela,observacao,valor) VALUES 
(1,1, '2019-07-10', 1, 'Obs 1', 501.20),
(2,1, '2019-08-10', 2, 'Obs 2', 502.50),
(3,1, '2019-09-10', 3, 'Obs 3', 505.00),
(4,2, '2019-07-10', 1, 'Obs 1', 4000.00),
(5,2, '2019-08-10', 2, 'Obs 2', 4200.00),
(6,2, '2019-09-10', 3, 'Obs 3', 4350.00),
(7,2, '2019-10-10', 4, 'Obs 4', 4500.00);

INSERT INTO Representante (cpf,nome,email,matricula,numeroEndereco,oab,polo,senha,telefone,tipoRepresentante) VALUES 
('15411554486','Paulo Andrade Lima', 'andradelima@uol.com', NULL, '150', '13256-PE','A','123456','81 96589-9856','D'),
('45678565664','Bergue Santos', 'bsantos@uol.com', NULL, '2C', '12568-PE','A','123456','81 96589-9856','D'),
('54606515613','Carlos Andre', 'candre@terra.com', NULL, '012', '545451-PE','P','123456','81 65489-6546','D'),
('56789845656','Thiago Lima', 'tlima@yahoo.com', NULL, '456', '45600-PE','P','123456','81 96589-9856','D'),
('12354563334','Paulo Marcelo Ferreira', 'pmf10@gmail.com', NULL, '565', '13256-PE','A','123456','81 96589-9856','A'),
('00546116566','Mozart Silva', 'msilva@uol.com', NULL, '456', '65411-PE','A','123456','81 96589-6599','A'),
('01234561231','Leornado Santana', 'lsantana@uol.com', NULL, '111', '22356-PE','P','123456','81 45666-2458','A'),
('02132546548','Cristina Elizabeth Correia', 'ccorreia@terra.com', NULL, '895', '45646-PE','P','123456','81 96582-3256','A');

INSERT INTO ProcessoRepresentante (processo,representante) VALUES 
('00051564620198170002', '00546116566'),
('00051564620198170002', '54606515613'),
('00536456520198170002', '15411554486'),
('00106948920198170001', '15411554486'),
('00123948920198170001', '45678565664'),
('00145656920198170001', '54606515613'),
('00012254520198170001', '56789845656'),
('00005548620198170002', '12354563334'),
('00004646820198170002', '01234561231'),
('00445554520198170002', '02132546548');

INSERT INTO Audiencia (id,pauta,processo,magistrado,servidor,estadoAudiencia,situacao,tipo,hora,sala) VALUES 
(1, 1, '00051564620198170002', NULL,'03576768574',1,'C','C','14:00:00', 'sala 2'),
(2, 2, '00051564620198170002', '71874846073',NULL,1,'C','C','14:00:00', 'sala 2'),
(3, 3, '00123948920198170001', NULL,'03576768574',1,'C','C','14:00:00', 'sala 2'),
(4, 4, '00123948920198170001', '71874846073',NULL,1,'C','C','14:00:00', 'sala 2'),
(5, 5, '00536456520198170002', '09457374848', NULL,1,'I','I','14:30:00', 'sala 2'),
(6, 1, '00548648220198170002', '09457374848', NULL,1,'I','I','14:30:00', 'sala 2'),
(7, 2, '00445554520198170002', '09457374848', NULL,1,'I','I','14:30:00', 'sala 2');

INSERT INTO Testemunha(id,nome, email, numeroEndereco, polo, telefone, dataNascimento ) VALUES 
(1,'Ronald Motta','rmotta@gtec.com.br','45C','A','81 99989-0987','1988-10-10'),
(2,'Carla Silva','ssilva@ertd.com.br','35','P','81 82738-7463','1995-01-01'),
(3,'Maria Sikva','msilva@ertd.com.br','355','P','81 84567-8976','1996-07-01'),
(4,'Pedro Souza','psouza@uol.com.br','1','A','81 84567-8976','1996-07-01'),
(5,'Mario Lopes','mlopes@ter.com.br','4','A','81 99984-8444','1970-07-01');

INSERT INTO Comunicacao (id, parte, testemunha, servidor, texto, dataHoraEnvio, tipoComunicacao, numeroAR, dataHoraRecebimento) VALUES 
(1, 1, NULL,'98764736373','TEXTO','2000-09-01 14:00:00','I','1231245637','2000-09-01 14:00:00'), 
(2, 2, NULL,'00394575873','TEXTO','2000-07-03 16:00:00','C','5436781234','2010-10-01 15:00:00'),
(3, NULL, 3,'02198374484','TEXTO','2000-07-03 16:40:00','I','9856734510','2016-07-10 16:30:00'),
(4, NULL, 4,'03576768574','TEXTO','2000-07-03 17:30:00','C','0987346271','2002-09-01 10:00:00');

INSERT INTO Movimentacao (id, processo, servidor, tipoMovimentacao, dataHota) VALUES 
(1, '00051564620198170002','00394575873', 1,'2019-01-03 14:00'), 
(2, '00051564620198170002','00394575873', 2,'2019-02-03 15:00'), 
(3, '00051564620198170002','02198374484', 3,'2019-03-03 16:00'),
(4, '00051564620198170002','00394575873', 4,'2019-03-03 17:00'),
(5, '00123948920198170001','63764644456', 1,'2000-08-03 14:00'), 
(6, '00005548620198170002','63764644456', 2,'1989-10-07 15:00'), 
(7, '00548648220198170002','46473646353', 3,'1999-10-08 16:00'), 
(8, '00445554520198170002','46473646353', 4,'2003-11-09 20:00'); 

INSERT INTO Documento (id,magistrado,servidor,representante,processo,documentoprincipal,tipodocumento,datacriacao,texto) VALUES 
(1,'71874846073',NULL ,NULL,'00051564620198170002',NULL,2,'2019-06-11','Petição N091 Venho atarvers xxxxx'), 
(2,'09457374848','00394575873', NULL,'00051564620198170002',NULL,2,'2019-06-11','Petição N 092 Venho atarvers xxxxx'),
(3,'09457374848','02198374484', NULL,'00051564620198170002', NULL,3,'2019-05-08','texto sdkh kjhkjh ajkhdjash jdkashdjk ahkjd'),
(4,'74837585484','03576768574', NULL,'00145656920198170001', NULL,4,'2019-03-04','texto jkggakkskA S GaksgkAGSKJaskjA KJSGajksgKAJSGKJagskjAGKJSagksjAGKJSakjsk');

INSERT INTO ProcessoFase (id,fase,processo,dataregistro) VALUES 
(1, 1, '00051564620198170002','2000-09-01'), 
(2, 2, '00051564620198170002','2000-07-03'),
(3, 3, '00123948920198170001','2000-10-08'),
(4, 4, '00123948920198170001','2000-03-04');

INSERT INTO ProcessoTestemunha (processo,testemunha) VALUES 
('00051564620198170002', 1), 
('00051564620198170002', 2), 
('00123948920198170001', 3), 
('00445554520198170002', 4); 


INSERT INTO ProcessoFeito (processo, feito) VALUES 
('00051564620198170002', 1), 
('00051564620198170002', 2), 
('00123948920198170001', 3), 
('00445554520198170002', 4);


INSERT INTO ProcessoParte (processo,parte) VALUES 
('00051564620198170002', 1), 
('00051564620198170002', 2), 
('00123948920198170001', 3), 
('00445554520198170002', 4); 

COMMIT;
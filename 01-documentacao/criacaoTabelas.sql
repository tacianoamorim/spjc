
DROP SCHEMA DBSPJC;
CREATE SCHEMA DBSPJC CHARSET = Latin1 COLLATE = latin1_swedish_ci;
USE DBSPJC;

CREATE TABLE Juizado (
  dataFim                        INTEGER UNSIGNED NULL
 ,dataInicio                     DATE NULL
 ,endereco                       INTEGER UNSIGNED NOT NULL
 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,magistrado                     VARCHAR(11) NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,numeroEndereco                 VARCHAR(10) NOT NULL
 ,servidorChefe                  VARCHAR(11) NOT NULL
 ,telefone                       VARCHAR(100) NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_Juizado_Endereco ON Juizado (endereco);
CREATE INDEX FK_Juizado_Servidor ON Juizado (servidorChefe);
CREATE INDEX FK_Juizado_Magistrado ON Juizado (magistrado);

CREATE TABLE Pauta (
  dataAgendamento                DATETIME NOT NULL
 ,estadoPauta                    INTEGER UNSIGNED NOT NULL
 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_EstadoPauta ON Pauta (estadoPauta);

CREATE TABLE Feito (
  descricao                      INTEGER UNSIGNED NOT NULL
 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE Fase (
  descricao                      VARCHAR(100) NOT NULL
 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE Testemunha (
  email                          VARCHAR(100) NOT NULL
 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,numeroEndereco                 VARCHAR(10) NULL
 ,polo                           CHAR(1) NOT NULL
 ,telefone                       VARCHAR(100) NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE Execucao (
  dataCumprimento                DATETIME NULL
 ,dataRegistro                   DATETIME NOT NULL
 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,numeroParcelas                 INTEGER UNSIGNED NOT NULL
 ,processo                       VARCHAR(20) NOT NULL
 ,valorTotalDivida               DECIMAL NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_Execucao_Processo ON Execucao (processo);

CREATE TABLE Processo (
  dataAjuizamento                INTEGER UNSIGNED NOT NULL
 ,dataBaixa                      DATETIME NULL
 ,juizado                        INTEGER UNSIGNED NOT NULL
 ,npu                            VARCHAR(20) NOT NULL
 ,observacao                     VARCHAR(512) NULL
 ,tipoBaica                      CHAR(1) NULL
 ,PRIMARY KEY (npu)
) ENGINE=InnoDB;
CREATE INDEX FK_Processo_Juizado ON Processo (juizado);

CREATE TABLE Magistrado (
  cpf                            VARCHAR(11) NOT NULL
 ,endereco                       INTEGER UNSIGNED NOT NULL
 ,matricula                      INTEGER UNSIGNED NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,numeroEndereco                 VARCHAR(10) NULL
 ,senha                          VARCHAR(512) NOT NULL
 ,PRIMARY KEY (cpf)
) ENGINE=InnoDB;
CREATE INDEX FK_Magistrado_Endereco ON Magistrado (endereco);

CREATE TABLE Audiencia (
  estadoAudiencia                INTEGER UNSIGNED NOT NULL
 ,hora                           TIME NOT NULL
 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,magistrado                     VARCHAR(11) NULL
 ,pauta                          INTEGER UNSIGNED NOT NULL
 ,processo                       VARCHAR(20) NOT NULL
 ,sala                           VARCHAR(20) NOT NULL
 ,servidor                       VARCHAR(11) NULL
 ,situacao                       CHAR(1) NOT NULL
 ,tipo                           CHAR(1) NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_Audiencia_Processo ON Audiencia (processo);
CREATE INDEX FK_Audiencia_EstadoAudiencia ON Audiencia (estadoAudiencia);
CREATE INDEX FK_Audiencia_Pauta ON Audiencia (pauta);
CREATE INDEX FK_Audiencia_magistrado ON Audiencia (magistrado);
CREATE INDEX FK_Audiencia_Servidor ON Audiencia (servidor);

CREATE TABLE Endereco (
  cep                            INTEGER UNSIGNED NOT NULL
 ,bairro                         VARCHAR(100) NOT NULL
 ,cidade                         VARCHAR(100) NOT NULL
 ,estado                         CHAR(2) NOT NULL
 ,pais                           VARCHAR(100) NULL
 ,rua                            VARCHAR(100) NOT NULL
 ,PRIMARY KEY (cep)
) ENGINE=InnoDB;

CREATE TABLE EstadoPauta (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE ProcessoFeito (
  feito                          INTEGER UNSIGNED NOT NULL
 ,processo                       VARCHAR(20) NOT NULL
 ,PRIMARY KEY (feito, processo)
) ENGINE=InnoDB;
CREATE INDEX FK_FeitoProcesso_Feito ON ProcessoFeito (feito);
CREATE INDEX FP_FeitoProcesso_Processo ON ProcessoFeito (processo);

CREATE TABLE Recebimento (
  dataPagamento                  DATE NULL
 ,dataVencimento                 DATE NOT NULL
 ,execucao                       INTEGER UNSIGNED NOT NULL
 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,numeroParcela                  INTEGER UNSIGNED NOT NULL
 ,observacao                     VARCHAR(512) NULL
 ,valor                          DECIMAL NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_Recebimento_Execucao ON Recebimento (execucao);

CREATE TABLE EstadoAudiencia (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE TipoMovimentacao (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE TipoDocumento (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE Servidor (

  cpf                            VARCHAR(11) NOT NULL

 ,dataFim                        DATE NULL

 ,dataInicio                     DATE NULL

 ,endereco                       INTEGER UNSIGNED NOT NULL

 ,juizado                        INTEGER UNSIGNED NOT NULL

 ,matricula                      INTEGER UNSIGNED NOT NULL

 ,nome                           VARCHAR(100) NOT NULL

 ,numeroEndereco                 VARCHAR(10) NULL

 ,senha                          VARCHAR(512) NOT NULL

 ,tipoServidor                   CHAR(1) NOT NULL

 ,PRIMARY KEY (cpf)

) ENGINE=InnoDB;



CREATE INDEX FK_Servidor_Endereco ON Servidor (endereco);



CREATE INDEX FK_Servidor_Juizado ON Servidor (juizado);



CREATE TABLE CursoEspecializacao (

  dataConclusao                  DATE NULL

 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL

 ,nivel                          VARCHAR(100) NULL

 ,nome                           VARCHAR(100) NOT NULL

 ,servidor                       VARCHAR(11) NOT NULL

 ,PRIMARY KEY (id)

) ENGINE=InnoDB;



CREATE INDEX FK_CursoEspecializacao_Servidor ON CursoEspecializacao (servidor);



CREATE TABLE ProcessoTestemunha (

  processo                       VARCHAR(20) NOT NULL

 ,testemunha                     INTEGER UNSIGNED NOT NULL

 ,PRIMARY KEY (processo, testemunha)

) ENGINE=InnoDB;



CREATE INDEX FK_ProcessoTestemunha_Pocesso ON ProcessoTestemunha (processo);



CREATE INDEX FK_ProcessoTestemunha_Testemunha ON ProcessoTestemunha (testemunha);



CREATE TABLE Movimentacao (

  dataHota                       TIMESTAMP NOT NULL

 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL

 ,processo                       VARCHAR(20) NOT NULL

 ,servidor                       VARCHAR(11) NOT NULL

 ,tipoMovimentacao               INTEGER UNSIGNED NOT NULL

 ,PRIMARY KEY (id)

) ENGINE=InnoDB;



CREATE INDEX FK_Movimentacao_TipoMovimentacao ON Movimentacao (tipoMovimentacao);



CREATE INDEX FK_Movimentacao_Servidor ON Movimentacao (servidor);



CREATE INDEX FK_Movimentacao_Processo ON Movimentacao (processo);



CREATE TABLE ProcessoFase (

  dataRegistro                   INTEGER UNSIGNED NOT NULL

 ,fase                           INTEGER UNSIGNED NOT NULL

 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL

 ,processo                       VARCHAR(20) NOT NULL

 ,PRIMARY KEY (id)

) ENGINE=InnoDB;



CREATE INDEX FK_ProcessoFase_Processo ON ProcessoFase (processo);



CREATE INDEX FK_ProcessoFase_Fase ON ProcessoFase (fase);



CREATE TABLE Documento (

  arquivo                        BLOB NULL

 ,assinatura                     VARCHAR(512) NULL

 ,dataAssinatura                 TIMESTAMP NULL

 ,dataCriacao                    TIMESTAMP NULL

 ,documentoPrincipal             INTEGER UNSIGNED NULL

 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL

 ,magistrado                     VARCHAR(11) NULL

 ,processo                       VARCHAR(20) NOT NULL

 ,representante                  VARCHAR(11) NULL

 ,servidor                       VARCHAR(11) NULL

 ,texto                          VARCHAR(5000) NULL

 ,tipoDocumento                  INTEGER UNSIGNED NOT NULL

 ,PRIMARY KEY (id)

) ENGINE=InnoDB;



CREATE INDEX FK_Documento_TipoDocumento ON Documento (tipoDocumento);



CREATE INDEX FH_Documento_Documento ON Documento (documentoPrincipal);



CREATE INDEX FK_Documento_Processo ON Documento (processo);



CREATE INDEX FK_Documento_Servidor ON Documento (servidor);



CREATE INDEX FK_Documento_Magistrado ON Documento (magistrado);



CREATE INDEX FK_Documento_Representante ON Documento (representante);



CREATE TABLE Representante (

  cpf                            VARCHAR(11) NOT NULL

 ,email                          VARCHAR(100) NULL

 ,matricula                      INTEGER UNSIGNED NULL

 ,nome                           VARCHAR(100) NULL

 ,numeroEndereco                 VARCHAR(10) NULL

 ,oab                            VARCHAR(20) NULL

 ,polo                           CHAR(1) NULL

 ,senha                          VARCHAR(512) NULL

 ,telefone                       VARCHAR(50) NULL

 ,tipoRepresentante              CHAR(1) NULL

 ,PRIMARY KEY (cpf)

) ENGINE=InnoDB;



CREATE TABLE ProcessoRepresentante (

  processo                       VARCHAR(20) NOT NULL

 ,representante                  VARCHAR(11) NOT NULL

 ,PRIMARY KEY (processo, representante)

) ENGINE=InnoDB;



CREATE INDEX FK_ProcessoRepresentante_Processo ON ProcessoRepresentante (processo);



CREATE INDEX FK_ProcessoRepresentante_Representante ON ProcessoRepresentante (representante);



CREATE TABLE Comunicacao (

  dataHoraEnvio                  TIMESTAMP NOT NULL

 ,dataHoraRecebimento            TIMESTAMP NULL

 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL

 ,numeroAR                       VARCHAR(10) NULL

 ,parte                          INTEGER UNSIGNED NOT NULL

 ,servidor                       VARCHAR(11) NOT NULL

 ,testemunha                     INTEGER UNSIGNED NULL

 ,texto                          VARCHAR(512) NOT NULL

 ,tipoComunicacao                CHAR(1) NOT NULL

 ,PRIMARY KEY (id)

) ENGINE=InnoDB;



CREATE INDEX FK_Comunicacao_servidor ON Comunicacao (servidor);



CREATE INDEX FK_Comunicacao_Testemunha ON Comunicacao (testemunha);



CREATE INDEX FK_Comunicacao_Parte ON Comunicacao (parte);



CREATE TABLE Parte (

  email                          VARCHAR(100) NULL

 ,id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL

 ,nemeroEndereco                 VARCHAR(10) NULL

 ,polo                           CHAR(1) NULL

 ,telefone                       VARCHAR(20) NULL

 ,PRIMARY KEY (id)

) ENGINE=InnoDB;



CREATE TABLE ProcessoParte (

  parte                          INTEGER UNSIGNED NOT NULL

 ,processo                       VARCHAR(20) NOT NULL

 ,PRIMARY KEY (processo, parte)

) ENGINE=InnoDB;



CREATE INDEX FK_ProcessoParte_processo ON ProcessoParte (processo);



CREATE INDEX FK_ProcessoParte_Parte ON ProcessoParte (parte);



CREATE TABLE ParteFisica (

  dataNascimento                 DATE NULL

 ,documentoIdentificacao         VARCHAR(50) NULL

 ,estadoCivil                    CHAR(1) NULL

 ,nome                           VARCHAR(100) NULL

 ,nomeMae                        VARCHAR(100) NULL

 ,nomePai                        VARCHAR(100) NULL

 ,parte                          INTEGER UNSIGNED NOT NULL

 ,tipoDocumentoIdentificacao     VARCHAR(10) NULL

 ,PRIMARY KEY (parte)

) ENGINE=InnoDB;



CREATE INDEX FK_ParteFisica_Parte ON ParteFisica (parte);



CREATE TABLE ParteJuridica (

  cnpj                           VARCHAR(20) NULL

 ,parte                          INTEGER UNSIGNED NOT NULL

 ,razaoSocial                    VARCHAR(100) NULL

 ,PRIMARY KEY (parte)

) ENGINE=InnoDB;



CREATE INDEX FK_ParteFisica_Parte ON ParteJuridica (parte);



ALTER TABLE Juizado

  ADD CONSTRAINT Rel_Endereco_Juizado FOREIGN KEY (endereco)

    REFERENCES Endereco (cep)

;

ALTER TABLE Juizado

  ADD CONSTRAINT Rel_Servidor_Juizado FOREIGN KEY (servidorChefe)

    REFERENCES Servidor (cpf)

;

ALTER TABLE Juizado

  ADD CONSTRAINT Rel_Magistrado_Juizado FOREIGN KEY (magistrado)

    REFERENCES Magistrado (cpf)

;

ALTER TABLE Pauta

  ADD CONSTRAINT Rel_Pauta_EstadoPauta FOREIGN KEY (estadoPauta)

    REFERENCES EstadoPauta (id)

;

ALTER TABLE Execucao

  ADD CONSTRAINT Rel_Processo_Execucao FOREIGN KEY (processo)

    REFERENCES Processo (npu)

;

ALTER TABLE Processo

  ADD CONSTRAINT Rel_Juizado_Processo FOREIGN KEY (juizado)

    REFERENCES Juizado (id)

;

ALTER TABLE Magistrado

  ADD CONSTRAINT Rel_Endereco_Magistrado FOREIGN KEY (endereco)

    REFERENCES Endereco (cep)

;

ALTER TABLE Audiencia

  ADD CONSTRAINT Rel_Processo_Audiencia FOREIGN KEY (processo)

    REFERENCES Processo (npu)

;

ALTER TABLE Audiencia

  ADD CONSTRAINT Rel_EstadoAudiencia_Audiencia FOREIGN KEY (estadoAudiencia)

    REFERENCES EstadoAudiencia (id)

;

ALTER TABLE Audiencia

  ADD CONSTRAINT Rel_Pauta_Audiencia FOREIGN KEY (pauta)

    REFERENCES Pauta (id)

;

ALTER TABLE Audiencia

  ADD CONSTRAINT Rel_Magistrado_Audiencia FOREIGN KEY (magistrado)

    REFERENCES Magistrado (cpf)

;

ALTER TABLE Audiencia

  ADD CONSTRAINT Rel_Servidor_Audiencia FOREIGN KEY (servidor)

    REFERENCES Servidor (cpf)

;

ALTER TABLE ProcessoFeito

  ADD CONSTRAINT Rel_Feito_ProcessoFeito FOREIGN KEY (feito)

    REFERENCES Feito (id)

;

ALTER TABLE ProcessoFeito

  ADD CONSTRAINT Rel_Processo_ProcessoFase FOREIGN KEY (processo)

    REFERENCES Processo (npu)

;

ALTER TABLE Recebimento

  ADD CONSTRAINT Rel_Execucao_Recebimento FOREIGN KEY (execucao)

    REFERENCES Execucao (id)

;

ALTER TABLE Servidor

  ADD CONSTRAINT Rel_Endereco_Servidor FOREIGN KEY (endereco)

    REFERENCES Endereco (cep)

;

ALTER TABLE Servidor

  ADD CONSTRAINT Rel_Juizado_Servidor FOREIGN KEY (juizado)

    REFERENCES Juizado (id)

;

ALTER TABLE CursoEspecializacao

  ADD CONSTRAINT Rel_Servido_CursoEspecializacao FOREIGN KEY (servidor)

    REFERENCES Servidor (cpf)

;

ALTER TABLE ProcessoTestemunha

  ADD CONSTRAINT Rel_Processo_ProcessoTestemunha FOREIGN KEY (processo)

    REFERENCES Processo (npu)

;

ALTER TABLE ProcessoTestemunha

  ADD CONSTRAINT Rel_Testemunha_ProcessoTestemunha FOREIGN KEY (testemunha)

    REFERENCES Testemunha (id)

;

ALTER TABLE Movimentacao

  ADD CONSTRAINT Rel_TipoMovimentacao_TipoMovimentacao FOREIGN KEY (tipoMovimentacao)

    REFERENCES TipoMovimentacao (id)

;

ALTER TABLE Movimentacao

  ADD CONSTRAINT Rel_Servidor_Movimentacao FOREIGN KEY (servidor)

    REFERENCES Servidor (cpf)

;

ALTER TABLE Movimentacao

  ADD CONSTRAINT Rel_Processo_Movimentacao FOREIGN KEY (processo)

    REFERENCES Processo (npu)

;

ALTER TABLE ProcessoFase

  ADD CONSTRAINT Rel_Fase_ProcessoFase FOREIGN KEY (fase)

    REFERENCES Fase (id)

;

ALTER TABLE Documento

  ADD CONSTRAINT Rel_TipoDocumento_Documento FOREIGN KEY (tipoDocumento)

    REFERENCES TipoDocumento (id)

;

ALTER TABLE Documento

  ADD CONSTRAINT Rel_Documento_Documento FOREIGN KEY (documentoPrincipal)

    REFERENCES Documento (id)

;

ALTER TABLE Documento

  ADD CONSTRAINT Rel_Processo_Documento FOREIGN KEY (processo)

    REFERENCES Processo (npu)

;

ALTER TABLE Documento

  ADD CONSTRAINT Rel_Servidor_Documento FOREIGN KEY (servidor)

    REFERENCES Servidor (cpf)

;

ALTER TABLE Documento

  ADD CONSTRAINT Rel_Magistrado_Documento FOREIGN KEY (magistrado)

    REFERENCES Magistrado (cpf)

;

ALTER TABLE Documento

  ADD CONSTRAINT Rel_Representante_Documento FOREIGN KEY (representante)

    REFERENCES Representante (cpf)

;

ALTER TABLE ProcessoRepresentante

  ADD CONSTRAINT Rel_Processo_ProcessoRepresentante FOREIGN KEY (processo)

    REFERENCES Processo (npu)

;

ALTER TABLE ProcessoRepresentante

  ADD CONSTRAINT Rel_Representante_ProcessoRepresentante FOREIGN KEY (representante)

    REFERENCES Representante (cpf)

;

ALTER TABLE Comunicacao

  ADD CONSTRAINT Rel_Servidor_Comunicacao FOREIGN KEY (servidor)

    REFERENCES Servidor (cpf)

;

ALTER TABLE Comunicacao

  ADD CONSTRAINT Rel_Testemunha_Comunicacao FOREIGN KEY (testemunha)

    REFERENCES Testemunha (id)

;

ALTER TABLE Comunicacao

  ADD CONSTRAINT Rel_Parte_Comunicacao FOREIGN KEY (parte)

    REFERENCES Parte (id)

;

ALTER TABLE ParteFisica

  ADD CONSTRAINT Rel_Parte_ParteFisica FOREIGN KEY (parte)

    REFERENCES Parte (id)

;

ALTER TABLE ParteJuridica

  ADD CONSTRAINT Rel_Parte_ParteJuridica FOREIGN KEY (parte)

    REFERENCES Parte (id)

;

ALTER TABLE ProcessoParte

  ADD CONSTRAINT Rel_Processo_ProcessoParte FOREIGN KEY (processo)

    REFERENCES Processo (npu)

;

ALTER TABLE ProcessoParte

  ADD CONSTRAINT Rel_Parte_ProcessoParte FOREIGN KEY (parte)

    REFERENCES Parte (id)

;


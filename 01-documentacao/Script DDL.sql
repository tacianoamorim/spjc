# APAGA O SCHEMA
DROP SCHEMA IF EXISTS DBSPJC;

# CRIA O SCHEMA
CREATE SCHEMA DBSPJC CHARSET = Latin1 COLLATE = latin1_swedish_ci;

# INFORMA QUE AS PROXIMA OPERACOES SERA NO SCHEMA
USE DBSPJC;

CREATE TABLE Juizado (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,magistrado                     VARCHAR(11) NOT NULL
 ,servidorChefe                  VARCHAR(11) NOT NULL
 ,endereco                       INTEGER UNSIGNED NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,telefone                       VARCHAR(100) NOT NULL
 ,numeroEndereco                 VARCHAR(10) NOT NULL
 ,dataInicio                     DATE NULL
 ,dataFim                        DATE NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_Juizado_Endereco ON Juizado (endereco);

CREATE INDEX FK_Juizado_Servidor ON Juizado (servidorChefe);

CREATE INDEX FK_Juizado_Magistrado ON Juizado (magistrado);

CREATE TABLE Pauta (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,estadoPauta                    INTEGER UNSIGNED NOT NULL
 ,dataAgendamento                DATETIME NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_EstadoPauta ON Pauta (estadoPauta);

CREATE TABLE Feito (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,nome                           INTEGER UNSIGNED NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE TABLE Fase (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,descricao                      VARCHAR(100) NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE TABLE Testemunha (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,polo                           CHAR(1) NOT NULL
 ,email                          VARCHAR(100) NOT NULL
 ,telefone                       VARCHAR(100) NOT NULL
 ,numeroEndereco                 VARCHAR(10) NULL
 ,dataNascimento                 DATE NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE TABLE Execucao (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,processo                       VARCHAR(20) NOT NULL
 ,numeroParcelas                 INTEGER UNSIGNED NOT NULL
 ,dataRegistro                   DATETIME NOT NULL
 ,valorTotalDivida               DECIMAL(10,2) NOT NULL
 ,dataCumprimento                DATETIME NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_Execucao_Processo ON Execucao (processo);

CREATE TABLE Processo (
  npu                            VARCHAR(20) NOT NULL
 ,juizado                        INTEGER UNSIGNED NOT NULL
 ,observacao                     VARCHAR(512) NULL
 ,dataAjuizamento                DATE NOT NULL
 ,dataBaixa                      DATE NULL
 ,tipoBaixa                      CHAR(1) NULL
 ,PRIMARY KEY (npu)
) ENGINE=InnoDB;
CREATE INDEX FK_Processo_Juizado ON Processo (juizado);

CREATE TABLE Magistrado (
  cpf                            VARCHAR(11) NOT NULL
 ,endereco                       INTEGER UNSIGNED NOT NULL
 ,matricula                      INTEGER UNSIGNED NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,senha                          VARCHAR(512) NOT NULL
 ,numeroEndereco                 VARCHAR(10) NULL
 ,PRIMARY KEY (cpf)
) ENGINE=InnoDB;
CREATE INDEX FK_Magistrado_Endereco ON Magistrado (endereco);

CREATE TABLE Audiencia (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,servidor                       VARCHAR(11) NULL
 ,magistrado                     VARCHAR(11) NULL
 ,pauta                          INTEGER UNSIGNED NOT NULL
 ,estadoAudiencia                INTEGER UNSIGNED NOT NULL
 ,processo                       VARCHAR(20) NOT NULL
 ,tipo                           CHAR(1) NOT NULL
 ,sala                           VARCHAR(20) NOT NULL
 ,situacao                       CHAR(1) NOT NULL
 ,hora                           TIME NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_Audiencia_Processo ON Audiencia (processo);

CREATE INDEX FK_Audiencia_EstadoAudiencia ON Audiencia (estadoAudiencia);

CREATE INDEX FK_Audiencia_Pauta ON Audiencia (pauta);

CREATE INDEX FK_Audiencia_magistrado ON Audiencia (magistrado);

CREATE INDEX FK_Audiencia_Servidor ON Audiencia (servidor);

CREATE TABLE Endereco (
  cep                            INTEGER UNSIGNED NOT NULL
 ,rua                            VARCHAR(100) NOT NULL
 ,bairro                         VARCHAR(100) NOT NULL
 ,estado                         CHAR(2) NOT NULL
 ,pais                           VARCHAR(100) NULL
 ,cidade                         VARCHAR(100) NOT NULL
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
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,execucao                       INTEGER UNSIGNED NOT NULL
 ,numeroParcela                  INTEGER UNSIGNED NOT NULL
 ,dataVencimento                 DATE NOT NULL
 ,valor                          DECIMAL(10,2)  NOT NULL
 ,dataPagamento                  DATE NULL
 ,observacao                     VARCHAR(512) NULL
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
 ,juizado                        INTEGER UNSIGNED NULL
 ,endereco                       INTEGER UNSIGNED NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,matricula                      INTEGER UNSIGNED NOT NULL
 ,senha                          VARCHAR(512) NOT NULL
 ,numeroEndereco                 VARCHAR(10) NULL
 ,tipoServidor                   CHAR(1) NOT NULL
 ,dataInicio                     DATE NULL
 ,dataFim                        DATE NULL
 ,PRIMARY KEY (cpf)
) ENGINE=InnoDB;
CREATE INDEX FK_Servidor_Endereco ON Servidor (endereco);

CREATE INDEX FK_Servidor_Juizado ON Servidor (juizado);

CREATE TABLE CursoEspecializacao (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,servidor                       VARCHAR(11) NOT NULL
 ,nome                           VARCHAR(100) NOT NULL
 ,nivel                          VARCHAR(100) NULL
 ,dataConclusao                  DATE NULL
 ,cargaHoraria                   INTEGER UNSIGNED NULL
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
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,processo                       VARCHAR(20) NOT NULL
 ,servidor                       VARCHAR(11) NOT NULL
 ,tipoMovimentacao               INTEGER UNSIGNED NOT NULL
 ,dataHota                       DATETIME NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_Movimentacao_TipoMovimentacao ON Movimentacao (tipoMovimentacao);

CREATE INDEX FK_Movimentacao_Servidor ON Movimentacao (servidor);

CREATE INDEX FK_Movimentacao_Processo ON Movimentacao (processo);

CREATE TABLE ProcessoFase (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,fase                           INTEGER UNSIGNED NOT NULL
 ,processo                       VARCHAR(20) NOT NULL
 ,dataRegistro                   DATETIME NOT NULL
 ,faseAtual                      BOOL NOT NULL DEFAULT false
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_ProcessoFase_Processo ON ProcessoFase (processo);
CREATE INDEX FK_ProcessoFase_Fase ON ProcessoFase (fase);
CREATE UNIQUE INDEX UN_Fase_Processo ON ProcessoFase (fase, processo);

CREATE TABLE Documento (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,magistrado                     VARCHAR(11) NULL
 ,servidor                       VARCHAR(11) NULL
 ,representante                  VARCHAR(11) NULL
 ,processo                       VARCHAR(20) NOT NULL
 ,documentoPrincipal             INTEGER UNSIGNED NULL
 ,tipoDocumento                  INTEGER UNSIGNED NOT NULL
 ,dataCriacao                    DATETIME NULL
 ,texto                          VARCHAR(5000) NULL
 ,arquivo                        BLOB NULL
 ,assinatura                     VARCHAR(512) NULL
 ,dataAssinatura                 DATETIME NULL
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
 ,nome                           VARCHAR(100) NULL
 ,oab                            VARCHAR(20) NULL
 ,email                          VARCHAR(100) NULL
 ,polo                           CHAR(1) NULL
 ,senha                          VARCHAR(512) NULL
 ,telefone                       VARCHAR(50) NULL
 ,numeroEndereco                 VARCHAR(10) NULL
 ,tipoRepresentante              CHAR(1) NULL
 ,matricula                      INTEGER UNSIGNED NULL
 ,PRIMARY KEY (cpf)
) ENGINE=InnoDB;

CREATE TABLE ProcessoRepresentante (
  processo                       VARCHAR(20) NOT NULL
 ,representante                  VARCHAR(11) NOT NULL
 ,PRIMARY KEY (processo, representante)
) ENGINE=InnoDB;
CREATE INDEX FK_ProcessoRepresentante_Processo ON ProcessoRepresentante (processo);

CREATE INDEX FK_ProcessoRepresentante_Representante ON ProcessoRepresentante (representante);

CREATE TABLE CorrespondenciaParte (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,comunicacaoParte               INTEGER UNSIGNED NOT NULL
 ,texto                          VARCHAR(512) NOT NULL
 ,dataHoraEnvio                  DATETIME NOT NULL
 ,tipoComunicacao                CHAR(1) NOT NULL
 ,numeroAR                       VARCHAR(10) NULL
 ,dataHoraRecebimento            DATETIME NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_CorrespondenciaParte_ComunicacaoParte ON CorrespondenciaParte (comunicacaoParte);

CREATE TABLE Parte (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,email                          VARCHAR(100) NULL
 ,polo                           CHAR(1) NULL
 ,telefone                       VARCHAR(20) NULL
 ,nemeroEndereco                 VARCHAR(10) NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE TABLE ProcessoParte (
  processo                       VARCHAR(20) NOT NULL
 ,parte                          INTEGER UNSIGNED NOT NULL
 ,PRIMARY KEY (processo, parte)
) ENGINE=InnoDB;
CREATE INDEX FK_ProcessoParte_processo ON ProcessoParte (processo);

CREATE INDEX FK_ProcessoParte_Parte ON ProcessoParte (parte);

CREATE TABLE CorrespondenciaTestemunha (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,comunicacaoTestemunha          INTEGER UNSIGNED NOT NULL
 ,texto                          VARCHAR(512) NOT NULL
 ,dataHoraEnvio                  TIMESTAMP NOT NULL
 ,tipoComunicacao                CHAR(1) NOT NULL
 ,numeroAR                       VARCHAR(10) NULL
 ,dataHoraRecebimento            DATETIME NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_CorrespondenciaTestemunha_ComunicacaoTestemumha ON CorrespondenciaTestemunha (comunicacaoTestemunha);

CREATE TABLE ComunicacaoParte (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,parte                          INTEGER UNSIGNED NOT NULL
 ,servidor                       VARCHAR(11) NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_ComunicacaoParte_Servidor ON ComunicacaoParte (servidor);

CREATE INDEX FK_ComunicacaoParte_Parte ON ComunicacaoParte (parte);

CREATE TABLE ComunicacaoTestemunha (
  id                             INTEGER UNSIGNED AUTO_INCREMENT NOT NULL
 ,servidor                       VARCHAR(11) NOT NULL
 ,testemunha                     INTEGER UNSIGNED NOT NULL
 ,PRIMARY KEY (id)
) ENGINE=InnoDB;
CREATE INDEX FK_ComunicacaoTestemunha_Servidor ON ComunicacaoTestemunha (servidor);

CREATE INDEX FK_ComunicacaoTestemunha_Testemunha ON ComunicacaoTestemunha (testemunha);

CREATE TABLE ParteFisica (
  parte                          INTEGER UNSIGNED NOT NULL
 ,nome                           VARCHAR(100) NULL
 ,nomeMae                        VARCHAR(100) NULL
 ,nomePai                        VARCHAR(100) NULL
 ,estadoCivil                    CHAR(1) NULL
 ,dataNascimento                 DATE NULL
 ,tipoDocumentoIdentificacao     VARCHAR(10) NULL
 ,documentoIdentificacao         VARCHAR(50) NULL
 ,PRIMARY KEY (parte)
) ENGINE=InnoDB;
CREATE INDEX FK_ParteFisica_Parte ON ParteFisica (parte);

CREATE TABLE ParteJuridica (
  parte                          INTEGER UNSIGNED NOT NULL
 ,cnpj                           VARCHAR(20) NULL
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
ALTER TABLE CorrespondenciaParte
  ADD CONSTRAINT Rel_ComunicacaoParte_CorrespondenciaParte FOREIGN KEY (comunicacaoParte)
    REFERENCES ComunicacaoParte (id)
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
ALTER TABLE CorrespondenciaTestemunha
  ADD CONSTRAINT Rel_ComunicacaoTestemunha_CorrespondenciaTestemunh FOREIGN KEY (comunicacaoTestemunha)
    REFERENCES ComunicacaoTestemunha (id)
;
ALTER TABLE ComunicacaoParte
  ADD CONSTRAINT Rel_Servidor_ComunicacaoParte FOREIGN KEY (servidor)
    REFERENCES Servidor (cpf)
;
ALTER TABLE ComunicacaoParte
  ADD CONSTRAINT Rel_Parte_ComunicacaoParte FOREIGN KEY (parte)
    REFERENCES Parte (id)
;
ALTER TABLE ComunicacaoTestemunha
  ADD CONSTRAINT Rel_Servidor_ComunicacaoTestemunha FOREIGN KEY (servidor)
    REFERENCES Servidor (cpf)
;
ALTER TABLE ComunicacaoTestemunha
  ADD CONSTRAINT Rel_Testemunha_ComunicacaoTestemunha FOREIGN KEY (testemunha)
    REFERENCES Testemunha (id)
;
ALTER TABLE ProcessoFase
  ADD CONSTRAINT Rel_Processo_ProcessoFase FOREIGN KEY (processo)
    REFERENCES Processo (npu)
;
ALTER TABLE ProcessoFase
  ADD CONSTRAINT Rel_Fase_ProcessoFase FOREIGN KEY (fase)
    REFERENCES Fase (id)
;

CREATE SCHEMA DBSPJC;
USE DBSPJC;

CREATE TABLE juizado (
	codigo_curso INTEGER,
	nome VARCHAR(30) NOT NULL,
	CONSTRAINT curso_pk PRIMARY KEY (codigo_curso)
);

CREATE TABLE professor (
	matricula_professor INTEGER,
	data_admissao DATE NOT NULL,
	matricula_lider INTEGER,
	CONSTRAINT professor_pk PRIMARY KEY (matricula_professor),
	CONSTRAINT professor_matricula_prof_fk FOREIGN KEY (matricula_professor) REFERENCES pessoa (matricula_pessoa),
	CONSTRAINT professor_matricula_lider_fk FOREIGN KEY (matricula_lider) REFERENCES professor (matricula_professor)
);
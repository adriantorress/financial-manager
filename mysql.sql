CREATE DATABASE FinancialManager;

drop database FinancialManager;

USE  FinancialManager;

CREATE TABLE tb_usuario (
	id INT PRIMARY KEY,
    nome VARCHAR(255),
    usuario VARCHAR(255) UNIQUE,
    senha VARCHAR(60)
);

CREATE TABLE tb_transacao (
	id INT PRIMARY KEY,
    id_user INT,
    tipo VARCHAR(50),
    data DATE,
    valor FLOAT,
    categoria VARCHAR(50),
    descricao VARCHAR(50),
    FOREIGN KEY(id_user) REFERENCES tb_usuario(id)
);

CREATE TABLE tb_orcamento(
	id INT PRIMARY KEY,
    id_user INT,
    categoriagasto INT,
    descricao VARCHAR(50),
    mesAno DATE,
    valorOrcado float,
    FOREIGN KEY(id_user) REFERENCES tb_usuario(id));

select * from tb_usuario;
select * from tb_orcamento
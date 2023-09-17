CREATE DATABASE FinancialManager;

CREATE TABLE tb_usuario (
	id INT PRIMARY KEY,
    nome VARCHAR(255),
    usuario VARCHAR(255),
    senha VARCHAR(60)
);

CREATE TABLE tb_transacao (
	id INT PRIMARY KEY,
    id_usuario INT,
    tipo VARCHAR(50),
    data DATE,
    valor FLOAT,
    categoria VARCHAR(50),
    descricao VARCHAR(50),
    FOREIGN KEY(id_usuario) REFERENCES tb_usuario(id)
);

CREATE TABLE tb_categoriagasto(
	id INT PRIMARY KEY,
    nome VARCHAR(50),
    id_usuario INT,
    FOREIGN KEY(id_usuario) REFERENCES tb_usuario(id)
);

CREATE TABLE tb_orcamento(
	id INT PRIMARY KEY,
    id_usuario INT,
    id_categoriagasto INT,
    mesAno DATE,
    valorOrcado float,
    FOREIGN KEY(id_usuario) REFERENCES tb_usuario(id)
    FOREIGN KEY(id_categoriagasto) REFERENCES tb_categoriagasto(id)
);
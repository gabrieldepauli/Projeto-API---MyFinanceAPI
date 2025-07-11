CREATE DATABASE MyFinanceApiDB;

USE MyFinanceApiDB;

CREATE TABLE Transacoes (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    tipo ENUM('receita', 'despesa') NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    data_criacao DATE NOT NULL
);

SELECT * FROM Transacoes;
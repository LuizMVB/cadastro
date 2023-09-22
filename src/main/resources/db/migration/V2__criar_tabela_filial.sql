CREATE TABLE Filial (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    nome VARCHAR(40) DEFAULT NULL,
    tipo VARCHAR(12) DEFAULT NULL,
    idMercado bigint(20) NOT NULL,
    FOREIGN KEY (idMercado) REFERENCES Mercado(id),
    PRIMARY KEY (id)
);
CREATE TABLE filial (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    nome VARCHAR(40) NOT NULL,
    tipo VARCHAR(12) NOT NULL COMMENT "Indica o tipo de filial quanto ao seu porte: - MERCEARIA; - MINIMERCADO; - SUPERMERCADO; - HIPERMERCADO",
    is_ativo BOOLEAN DEFAULT 1 NOT NULL,
    id_mercado bigint(20) NOT NULL,
    data_inclusao DATETIME NOT NULL,
    data_atualizacao DATETIME DEFAULT NULL,
    FOREIGN KEY (id_mercado) REFERENCES mercado(id),
    PRIMARY KEY (id)
);
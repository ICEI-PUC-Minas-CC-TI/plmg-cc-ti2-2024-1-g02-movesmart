-- Schema
CREATE SCHEMA IF NOT EXISTS movesmart;
-- Table Usuario
CREATE TABLE IF NOT EXISTS movesmart.usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(45),
    login VARCHAR(45),
    senha VARCHAR(45),
    email VARCHAR(45),
    telefone VARCHAR(45)
);

-- Table OD
CREATE TABLE IF NOT EXISTS movesmart.od (
    id_od SERIAL PRIMARY KEY,
    horario TIME,
    origem VARCHAR(45),
    destino VARCHAR(45),
    usuario_id_usuario INT NOT NULL,
    CONSTRAINT fk_usuario_id_od FOREIGN KEY (usuario_id_usuario) REFERENCES movesmart.usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Table Onibus
CREATE TABLE IF NOT EXISTS movesmart.onibus (
    id_onibus SERIAL PRIMARY KEY,
    numero VARCHAR(45)
);

-- Table Rota
CREATE TABLE IF NOT EXISTS movesmart.rota (
    id_rota SERIAL PRIMARY KEY,
    onibus_id_onibus INT NOT NULL,
    CONSTRAINT fk_onibus_id_rota FOREIGN KEY (onibus_id_onibus) REFERENCES movesmart.onibus (id_onibus) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Table Ponto
CREATE TABLE IF NOT EXISTS movesmart.ponto (
    id_ponto SERIAL PRIMARY KEY,
    logradouro VARCHAR(45),
    numero INT,
    rota_id_rota INT NOT NULL,
    od_id_od INT NOT NULL,
    CONSTRAINT fk_rota_id_ponto FOREIGN KEY (rota_id_rota) REFERENCES movesmart.rota (id_rota) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_od_id_ponto FOREIGN KEY (od_id_od) REFERENCES movesmart.od (id_od) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Table Horario
CREATE TABLE IF NOT EXISTS movesmart.horario (
    id_horario SERIAL PRIMARY KEY,
    horario TIME,
    onibus_id_onibus INT NOT NULL,
    CONSTRAINT fk_onibus_id_horario FOREIGN KEY (onibus_id_onibus) REFERENCES movesmart.onibus (id_onibus) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Table Onibus_has_Ponto
CREATE TABLE IF NOT EXISTS movesmart.onibus_has_ponto (
    onibus_id_onibus INT NOT NULL,
    pontos_id_ponto INT NOT NULL,
    pontos_rota_id_rota INT NOT NULL,
    pontos_od_id_od INT NOT NULL,
    PRIMARY KEY (
        onibus_id_onibus,
        pontos_id_ponto,
        pontos_rota_id_rota,
        pontos_od_id_od
    ),
    CONSTRAINT fk_onibus_id_onibus_has_ponto FOREIGN KEY (onibus_id_onibus) REFERENCES movesmart.onibus (id_onibus) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_ponto_id_onibus_has_ponto FOREIGN KEY (
        pontos_id_ponto,
        pontos_rota_id_rota,
        pontos_od_id_od
    ) REFERENCES movesmart.ponto (id_ponto, rota_id_rota, od_id_od) ON DELETE NO ACTION ON UPDATE NO ACTION
);
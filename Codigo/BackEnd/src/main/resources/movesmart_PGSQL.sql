-- Table Usuario
CREATE TABLE IF NOT EXISTS Usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(45),
    login VARCHAR(45),
    senha VARCHAR(45),
    email VARCHAR(45),
    telefone VARCHAR(45)
);

-- Table OD
CREATE TABLE IF NOT EXISTS OD (
    id_od SERIAL PRIMARY KEY,
    horario TIME,
    origem VARCHAR(45),
    destino VARCHAR(45),
    Usuario_id_usuario INT NOT NULL,
    CONSTRAINT fk_Usuario_id_OD FOREIGN KEY (Usuario_id_usuario) REFERENCES Usuario (id_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
);
0101
-- Table Onibus
CREATE TABLE IF NOT EXISTS Onibus (
    id_onibus SERIAL PRIMARY KEY,
    numero VARCHAR(45)
);

-- Table Rota
CREATE TABLE IF NOT EXISTS Rota (
    id_rota SERIAL PRIMARY KEY,
    Onibus_id_onibus INT NOT NULL,
    CONSTRAINT fk_Onibus_id_Rota FOREIGN KEY (Onibus_id_onibus) REFERENCES Onibus (id_onibus) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Table Ponto
CREATE TABLE IF NOT EXISTS Ponto (
    id_ponto SERIAL,
    logradouro VARCHAR(45),
    numero INT,
    Rota_id_rota INT NOT NULL,
    OD_id_od INT NOT NULL,
    PRIMARY KEY (id_ponto, Rota_id_rota, OD_id_od),
    CONSTRAINT fk_Rota_id_Ponto FOREIGN KEY (Rota_id_rota) REFERENCES Rota (id_rota) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_OD_id_Ponto FOREIGN KEY (OD_id_od) REFERENCES OD (id_od) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Table Horario
CREATE TABLE IF NOT EXISTS Horario (
    id_horario SERIAL PRIMARY KEY,
    horario TIME,
    Onibus_id_onibus INT NOT NULL,
    CONSTRAINT fk_Onibus_id_Horario FOREIGN KEY (Onibus_id_onibus) REFERENCES Onibus (id_onibus) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- Table Onibus_has_Ponto
CREATE TABLE IF NOT EXISTS Onibus_has_Ponto (
    Onibus_id_onibus INT NOT NULL,
    Pontos_id_ponto INT NOT NULL,
    Pontos_Rota_id_rota INT NOT NULL,
    Pontos_OD_id_od INT NOT NULL,
    PRIMARY KEY (
        Onibus_id_onibus,
        Pontos_id_ponto,
        Pontos_Rota_id_rota,
        Pontos_OD_id_od
    ),
    CONSTRAINT fk_Onibus_id_Onibus_has_Ponto FOREIGN KEY (Onibus_id_onibus) REFERENCES Onibus (id_onibus) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_Ponto_id_Onibus_has_Ponto FOREIGN KEY (
        Pontos_id_ponto,
        Pontos_Rota_id_rota,
        Pontos_OD_id_od
    ) REFERENCES Ponto (id_ponto, Rota_id_rota, OD_id_od) ON DELETE NO ACTION ON UPDATE NO ACTION
);

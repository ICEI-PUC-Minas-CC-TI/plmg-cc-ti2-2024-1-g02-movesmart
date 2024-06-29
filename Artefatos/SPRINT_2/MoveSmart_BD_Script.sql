-- Criação da tabela Usuário
CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    login TEXT NOT NULL,
    senha TEXT NOT NULL,
    email TEXT NOT NULL
);

-- Criação da tabela Transporte
CREATE TABLE Transporte (
    id SERIAL PRIMARY KEY,
    localizacao VARCHAR(100) NOT NULL,
    status VARCHAR(50) NOT NULL,
    id_usuario INTEGER REFERENCES Usuario(id) ON DELETE CASCADE,
    horarios DOUBLE PRECISION[] -- array de horários
);

-- Criação da tabela Rota
CREATE TABLE Rota (
    id SERIAL PRIMARY KEY,
    horario DOUBLE PRECISION NOT NULL,
    id_transporte INTEGER REFERENCES Transporte(id) ON DELETE CASCADE,
    pontos_parada VARCHAR(100)[] -- array de pontos de parada
);

-- Criação da tabela Notificação
CREATE TABLE Notificacao (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL,
    mensagem TEXT NOT NULL,
    timestamp_tra TIMESTAMP NOT NULL,
    id_usuario INTEGER REFERENCES Usuario(id) ON DELETE CASCADE
);

-- Tabela de junção entre Usuario e Email
CREATE TABLE Usuario_Email (
    id_usuario INTEGER REFERENCES Usuario(id) ON DELETE CASCADE,
    email TEXT NOT NULL,
    PRIMARY KEY (id_usuario, email)
);

-- Tabela de junção entre Rota e Ponto de Parada
CREATE TABLE Rota_PontoParada (
    id_rota INTEGER REFERENCES Rota(id) ON DELETE CASCADE,
    ponto_parada VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_rota, ponto_parada)
);

-- Tabela de junção entre Transporte e Rota
CREATE TABLE Transporte_Rota (
    id_transporte INTEGER REFERENCES Transporte(id) ON DELETE CASCADE,
    id_rota INTEGER REFERENCES Rota(id) ON DELETE CASCADE,
    PRIMARY KEY (id_transporte, id_rota)
);

-- Tabela de junção entre Notificação e Tipo
CREATE TABLE Notificacao_Tipo (
    id_notificacao INTEGER REFERENCES Notificacao(id) ON DELETE CASCADE,
    tipo VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_notificacao, tipo)
);

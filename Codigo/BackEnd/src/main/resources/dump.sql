-- PRIMEIRO PASSO:EXECUTAR OS QUATRO PRIMEIROS COMANDOS NO BANCO DE DADOS (USUARIO, TRANSPORTE, ROTA E NOTIFICACAO)
-- SEGUNDO PASSO: EXECUTAR OS COMANDOS DE RELACIONAMENTO ENTRE AS TABELAS

--Criação da tabela Usuario
CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    login VARCHAR(50),
    senha VARCHAR(50),
    email VARCHAR(100)
);

-- Criação da tabela Transporte
CREATE TABLE Transporte (
    id SERIAL PRIMARY KEY,
    localizacao VARCHAR(100),
    status VARCHAR(50)
);

-- Criação da tabela Rota
CREATE TABLE Rota (
    id SERIAL PRIMARY KEY,
    horario DATE,
    transporte_id INT,
    ponto_parada VARCHAR(100),
    FOREIGN KEY (transporte_id) REFERENCES Transporte(id)
);

-- Criação da tabela Notificação
CREATE TABLE Notificacao (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(50),
    mensagem TEXT,
    timestamp TIMESTAMP
);

-- Tabela de relacionamento entre Usuario e Notificação (Muitos-para-Muitos)
CREATE TABLE Usuario_Notificacao (
    usuario_id INT,
    notificacao_id INT,
    PRIMARY KEY (usuario_id, notificacao_id),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (notificacao_id) REFERENCES Notificacao(id)
);

-- Tabela de relacionamento entre Transporte e Notificação (Muitos-para-Muitos)
CREATE TABLE Transporte_Notificacao (
    transporte_id INT,
    notificacao_id INT,
    PRIMARY KEY (transporte_id, notificacao_id),
    FOREIGN KEY (transporte_id) REFERENCES Transporte(id),
    FOREIGN KEY (notificacao_id) REFERENCES Notificacao(id)
);

-- Restrição: Um usuário não pode configurar uma notificação para um transporte que não existe
ALTER TABLE Usuario_Notificacao
ADD CONSTRAINT fk_usuario_notificacao FOREIGN KEY (notificacao_id) REFERENCES Notificacao(id);

-- Restrição: Um transporte não pode passar por uma rota que não existe
ALTER TABLE Rota
ADD CONSTRAINT fk_rota FOREIGN KEY (transporte_id) REFERENCES Transporte(id);
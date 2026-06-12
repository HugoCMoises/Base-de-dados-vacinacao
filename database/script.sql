DROP DATABASE IF EXISTS vacinacao_db;
CREATE DATABASE vacinacao_db;
USE vacinacao_db;

CREATE TABLE regiao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE escolaridade (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(80) NOT NULL
);

CREATE TABLE paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    sexo VARCHAR(20) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    endereco VARCHAR(150),
    cidade VARCHAR(80),
    regiao_id INT NOT NULL,
    escolaridade_id INT NOT NULL,
    doente BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_paciente_regiao
        FOREIGN KEY (regiao_id) REFERENCES regiao(id),
    CONSTRAINT fk_paciente_escolaridade
        FOREIGN KEY (escolaridade_id) REFERENCES escolaridade(id)
);

CREATE TABLE vacina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    fabricante VARCHAR(100) NOT NULL,
    tipo VARCHAR(60) NOT NULL,
    quantidade_doses INT NOT NULL,
    lote VARCHAR(30) NOT NULL,
    data_aplicacao DATE NOT NULL
);

CREATE TABLE aplicacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    paciente_id INT NOT NULL,
    vacina_id INT NOT NULL,
    dose INT NOT NULL,
    data DATE NOT NULL,
    observacoes VARCHAR(255),
    CONSTRAINT fk_aplicacao_paciente
        FOREIGN KEY (paciente_id) REFERENCES paciente(id),
    CONSTRAINT fk_aplicacao_vacina
        FOREIGN KEY (vacina_id) REFERENCES vacina(id)
);

INSERT INTO regiao (id, nome) VALUES
(1, 'Norte'),
(2, 'Nordeste'),
(3, 'Centro-Oeste'),
(4, 'Sudeste'),
(5, 'Sul');

INSERT INTO escolaridade (id, descricao) VALUES
(1, 'Fundamental incompleto'),
(2, 'Fundamental completo'),
(3, 'Ensino medio'),
(4, 'Ensino superior'),
(5, 'Pos-graduacao');

INSERT INTO paciente (id, nome, idade, sexo, cpf, telefone, endereco, cidade, regiao_id, escolaridade_id, doente) VALUES
(1, 'Ana Souza', 22, 'Feminino', '111.111.111-01', '(11) 90000-0001', 'Rua das Flores, 10', 'Sao Paulo', 4, 3, false),
(2, 'Bruno Lima', 35, 'Masculino', '111.111.111-02', '(21) 90000-0002', 'Av Central, 200', 'Rio de Janeiro', 4, 4, true),
(3, 'Carla Mendes', 41, 'Feminino', '111.111.111-03', '(31) 90000-0003', 'Rua A, 33', 'Belo Horizonte', 4, 5, false),
(4, 'Diego Santos', 17, 'Masculino', '111.111.111-04', '(91) 90000-0004', 'Travessa Norte, 50', 'Belem', 1, 2, false),
(5, 'Elisa Rocha', 64, 'Feminino', '111.111.111-05', '(51) 90000-0005', 'Rua Sul, 88', 'Porto Alegre', 5, 1, true),
(6, 'Fabio Costa', 28, 'Masculino', '111.111.111-06', '(81) 90000-0006', 'Rua Recife, 120', 'Recife', 2, 3, false),
(7, 'Gabriela Alves', 33, 'Feminino', '111.111.111-07', '(62) 90000-0007', 'Av Goias, 500', 'Goiania', 3, 4, false),
(8, 'Henrique Barros', 52, 'Masculino', '111.111.111-08', '(41) 90000-0008', 'Rua Parana, 77', 'Curitiba', 5, 3, true),
(9, 'Isabela Martins', 15, 'Feminino', '111.111.111-09', '(85) 90000-0009', 'Rua Ceara, 91', 'Fortaleza', 2, 2, false),
(10, 'Joao Pereira', 71, 'Masculino', '111.111.111-10', '(92) 90000-0010', 'Av Amazonas, 300', 'Manaus', 1, 1, true),
(11, 'Karen Silva', 26, 'Feminino', '111.111.111-11', '(11) 90000-0011', 'Rua Paulista, 45', 'Sao Paulo', 4, 4, false),
(12, 'Lucas Ferreira', 39, 'Masculino', '111.111.111-12', '(71) 90000-0012', 'Rua Bahia, 18', 'Salvador', 2, 3, false),
(13, 'Mariana Gomes', 58, 'Feminino', '111.111.111-13', '(67) 90000-0013', 'Rua Campo, 600', 'Campo Grande', 3, 2, true),
(14, 'Nelson Araujo', 44, 'Masculino', '111.111.111-14', '(48) 90000-0014', 'Rua Ilha, 20', 'Florianopolis', 5, 5, false),
(15, 'Olivia Nunes', 19, 'Feminino', '111.111.111-15', '(98) 90000-0015', 'Rua Maranhao, 102', 'Sao Luis', 2, 3, false),
(16, 'Paulo Ribeiro', 30, 'Masculino', '111.111.111-16', '(69) 90000-0016', 'Rua Rondonia, 11', 'Porto Velho', 1, 4, false),
(17, 'Renata Teixeira', 47, 'Feminino', '111.111.111-17', '(61) 90000-0017', 'Quadra 5, casa 9', 'Brasilia', 3, 5, true),
(18, 'Samuel Dias', 12, 'Masculino', '111.111.111-18', '(27) 90000-0018', 'Rua Vitoria, 76', 'Vitoria', 4, 1, false),
(19, 'Tatiane Lopes', 55, 'Feminino', '111.111.111-19', '(84) 90000-0019', 'Rua Natal, 303', 'Natal', 2, 2, false),
(20, 'Victor Martins', 68, 'Masculino', '111.111.111-20', '(43) 90000-0020', 'Rua Londrina, 14', 'Londrina', 5, 3, true);

INSERT INTO vacina (id, nome, fabricante, tipo, quantidade_doses, lote, data_aplicacao) VALUES
(1, 'CoronaVac', 'Butantan', 'Virus inativado', 2, 'CV2026A', '2026-02-10'),
(2, 'Pfizer Adulto', 'Pfizer', 'RNA mensageiro', 2, 'PF2026B', '2026-02-15'),
(3, 'Influenza', 'Fiocruz', 'Virus inativado', 1, 'FLU2026C', '2026-03-01'),
(4, 'Hepatite B', 'Butantan', 'Recombinante', 3, 'HB2026D', '2026-03-12'),
(5, 'Febre Amarela', 'Fiocruz', 'Virus vivo atenuado', 1, 'FA2026E', '2026-04-05');

INSERT INTO aplicacao (paciente_id, vacina_id, dose, data, observacoes) VALUES
(1, 1, 1, '2026-02-10', 'Sem reacao'),
(1, 1, 2, '2026-03-10', 'Esquema completo'),
(2, 2, 1, '2026-02-15', 'Paciente relatou dor no braco'),
(3, 3, 1, '2026-03-01', 'Dose unica'),
(4, 1, 1, '2026-02-18', 'Sem reacao'),
(5, 3, 1, '2026-03-05', 'Prioridade por idade'),
(6, 2, 1, '2026-02-20', 'Sem reacao'),
(6, 2, 2, '2026-03-20', 'Esquema completo'),
(7, 4, 1, '2026-03-12', 'Primeira dose'),
(8, 5, 1, '2026-04-05', 'Dose unica'),
(10, 3, 1, '2026-03-07', 'Paciente do grupo de risco'),
(11, 1, 1, '2026-02-22', 'Sem reacao'),
(12, 2, 1, '2026-02-25', 'Sem reacao'),
(13, 3, 1, '2026-03-08', 'Paciente doente em acompanhamento'),
(14, 4, 1, '2026-03-15', 'Primeira dose'),
(16, 5, 1, '2026-04-07', 'Dose unica'),
(17, 2, 1, '2026-02-28', 'Paciente doente em acompanhamento'),
(17, 2, 2, '2026-03-28', 'Esquema completo'),
(19, 1, 1, '2026-02-26', 'Sem reacao'),
(20, 3, 1, '2026-03-09', 'Prioridade por idade');

USE vacinacao_db;

-- SELECT de pacientes
SELECT p.id, p.nome, p.idade, p.cpf, r.nome AS regiao, e.descricao AS escolaridade
FROM paciente p
JOIN regiao r ON p.regiao_id = r.id
JOIN escolaridade e ON p.escolaridade_id = e.id
ORDER BY p.nome;

-- SELECT de vacinados
SELECT DISTINCT p.id, p.nome, p.cpf
FROM paciente p
JOIN aplicacao a ON p.id = a.paciente_id
ORDER BY p.nome;

-- SELECT de nao vacinados
SELECT p.id, p.nome, p.cpf
FROM paciente p
LEFT JOIN aplicacao a ON p.id = a.paciente_id
WHERE a.id IS NULL
ORDER BY p.nome;

-- SELECT por regiao
SELECT r.nome AS regiao, p.nome AS paciente
FROM paciente p
JOIN regiao r ON p.regiao_id = r.id
ORDER BY r.nome, p.nome;

-- SELECT por escolaridade
SELECT e.descricao AS escolaridade, p.nome AS paciente
FROM paciente p
JOIN escolaridade e ON p.escolaridade_id = e.id
ORDER BY e.descricao, p.nome;

-- SELECT quantidade de doses
SELECT COUNT(*) AS total_doses_aplicadas
FROM aplicacao;

-- SELECT vacinado x doente
SELECT
    CASE WHEN p.doente = 1 THEN 'Doente' ELSE 'Nao doente' END AS situacao,
    COUNT(DISTINCT a.paciente_id) AS total_vacinados
FROM paciente p
LEFT JOIN aplicacao a ON p.id = a.paciente_id
GROUP BY p.doente;

-- SELECT total por regiao
SELECT r.nome AS regiao, COUNT(DISTINCT a.paciente_id) AS total_vacinados
FROM regiao r
JOIN paciente p ON r.id = p.regiao_id
LEFT JOIN aplicacao a ON p.id = a.paciente_id
GROUP BY r.nome
ORDER BY r.nome;

-- Quantidade por escolaridade
SELECT e.descricao AS escolaridade, COUNT(DISTINCT a.paciente_id) AS total_vacinados
FROM escolaridade e
JOIN paciente p ON e.id = p.escolaridade_id
LEFT JOIN aplicacao a ON p.id = a.paciente_id
GROUP BY e.descricao
ORDER BY e.descricao;

-- Quantidade por faixa etaria
SELECT
    CASE
        WHEN p.idade < 18 THEN 'Menor de 18'
        WHEN p.idade BETWEEN 18 AND 29 THEN '18 a 29'
        WHEN p.idade BETWEEN 30 AND 59 THEN '30 a 59'
        ELSE '60 ou mais'
    END AS faixa_etaria,
    COUNT(DISTINCT a.paciente_id) AS total_vacinados
FROM paciente p
LEFT JOIN aplicacao a ON p.id = a.paciente_id
GROUP BY faixa_etaria
ORDER BY faixa_etaria;

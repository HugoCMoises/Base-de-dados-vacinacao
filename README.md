# Sistema de Gerenciamento de Vacinacao

Projeto desenvolvido em Java, MySQL e interface de console para uma disciplina de Analise e Desenvolvimento de Sistemas.

## Estrutura de pastas

```text
src/
  Main.java
  Menu.java
  DatabaseConnection.java
  Paciente.java
  Vacina.java
  Aplicacao.java
  PacienteDAO.java
  VacinaDAO.java
  AplicacaoDAO.java

database/
  script.sql
  consultas.sql
  modelo-conceitual.txt
  modelo-logico.txt
  modelo-fisico.txt

lib/
  mysql-connector-j-8.4.0.jar

README.md
```

## O que o sistema faz

- Cadastra pacientes.
- Consulta paciente por CPF.
- Lista pacientes.
- Cadastra vacinas.
- Registra aplicacao de vacina.
- Consulta historico de vacinacao.
- Mostra relatorios.
- Mostra consultas estatisticas.

## Banco de dados

O banco usado e MySQL.

Para criar o banco:

1. Abra o MySQL Workbench, phpMyAdmin, DBeaver, PyCharm Database ou terminal do MySQL.
2. Execute o arquivo:

```sql
database/script.sql
```

Esse script cria o banco `vacinacao_db`, cria as tabelas e insere dados de exemplo.

## Configuracao da conexao

A conexao esta no arquivo:

```text
src/DatabaseConnection.java
```

Configuracao padrao:

```java
private static final String URL = "jdbc:mysql://localhost:3306/vacinacao_db?useTimezone=true&serverTimezone=UTC";
private static final String USUARIO = "root";
private static final String SENHA = "";
```

Se o seu MySQL tiver senha, altere o valor de `SENHA`.

## Dependencia

Para executar o projeto, e necessario ter o MySQL Connector/J no classpath.

Neste projeto ele esta na pasta:

```text
lib/mysql-connector-j-8.4.0.jar
```

## Como compilar

No terminal, dentro da pasta do projeto:

```powershell
javac -d out src\*.java
```

Esse comando cria a pasta `out` com os arquivos compilados. A pasta `out` nao precisa ser enviada para o GitHub.

## Como executar no Windows

```powershell
java -cp "out;lib\mysql-connector-j-8.4.0.jar" Main
```

## Consultas SQL

As consultas pedidas estao no arquivo:

```text
database/consultas.sql
```

Esse arquivo possui consultas de pacientes, vacinados, nao vacinados, total por regiao, total por escolaridade, faixa etaria, doses aplicadas e vacinado x doente.

## Como enviar para o GitHub

Dentro da pasta do projeto, execute:

```bash
git init
git add .
git commit -m "Projeto sistema de vacinacao"
git branch -M main
git remote add origin https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git
git push -u origin main
```

Antes de enviar, confira se o arquivo `.gitignore` esta no projeto. Ele evita enviar a pasta `out`, arquivos `.class` e configuracoes locais do PyCharm.

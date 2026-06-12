# Sistema de Gerenciamento de Vacinacao

Projeto academico desenvolvido em Java com MySQL e interface via console.

O sistema permite cadastrar pacientes, cadastrar vacinas, registrar aplicacoes e consultar informacoes sobre a vacinacao da populacao. Tambem possui consultas estatisticas simples para apoiar a analise dos dados cadastrados.

## Tecnologias utilizadas

- Java
- MySQL
- JDBC
- MySQL Connector/J
- Interface de texto no console

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

## Funcionalidades

- Cadastra pacientes.
- Consulta paciente por CPF.
- Lista pacientes.
- Cadastra vacinas.
- Registra aplicacao de vacina.
- Consulta historico de vacinacao.
- Mostra relatorios.
- Mostra consultas estatisticas.

## Estrutura de dados utilizada

Antes de salvar os dados no banco, o sistema cria objetos em Java e armazena temporariamente esses objetos em listas:

```java
ArrayList<Paciente>
ArrayList<Vacina>
ArrayList<Aplicacao>
```

Depois disso, os dados sao enviados para o MySQL pelas classes DAO. Essa etapa mostra o uso de estrutura de dados antes da persistencia no banco.

## Banco de dados

O banco usado e MySQL.

Para criar o banco e inserir os dados de exemplo:

1. Abra o MySQL Workbench, phpMyAdmin, DBeaver, PyCharm Database ou terminal do MySQL.
2. Execute o arquivo:

```text
database/script.sql
```

Esse script cria:

- banco `vacinacao_db`;
- tabelas `regiao`, `escolaridade`, `paciente`, `vacina` e `aplicacao`;
- chaves primarias e estrangeiras;
- dados de exemplo para testes.

O arquivo `database/consultas.sql` possui consultas prontas para testar os dados.

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

## Dependencia do MySQL

Para executar o projeto, e necessario ter o MySQL Connector/J no classpath.

Neste projeto ele esta na pasta:

```text
lib/mysql-connector-j-8.4.0.jar
```

## Como compilar e executar

Com o MySQL ligado e o banco criado, abra o terminal na pasta do projeto e compile:

```powershell
javac -d out src\*.java
```

Depois execute:

```powershell
java -cp "out;lib\mysql-connector-j-8.4.0.jar" Main
```

Se algum arquivo `.java` for alterado, compile novamente antes de executar.

## Como executar pelo PyCharm

1. Abra o projeto no PyCharm.
2. Adicione o arquivo `lib/mysql-connector-j-8.4.0.jar` como biblioteca do projeto.
3. Garanta que o MySQL esteja ligado.
4. Execute `database/script.sql` se o banco ainda nao tiver sido criado.
5. Abra `src/Main.java`.
6. Clique no botao verde para executar o metodo `main`.

## Menu do sistema

Ao executar o projeto, o menu exibido no console possui as opcoes:

```text
1 - Cadastrar paciente
2 - Consultar paciente
3 - Listar pacientes
4 - Cadastrar vacina
5 - Registrar vacinacao
6 - Consultar historico de vacinacao
7 - Relatorios
8 - Consultas estatisticas
9 - Sair
```

## Consultas SQL

As consultas pedidas estao no arquivo:

```text
database/consultas.sql
```

Esse arquivo possui consultas de pacientes, vacinados, nao vacinados, total por regiao, total por escolaridade, faixa etaria, doses aplicadas e vacinado x doente.

## Modelagem

Os arquivos de modelagem estao na pasta `database`:

- `modelo-conceitual.txt`
- `modelo-logico.txt`
- `modelo-fisico.txt`

Eles descrevem as entidades, tabelas, atributos, chaves primarias, chaves estrangeiras e relacionamentos do sistema.

## Observacoes

- A pasta `out` e gerada apenas depois da compilacao.
- O arquivo `.gitignore` evita salvar arquivos compilados e configuracoes locais da IDE.
- O projeto foi mantido simples para facilitar a leitura e a apresentacao academica.

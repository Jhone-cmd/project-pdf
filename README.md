# PDF Report Generator

Projeto Spring Boot desenvolvido com base nas aulas da plataforma Rocketseat, com foco em persistência de dados, geração de relatórios e exportação de documentos em PDF.

## Visão geral

Esta aplicação gerencia duas entidades principais, escolas e alunos, armazenando os dados em banco H2 em memória e disponibilizando consultas em JSON e relatórios em PDF.

O objetivo do projeto é demonstrar, de forma prática, como transformar dados persistidos em documentos prontos para leitura, compartilhamento e arquivamento. Além disso, o projeto reforça conceitos importantes do ecossistema Java moderno, como Spring Boot, JPA, validação, organização em camadas e uso de bibliotecas externas para geração de PDF.

## Funcionalidades

- Listagem completa de alunos via endpoint REST.
- Listagem completa de escolas via endpoint REST.
- Geração de relatório em PDF com a relação de alunos.
- Geração de relatório em PDF com a relação de escolas.
- Ordenação dos dados antes da montagem do relatório.
- Cálculo de idade dos alunos com base na data de nascimento.
- Exibição da data de criação dos registros.
- Popularização automática do banco com dados iniciais ao subir a aplicação.

## Endpoints

### Alunos

- `GET /students`
  - Retorna todos os alunos cadastrados em JSON.

- `GET /students/report`
  - Gera e retorna um PDF com a lista de alunos.

### Escolas

- `GET /schools`
  - Retorna todas as escolas cadastradas em JSON.

- `GET /schools/report`
  - Gera e retorna um PDF com a lista de escolas e a quantidade de alunos por escola.

## Estrutura de pastas

```text
src/
  main/
    java/com/jhonecmd/pdf/
      PdfApplication.java
      controller/
        SchoolController.java
        StudentController.java
      model/
        SchoolEntity.java
        StudentEntity.java
      repository/
        SchoolRepository.java
        StudentRepository.java
      seeds/
        SeedsRunner.java
      service/
        SchoolService.java
        StudentService.java
      start/
        StartApp.java
      utils/
        DateUtils.java
        ReportUtils.java
    resources/
      application.properties
      static/
      templates/
  test/
    java/com/jhonecmd/pdf/
      PdfApplicationTests.java
```

## Explicação da arquitetura

### `controller`

Responsável por receber as requisições HTTP e devolver as respostas. Nesta aplicação, os controllers expõem tanto dados em JSON quanto arquivos PDF.

### `model`

Contém as entidades JPA que representam o domínio da aplicação:

- `SchoolEntity`: escola com nome e data de criação.
- `StudentEntity`: aluno com nome, e-mail, senha, data de nascimento, escola vinculada e data de criação.

### `repository`

Camada responsável pelo acesso ao banco de dados com Spring Data JPA.

### `service`

Onde fica a regra de negócio e a montagem dos relatórios PDF.

- `StudentService` monta o relatório de alunos.
- `SchoolService` monta o relatório de escolas e soma o total de alunos.

### `seeds`

Classe responsável por inserir dados iniciais quando a aplicação inicia, facilitando testes e demonstrações.

### `utils`

Concentra funções auxiliares reutilizáveis:

- `DateUtils`: formatação de datas e cálculo de idade.
- `ReportUtils`: abstração para criação e preenchimento do PDF.

### `start`

Contém o ponto de inicialização complementar da aplicação.

## Bibliotecas utilizadas

### Spring Boot

Base do projeto. Usado para criar uma aplicação web organizada, com injeção de dependência, REST controllers e configuração simplificada.

### Spring Data JPA

Usado para persistência e consulta de dados no banco H2.

### Spring Validation

Disponibiliza suporte à validação de dados, mesmo que o projeto atual use mais o fluxo de persistência e leitura.

### Spring WebMVC

Responsável pela criação dos endpoints REST e pelas respostas HTTP.

### H2 Database

Banco em memória utilizado para facilitar o desenvolvimento e a demonstração da aplicação.

### iText 7

Biblioteca usada para gerar os arquivos PDF a partir dos dados armazenados no sistema.

### Lombok

Reduz código repetitivo, principalmente em construtores e injeção de dependência.

### DevTools

Auxilia no desenvolvimento com recarga mais prática e produtividade.

## Configuração do ambiente

O projeto utiliza Java 21 e Maven.

### Banco de dados

O banco configurado é o H2 em memória, com console habilitado para inspeção durante o desenvolvimento.

Arquivo de configuração principal:

- `src/main/resources/application.properties`

## Como executar

### Pré-requisitos

- Java 21
- Maven

### Execução com Maven Wrapper

```bash
./mvnw spring-boot:run
```

### Execução do pacote de testes

```bash
./mvnw test
```

## Geração dos PDFs

A criação dos relatórios acontece na camada de serviço. O fluxo segue esta ideia:

1. O controller recebe a requisição no endpoint `/report`.
2. O service busca os dados no banco.
3. Os registros são ordenados e formatados.
4. A biblioteca iText monta o documento PDF.
5. O arquivo é devolvido como resposta HTTP com `Content-Type: application/pdf`.

Nos relatórios, a aplicação apresenta:

- título centralizado;
- tabela com colunas organizadas;
- dados formatados para leitura;
- total de alunos por escola no relatório de escolas.

### Relatório de alunos

O relatório de alunos exibe nome, e-mail, idade, data de nascimento, escola e data de criação.

### Relatório de escolas

O relatório de escolas exibe nome, quantidade de alunos e data de criação, além de um total consolidado.

## Por que geração de PDF ainda é importante hoje

Mesmo com dashboards modernos, APIs e interfaces web interativas, o PDF continua sendo um formato essencial em muitos cenários.

Ele é importante porque:

- mantém a formatação original independentemente do dispositivo;
- facilita compartilhamento com clientes, gestores e setores administrativos;
- serve como registro formal e arquivável;
- é amplamente aceito em processos acadêmicos, corporativos e jurídicos;
- permite transformar dados operacionais em documentos prontos para consumo humano.

Na prática, isso significa que uma aplicação pode sair do nível de armazenamento e consulta de dados e entregar um resultado final mais útil para pessoas e processos. É exatamente essa ponte que este projeto demonstra: dados organizados virando relatórios profissionais.

## Aprendizado principal

Este projeto foi desenvolvido assistindo às aulas da plataforma Rocketseat e serve como estudo prático de como estruturar uma aplicação Java com foco em produtividade, organização e geração de documentos em PDF.

O aprendizado mais relevante aqui é entender que uma aplicação bem construída não apenas armazena dados, mas também os transforma em informação útil para quem precisa consultar, imprimir, compartilhar ou auditar esses registros.

## Observações

- Os dados são carregados automaticamente por seeds ao iniciar a aplicação.
- O banco H2 é volátil, então os dados são reiniciados quando a aplicação para.
- Os relatórios são retornados inline no navegador, permitindo visualização imediata.

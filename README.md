# 📚 Academic System - Trabalho Final POO

## Sobre o Projeto

Este repositório contém a implementação do trabalho semestral da disciplina de Orientação a Objetos, ministrada pelo Professor Rodrigo Martins Pagliares no curso de Bacharelado em Ciência da Computação da UNIFAL-MG.

O sistema consiste em uma aplicação Java para gerenciamento acadêmico de turmas e avaliações, desenvolvida incrementalmente através de 61 histórias de usuário que abordam desde conceitos fundamentais de POO até práticas avançadas de engenharia de software, como persistência, segurança, testes automatizados, interface gráfica e CI/CD.

## 🎯 Objetivos do Trabalho
Complementar conhecimentos de orientação a objetos através da implementação de funcionalidades técnicas e requisitos de qualidade:
* Interface gráfica com o usuário (JavaFX)
* Segurança e controle de acesso (RBAC)
* Persistência de dados (TXT, XML, JSON)
* Logging e auditoria
* Tratamento de exceções e validação
Incentivar a adoção dos princípios da Engenharia de Software Aprimorada por IA (AI-Augmented Software Engineering) , avaliando os ganhos de produtividade com o uso de LLMs, mantendo o ser humano no centro do processo de desenvolvimento ("human-in-the-loop").

## 👥 Equipe e Competências
Papel|	Integrante	|Responsabilidades
|---|----|----|
Pessoa 1|	Victória Almeida Tambasco|	Core Academic & Core Architecture - Implementação das regras de negócio principais, modelos de domínio, serviços base (ClassService, AssessmentService, ReportService), refatoração com Lombok, inicialização do sistema e relatórios acadêmicos.|
Pessoa 2	|A escolher|	Persistência de Dados & Logs - Implementação dos repositórios e salvamento em TXT, XML e JSON; configuração dinâmica de persistência; criação do PersistenceService; configuração de logging (SLF4J/Logback) e auditoria de operações.|
Pessoa 3	|Joaquim Pedro do Nascimento Moreira de Jesus|	Segurança, Validação & Exceções - Autenticação e autorização baseada em papéis (RBAC); hierarquia de exceções customizadas; validação declarativa com Jakarta Bean Validation; logs de eventos de segurança (login/logout/falhas de autorização); menu dinâmico por papel.|
Pessoa 4	|A escolher|	Interface Gráfica (JavaFX) - Configuração da infraestrutura JavaFX; criação de todas as telas (login, principal, cadastro de turmas/avaliações, relatórios, configuração de persistência, visualização); introdução do AuthenticationController para desacoplar a GUI das regras de serviço.|
Pessoa 5	|Luiz Gabriel da Silva Cabrera|	DevOps, CI/CD & Infraestrutura de Testes - Containerização com Docker; configuração de testes automatizados (JUnit/Mockito); pipeline de CI/CD com GitHub Actions; geração de relatórios de cobertura; publicação automática de imagens Docker; validação de Pull Requests; branch protection.|

## 🛠️ Tecnologias Utilizadas
Tecnologia	|Propósito
----|----
Java SE 25|	Linguagem de programação principal|
Maven|	Gerenciamento de dependências e build|
Lombok	|Redução de código boilerplate|
Jakarta Bean Validation	|Validação de domínio|
JUnit Jupiter / Mockito|	Testes automatizados|
SLF4J / Logback|	Logging e auditoria|
JavaFX|	Interface gráfica|
Docker	|Containerização|
GitHub Actions	|CI/CD e automação|

## 📋 Funcionalidades Implementadas
### ✅ Módulo Acadêmico
* Cadastro de turmas
* Cadastro de avaliações (Prova, Trabalho Prático, Seminário, Trabalho)
* Geração de relatórios (sumário de turmas, peso de avaliações)

### ✅ Persistência
* Salvamento em TXT, XML e JSON
* Configuração dinâmica de tipo de persistência
* Relatório de configuração de persistência

### ✅ Segurança
* Autenticação de usuários
* Autorização baseada em papéis (ADMIN/PROFESSOR)
* Logout e gerenciamento de sessão
* Menu dinâmico baseado no papel do usuário

### ✅ Validação e Exceções
* Hierarquia de exceções customizadas
* Validação de domínio com Jakarta Bean Validation
* Tratamento de erros de entrada do teclado

### ✅ Interface Gráfica (JavaFX)
* Tela de login
* Menu principal baseado no papel do usuário
* Telas de cadastro de turmas e avaliações
* Tela de relatórios
* Tela de configuração de persistência
* Tela de visualização de turmas e avaliações

### ✅ DevOps e CI/CD
* Dockerfile para containerização
* Pipeline de CI com GitHub Actions
* Testes automatizados com cobertura
* Publicação automática de imagem Docker
* Validação de Pull Requests
* Branch protection para a branch main

## 🚀 Como Executar
* Requisitos
* Java 25 ou superior
* Maven 3.9 ou superior
* Docker (opcional)

### Clonar e Compilar
```bash
git clone https://github.com/Amoreirinha/Trabalho-Final-POO.git
cd Trabalho-Final-POO/academic-system
mvn clean install
```
### Executar (Linha de Comando)
```bash
mvn exec:java
```
### Executar com Docker
```bash
docker build -t academic-system .
docker run -it academic-system
```

### Credenciais de Teste
Usuário|	Senha	|Papel
----|----|----
admin|	admin123	|ADMIN
professor|	prof123|	PROFESSOR

## 📊 Status das Histórias de Usuário
Todas as 61 histórias de usuário definidas no trabalho foram implementadas, cobrindo:

- ✅ 5 histórias acadêmicas
- ✅ 5 histórias de persistência
- ✅ 5 histórias de segurança
- ✅ 4 histórias de validação/exceções
- ✅ 11 histórias de arquitetura/refatoração
- ✅ 1 história de Docker
- ✅ 15 histórias de testes
- ✅ 5 histórias de logging
- ✅ 9 histórias de interface gráfica (JavaFX)
- ✅ 6 histórias de CI/CD

## 📝 Licença
Este repositório foi desenvolvido para fins educacionais e acadêmicos.

---

Última atualização: 29 de junho de 2026

Professor: Rodrigo Martins Pagliares

Disciplina: Orientação a Objetos - 3º Período - Ciência da Computação - UNIFAL-MG
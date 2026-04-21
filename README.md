# 🏥 API de Gestão de Cirurgias

API REST para gerenciamento de cirurgias hospitalares, desenvolvida com **Spring Boot**, com foco em regras de negócio, controle de agenda médica e integridade dos dados.

Permite cadastrar, atualizar, consultar e excluir cirurgias, com associação de pacientes, médicos e instrumentos cirúrgicos.

## 🚀 Tecnologias

- Java 17
- Spring Boot 3
- Spring Data JPA / Hibernate
- MySQL
- Maven
- Lombok
- Springdoc (Swagger/OpenAPI)

---

## ⚙️ Funcionalidades

- [x] Cadastro de cirurgias
- [x] Atualização de dados da cirurgia
- [x] Associação com paciente
- [x] Associação com múltiplos médicos
- [x] Definição de médico principal
- [x] Associação com instrumentos cirúrgicos
- [x] Consulta por ID e listagem
- [x] Exclusão de cirurgias

---

## 📌 Regras de Negócio

- [x] Um paciente não pode ter mais de uma cirurgia no mesmo dia
- [x] Um médico não pode participar de mais de uma cirurgia no mesmo horário
- [x] Apenas um médico pode ser definido como principal por cirurgia
- [x] Controle de relacionamentos N:N com entidades intermediárias
- [x] Atualização inteligente de relacionamentos (adição e remoção)

---

## 🧠 Arquitetura

- Padrão MVC (Controller, Service, Repository)
- Separação de responsabilidades por camada
- Uso de entidades intermediárias (MedicoCirurgia, InstrumentoCirurgia)
- Regras de negócio centralizadas na camada de serviço
- Garantia de consistência e integridade dos dados

---

## ▶️ Como executar o projeto

### 📌 Banco de Dados

```sql
CREATE DATABASE cirurgias;
```

### 📌 Configuração

No arquivo application.yml ou application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/cirurgias

spring.datasource.username=seu_usuario

spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update

### 📌 Executar aplicação

```bash
git clone https://github.com/JacquelineCasali/sistema-marcacao-cirurgia
cd sistema-marcacao-cirurgia
mvn spring-boot:run
```

🔗 API disponível em: http://localhost:8080

## 📄 Documentação da API

Acesse via Swagger:

🔗 http://localhost:8080/swagger-ui/index.html

## 📌 Diferenciais do Projeto

- [x] Implementação de regras complexas de agenda médica
- [x] Controle de conflitos de horário
- [x] Modelagem com relacionamentos N:N
- [x] Uso de entidades intermediárias
- [x] Foco em consistência e integridade dos dados
- [x] Estrutura preparada para evolução

## 👩‍💻 Autora

Jacqueline Casali

🔗 LinkedIn: https://www.linkedin.com/in/jaquelinecasali/

🔗 GitHub: https://github.com/JacquelineCasali

🌐 Portfólio: https://casali.vercel.app

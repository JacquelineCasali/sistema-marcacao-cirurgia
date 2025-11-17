🏥 API – Sistema de Marcação de Cirurgias

API REST desenvolvida para gerenciamento de cirurgias hospitalares, permitindo registrar, atualizar, consultar e excluir cirurgias.
Cada cirurgia pode ser associada a:

um paciente

- ➡️um médico principal

- ➡️ vários médicos participantes

- ➡️ instrumentos cirúrgicos usados

Construída com Spring Boot, JPA/Hibernate, MySQL, seguindo boas práticas e regras de negócio específicas da área hospitalar.
---
## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA**
- **Validator**
- **Mysql**
- **Lombok**
- **Swagger (Springdoc OpenAPI)**
- **Maven**

---
## 📦 Funcionalidades da API

A API permite:

### ✔ Criar cirurgia
Incluindo:
- data da cirurgia
- descrição
- paciente
- lista de médicos
- médico principal
- lista de instrumentos

### ✔ Atualizar cirurgia
Com possibilidade de:
- alterar data/descrição
- trocar paciente
- adicionar/remover médicos
- alterar ou definir novo médico principal
- adicionar/remover instrumentos

### ✔ Regras de Negócio Implementadas
- ❗ **Um paciente não pode ter mais de uma cirurgia no mesmo dia**
- ❗ **Os medicos  não pode ter mais de uma cirurgia no mesmo horário**
- ❗ **Somente um médico pode ser marcado como principal por cirurgia**
- ❗ Entidades de relação (MedicoCirurgia e InstrumentoCirurgia) utilizam chaves compostas
- ❗ Atualização inteligente: remove relacionamentos que não existem mais no DTO

### ✔ Consultas
- Listar todas as cirurgias
- Buscar cirurgia por ID

### ✔ Exclusão
- Deletar cirurgia por ID

---

## 🛠 Como Rodar o Projeto

### 1️⃣ Criar banco Mysql

```sql
CREATE DATABASE cirurgias;

## ▶️ Como Rodar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/JacquelineCasali/sistema-marcacao-cirurgia
 

2. ** Configure o banco de dados no arquivo application.yml:
  spring.datasource.url=jdbc:mysql://127.0.0.1/cirurgias
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
    
3. Compile e rode o projeto:

mvn spring-boot:run
Acesse no navegador ou via Postman:

4. Acesse a API no postman ou no swagger

http://localhost:8080

5. Acesse a API no swagger
   http://localhost:8080/swagger-ui/index.html

```
🗂 Inserir dados de exemplo nas tabelas ,
iserir dados nas tabelas 
INSERT INTO `cirurgia` VALUES (1,'2021-11-10 10:00:00',1,'Duplo J'),(2,'2021-10-13 07:00:00',2,'Drenagem');
INSERT INTO `especialidade` VALUES (1,'Cardiovascular'),(2,'Geral'),(3,'Neurológica'),(4,'Plástica');
INSERT INTO `instrumento` VALUES (1,'Pinça Anatômica Dissecção'),(2,'Bisturi'),(3,'Pinça Kelly'),(4,'Porta agulha Mayo Hegar'),(5,'Tesoura Cirurgica'),(6,'Caixa Cirúrgica');
INSERT INTO `instrumento_cirurgia` VALUES (1,1),(2,1),(1,2),(2,3),(1,4),(2,4);
INSERT INTO `medico` VALUES (1,11111,'Maria','111222',1),(2,22222,'João','222333',2);
INSERT INTO `medico_cirurgia` VALUES (1,1,1),(1,2,0),(2,1,0),(2,2,1);
INSERT INTO `paciente` VALUES (1,11111,'Carlos','2021-10-11 00:00:00','N39.0'),(2,22222,'Mariana','2021-09-05 00:00:00','A31.0');

👩‍💻 Para conhecer o front-end desse projeto acesse o github para clonar o projeto

https://github.com/JacquelineCasali/Sistema-de-marcao-de-cirurgia-front-end

🧑‍💻 Autora

Jaqueline Casali
Desenvolvedora Full Stack
GitHub: https://github.com/JacquelineCasali
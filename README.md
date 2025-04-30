# Login Ramal

Sistema de gerenciamento de logins em ramais, permitindo realizar pesquisas eficientes por número e definir intervalo de ramais.

---

> Desenvolvido com:
> - Java + Spring Boot (Back-end)
> - HTML, CSS, JavaScript e Bootstrap (Front-end)
> - Banco de dados: SQL Server

---

## Funcionalidades

- 🔐 **Login de ramais**: autenticação simples com verificação de e-mail para realizar logout com segurança.
- 📶 **Controle de status dos ramais**: exibe em tempo real quais ramais estão disponíveis ou ocupados.
- 🔍 **Barra de pesquisa interativa**: permite localizar rapidamente um ramal específico pelo número, mesmo dentro de listas filtradas.
- 🎯 **Filtros por intervalo de ramais**: os dados exibidos se adaptam ao intervalo definido pelo usuário nas configurações.
- 💬 **Feedbacks com SweetAlert2**: todas as ações (login, logout, erros, etc.) retornam mensagens visuais amigáveis e estilizadas com SweetAlert2.
- 🧩 **Modais de login e logout**: botões da tela abrem modais para fazer login/logout, melhorando a experiência do usuário.

---

## Tecnologias utilizadas

- **Back-end:** Java 21, Spring Boot, Maven
- **Front-end:** HTML5, CSS3, JavaScript, Bootstrap
- **Banco de dados:** SQL Server

---

## Como rodar o projeto

### Pré-requisitos:
- Java 21 (versão usada no projeto)
- IntelliJ IDEA ou outra IDE compatível com Spring
- SQL Server instalado e rodando
- Postman (opcional, para testar rotas da API)
- **VSCode** (ou outra IDE com suporte a Live Server para o front-end)

### Passos:

#### 1. Rodando o Back-end:
1. Clone o repositório:
   ```bash
   git clone https://github.com/Thiago-vinicius2/login-ramal.git
   ```

2. Navegue até o diretório do projeto e execute o comando para rodar o back-end:
   ```bash
   ./mvnw spring-boot:run
   ```
   Isso iniciará o servidor do Spring Boot, geralmente disponível em `http://localhost:8080`.

#### 2. Rodando o Front-end:
1. Navegue até a pasta onde o arquivo `index.html` do front-end está localizado.
   
2. Abra a pasta no **VSCode** (ou outra IDE de sua preferência).

3. **Com o Live Server**:
   - No VSCode, clique com o botão direito sobre o arquivo `index.html` (ou a pasta que o contém) e selecione **Open with Live Server**.
   - Isso abrirá o front-end no navegador, geralmente em `http://127.0.0.1:5500` (ou outra porta, dependendo da configuração do Live Server).

#### 3. Testando a Aplicação:
- Com o **back-end** rodando em `http://localhost:8080` e o **front-end** em `http://127.0.0.1:5500`, você pode interagir com o sistema de gerenciamento de ramais.

---

## Configuração do Banco de Dados

Antes de rodar o projeto, é necessário configurar o banco de dados para garantir que o Spring Boot se conecte corretamente ao SQL Server.

### 1. **Configuração do Banco de Dados SQL Server**

O sistema utiliza o banco de dados **SQL Server**, e você precisará configurar o banco e as credenciais de acesso para o Spring Boot.

### 2. **Credenciais de Banco de Dados**

No arquivo `src/main/resources/application.properties`, as credenciais de acesso ao banco de dados devem ser configuradas conforme o exemplo abaixo:

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=NOME_DO_BANCO;trustServerCertificate=true
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Atenção: substitua NOME_DO_BANCO, SEU_USUARIO e SUA_SENHA pelas configurações reais do seu ambiente local.
```

### 3. **Passos para Configurar o Banco de Dados**

1. **Instalar o SQL Server**: 
   Se ainda não tiver o **SQL Server** instalado, baixe e instale o SQL Server em seu computador ou servidor.

2. **Criar o Banco de Dados**:
   No SQL Server, crie um banco de dados.

   Execute o seguinte comando SQL para criar o banco de dados:

   ```sql
   CREATE DATABASE NOME_DO_BANCO;
   ```

3. **Atualizar as Credenciais no `application.properties`**:
   No arquivo `src/main/resources/application.properties`, atualize as credenciais de acesso (usuário e senha) para corresponder ao seu ambiente local.

   Por exemplo, se o nome de usuário do seu banco de dados for `admin` e a senha `senha123`, altere a configuração para:

   ```properties
   spring.datasource.username=admin
   spring.datasource.password=senha123
   ```

4. **Verificar a Conexão**:
   Após configurar o banco de dados, o Spring Boot tentará se conectar automaticamente ao banco na inicialização do projeto. Se o banco de dados e as credenciais estiverem corretos, a aplicação será capaz de se conectar ao banco sem problemas.

### 4. **Problemas Comuns**

- **Banco não encontrado**: Certifique-se de que o banco de dados foi criado corretamente e está acessível.
- **Problemas de credenciais**: Verifique se o nome de usuário e a senha estão configurados corretamente no arquivo `application.properties`.
- **Porta SQL Server**: O padrão do SQL Server é a porta `1433`. Verifique se o servidor SQL está rodando nessa porta.

Com essas configurações, o banco de dados estará pronto para ser usado pelo projeto.

---

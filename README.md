<h1 align="center">Best-Commerce</h1>

<p align='center'> 
    <img src="https://img.shields.io/badge/Spring_Boot  V3.0.5-F2F4F9?style=for-the-badge&logo=spring-boot"/>
    <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/>  
</p>

O desenvolvimento de um aplicativo de e-commerce chamado "best-commerce" utilizando o framework Springboot em Java. Para melhorar a eficiência, segurança e qualidade do sistema, segui as melhores práticas de desenvolvimento em Java e Springboot, utilizando diversas tecnologias.

Para garantir uma maior flexibilidade e separação de responsabilidades, utilizei DTO em vez de DAO para trafegar os dados entre as camadas, seguindo uma arquitetura em camadas padrão, incluindo as camadas de repository, service e controller, além das entidades do modelo.

Para simplificar o acesso aos dados do banco de dados, utilizei o Spring Data JPA, um módulo do Spring que oferece uma implementação padrão das interfaces repository. Além disso, utilizei o Docker para rodar o banco de dados PostgreSQL e o PGAdmin, e para empacotar a API em um contêiner Docker, garantindo uma maior facilidade de implantação e portabilidade do aplicativo.

Para garantir a segurança da aplicação, implementei a autenticação do usuário usando o Spring Security, e configurei perfis para os ambientes de desenvolvimento e de teste. No ambiente de desenvolvimento, utilizei o banco de dados PostgreSQL, enquanto no ambiente de teste, utilizei o banco de dados H2.

O modelo de dados do aplicativo consistiu nas entidades Usuário, Pedido, Loja, Produto e Categoria, e utilizei um diagrama conceitual para visualizar a relação entre essas entidades. Para documentar os endpoints da API, utilizei o Swagger UI.

Além disso, para garantir a qualidade do código, criei testes unitários em algumas camadas do sistema, incluindo as camadas de serviço. Também adicionei o tratamento de erros personalizados, que é extremamente importante em uma aplicação de e-commerce, pois pode ajudar a identificar e corrigir problemas rapidamente, reduzindo o impacto sobre os usuários e evitando perda de vendas.

Em resumo, o objetivo do meu trabalho foi criar um aplicativo de e-commerce funcional e escalável, seguindo as melhores práticas de desenvolvimento em Java e Springboot, e utilizando tecnologias como Docker, DTO, Swagger UI, testes unitários e tratamento de erros personalizados para melhorar a eficiência, segurança e qualidade do sistema.

## Veja o projeto

Experimente live demo completa:

![Bakcend](https://raw.githubusercontent.com/joosecj/best-commerce-ws/main/docs/imgs/video.gif)

## Como criar e executar o Best-Commerce localmente

Criar e executar o projeto em seu ambiente de desenvolvimento local é muito fácil. Certifique-se de ter o **Git**, **JDK17**, **Maven** e **Docker**(No uso do docker não e necessario a instalação da JDK17 e Maven) instalados e siga as instruções abaixo. Precisa de informações adicionais? entre em contato no e-mail josecarloscjj@gmail.com.
(Estas instruções pressupõem que você esteja instalando como um usuário root.)

### Backend - Via Docker

1. Clone o código fonte
   ```bash
   git clone git@github.com:joosecj/best-commerce-ws.git
   ```

2. Navegue até a pasta **backend**:
   ```bash
   cd backend
   ```

3. Execute os comandos:
   ```bash
   docker compose up -d
   ```

4. Ao executar o projeto, pode ser acessado um navegador da Web em http://**IP CONTAINER**:8080:

- Obs: para descobrir o IP do container, digite o comando a seguir:

   ```bash
   docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' api-bestcommerce
   ```

5. Interfaçe do Swagger UI para utilizar/testar os endpoints:

   ```bash
   http://IPCONTAINER:8080/swagger-ui/index.html
   ```

6. Documentação do Swagger contendo todos os endpoints que pode ser importada para o **postman** e fazer as requisições GET/PUT/DELETE E UPDATE.

  - Obs: projeto precisa está em execução.

      ```bash
      http://IPCONTAINER:8080/v3/api-docs
      ```

7. Todos os locais que estão **localhost:8080**, substitua para **IP CONTAINER**:8080

### Backend - Via terminal

1. Clone o código fonte
   ```bash
   git clone git@github.com:joosecj/best-commerce-ws.git
   ```

2. Navegue até a pasta **backend**.
   ```bash
   cd backend
   ```

3. Execute o comando:
   - Obs: para alterar o perfil, só alterar o **test** para **dev**, sendo que ambiente de dev necessita do banco de dados PosgreSQL já o **test** roda com banco H2 em memoria.

   ```bash
    ./mvnw spring-boot:run -Dspring.profiles.active=test
   ```
  
4. Ao executar o projeto, pode ser acessado um navegador da Web em **http://localhost:8080/**

5. Interfaçe do Swagger UI para utilizar/testar os endpoints [aqui](http://localhost:8080/swagger-ui/index.html).

6. Documentação do Swagger contendo todos os endpoints que pode ser importada para o **postman** e fazer as requisições GET/PUT/DELETE E UPDATE, através do [link](http://localhost:8080/v3/api-docs). Obs: projeto precisa está em execução.

#

#### Respostas com as demais perguntas do questionario [aqui](https://github.com/joosecj/best-commerce-ws/tree/main/docs).

#

   ## Tecnologias utlizadas:

   - [Java](https://docs.oracle.com/en/java/javase/17/)
   - [Maven](https://maven.apache.org/guides/)
   - [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
   - [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
   - [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
   - [JWT](https://jwt.io/introduction)
   - [Swagger UI](https://swagger.io/tools/swagger-ui/)
   - [H2 Database](https://www.h2database.com/html/main.html)
   - [PostgreSQL](https://www.postgresql.org/docs/)

   ### Ferramentas:

   - [Postman](https://www.postman.com/api-documentation-tool/)
   - [Docker](https://docs.docker.com/reference/)
   - [Docker Compose](https://docs.docker.com/compose/)


   ##

   <div align="center">
   <h2>Autor: José Carlos</h2>
      <img align="center" alt="Jose-Js" height="190" width="190" src="https://avatars.githubusercontent.com/u/100246121?s=400&u=b15a545fb2c49f97f84e25aa0520b8b525631384&v=4"
   </div>
   </br> </br>
   <div align="center">
      <a href = "mailto:josecarloscjj@gmail.com"><img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
      <a href="https://www.linkedin.com/in/jos%C3%A9-carlos-a79736a0/" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a> 
   </div>
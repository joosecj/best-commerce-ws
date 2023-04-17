

##

3. **Compartilhe seu script de banco de dados conosco. Para este projeto específico, precisamos de um esquema de dialeto PostgreSQL, mas se você definiu as necessidades, pode compartilhar conosco a estrutura baseada em documento usando Mongo db também. Observe que postgre e/ou mongoDb são obrigatórios. mas você precisa entender o que está fazendo para defini-lo com melhores requisitos.**

Foi utilizado o **PostgreSQL** e link para seed com os dado estão [aqui](https://github.com/joosecj/best-commerce-ws/blob/main/backend/create.sql).

##

5. **Por favor, explique sua ideia de infraestrutura:**

Minha ideia de infraestrutura para o aplicativo Springboot "best-commerce" seria criar uma arquitetura em camadas usando o padrão de arquitetura de software de três camadas (Three-Tier Architecture) para separar as responsabilidades de cada componente.

Na camada de dados (Data Layer), teríamos as entidades do sistema, como Usuário, Pedido, Loja, Produto e Categoria, além das interfaces de acesso a dados (Repositories) para cada entidade.

Na camada de negócios (Business Layer), teríamos as classes responsáveis por gerenciar a lógica de negócios, que irão se comunicar com as camadas Data e Presentation. Nessa camada, também teríamos os serviços (Services) que serão responsáveis por orquestrar as transações de negócio entre as entidades.

Na camada de apresentação (Presentation Layer), teríamos os controllers, responsáveis por gerenciar as requisições do usuário e a comunicação com a camada Business. Nessa camada, também teríamos os endpoints de API REST para que outros sistemas possam consumir os serviços da aplicação.

Para implementar a autenticação do usuário, podemos usar o Spring Security, que é um módulo do Spring que fornece autenticação e controle de acesso para a aplicação. Com ele, podemos criar filtros de autenticação e autorização para proteger as rotas que precisam de autenticação.

Além disso, podemos usar o Spring Data JPA, que é um módulo do Spring que simplifica o acesso aos dados do banco de dados, fornecendo uma implementação padrão das interfaces Repository e eliminando a necessidade de escrever código SQL.

Com essa arquitetura em camadas e o uso de ferramentas como o Spring Security e o Spring Data JPA, podemos desenvolver um aplicativo robusto e escalável, seguindo as melhores práticas de desenvolvimento em Java e Springboot. Também podemos considerar o uso de tecnologias como Docker e Kubernetes para implantar o aplicativo em um ambiente de contêineres, com grandes chances de se tornar um microserviço.

##

6. **Por favor, explique todo o seu trabalho anterior com suas próprias palavras:**

Meu trabalho anterior foi o desenvolvimento de um aplicativo de e-commerce chamado "best-commerce" utilizando o framework Springboot em Java. Para melhorar a eficiência, segurança e qualidade do sistema, segui as melhores práticas de desenvolvimento em Java e Springboot, utilizando diversas tecnologias.

Para garantir uma maior flexibilidade e separação de responsabilidades, utilizei DTO em vez de DAO para trafegar os dados entre as camadas, seguindo uma arquitetura em camadas padrão, incluindo as camadas de repository, service e controller, além das entidades do modelo.

Para simplificar o acesso aos dados do banco de dados, utilizei o Spring Data JPA, um módulo do Spring que oferece uma implementação padrão das interfaces repository. Além disso, utilizei o Docker para rodar o banco de dados PostgreSQL e o PGAdmin, e para empacotar a API em um contêiner Docker, garantindo uma maior facilidade de implantação e portabilidade do aplicativo.

Para garantir a segurança da aplicação, implementei a autenticação do usuário usando o Spring Security, e configurei perfis para os ambientes de desenvolvimento e de teste. No ambiente de desenvolvimento, utilizei o banco de dados PostgreSQL, enquanto no ambiente de teste, utilizei o banco de dados H2.

O modelo de dados do aplicativo consistiu nas entidades Usuário, Pedido, Loja, Produto e Categoria, e utilizei um diagrama conceitual para visualizar a relação entre essas entidades. Para documentar os endpoints da API, utilizei o Swagger UI.

Além disso, para garantir a qualidade do código, criei testes unitários em algumas camadas do sistema, incluindo as camadas de serviço. Também adicionei o tratamento de erros personalizados, que é extremamente importante em uma aplicação de e-commerce, pois pode ajudar a identificar e corrigir problemas rapidamente, reduzindo o impacto sobre os usuários e evitando perda de vendas.

Em resumo, o objetivo do meu trabalho foi criar um aplicativo de e-commerce funcional e escalável, seguindo as melhores práticas de desenvolvimento em Java e Springboot, e utilizando tecnologias como Docker, DTO, Swagger UI, testes unitários e tratamento de erros personalizados para melhorar a eficiência, segurança e qualidade do sistema.

![Diagrama](https://raw.githubusercontent.com/joosecj/best-commerce-ws/main/docs/imgs/WS_Work.png)
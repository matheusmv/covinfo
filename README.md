# COVINFO

Projeto desenvolvido durante a disciplina de engenharia de software

## Backend

API Rest desenvolvida durante a disciplina de Engenharia de Software que tem como objetivo fornecer dados relacionados ao COVID-19

### Principais tecnologias

 - Spring Web
 - Spring Data JPA
 - Spring Security (autenticação e autorização com JWT)
 - MySQL
 - Consumo de APIs externas:
   - [Campanha Nacional de Vacinação contra Covid-19](https://opendatasus.saude.gov.br/dataset/covid-19-vacinacao)
   - [Registro de Ocupação Hospitalar COVID-19](https://opendatasus.saude.gov.br/dataset/registro-de-ocupacao-hospitalar)
   - [Postmon](https://github.com/PostmonAPI/postmon)

### Configurando variáveis de ambiente

 - [application.yml](https://github.com/matheusmv/covinfo/blob/main/backend/app/src/main/resources/application.yml)
 - [docker-compose](https://github.com/matheusmv/covinfo/blob/main/backend/docker-compose.yml)

### Executando a aplicação

 - Por meio do Maven wrapper:

       git clone https://github.com/matheusmv/covinfo.git && cd covinfo/backend
    
       ./mvnw package && java -jar app/target/app-0.0.1.jar

 - Utilizando docker-compose:

       git clone https://github.com/matheusmv/covinfo.git && cd covinfo/backend
       
       docker-compose build
       
       docker-compose up
       
### Documentação

- [Documentação Postman](https://documenter.getpostman.com/view/9922970/TzY1hGdJ)
- Swagger(http://localhost:8080/swagger-ui.html)

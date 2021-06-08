# COVINFO
Projeto desenvolvido durante a disciplina de engenharia de software

## Backend

 - [Documentação Postman](https://documenter.getpostman.com/view/9922970/TzY1hGdJ)

### Configurando MySQL e MailDev

*Para executar a aplicação nos ambientes de teste e desenvolvimento certifique-se de instalar as seguintes ferramentas:*

 - [Docker](https://www.docker.com/get-started)
 - [MySQL](https://hub.docker.com/_/mysql/)
 - [MailDev](https://maildev.github.io/maildev/)

Configurando a imagem do MySQL Server no docker:

    docker pull mysql/mysql-server:latest
    docker run --name=mysql-server -p 3306:3306 -e MYSQL_ROOT_PASSWORD=admin -d mysql/mysql-server:8.0
    
Conectando-se ao MySQL server:

    docker exec -it mysql-server mysql -u root -p

Configurando a imagem do MailDev no docker:

    docker pull maildev/maildev
    docker run -p 80:80 -p 25:25 maildev/maildev

Conectando-se ao MailDev:

    http://localhost:80

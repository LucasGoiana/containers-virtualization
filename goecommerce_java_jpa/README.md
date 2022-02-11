
<h1 align="center"> GO Ecommerce </a>  </h1>
 <p align="center"> Projeto criado para a disciplina  "PERSISTENCE" - FIAP <br>Sistema de cadastro de produtos e pedidos em um portal de e-commerce</p>

# Como utilizar o backend

### Stack
	

 - Java 11
 - MySql
 - Spring Framework

### Pré-requisitos

 - Docker
 - Conexão a internet
 
 
 <p> Clone ou Extraia o projeto em um diretório de sua preferência:</p>
 
    "diretorio de sua preferencia"
    git clone <projeto>
    
ou

	"diretorio de sua preferencia"
	unzip file.zip

    
### Configuração
O projeto foi configurado para que inicialize a aplicação e banco de dados em contêineres.
As seguintes portas estão sendo utilizadas para exposição dos serviços:

 - 8081 - Aplicação
 - 3306 - Banco de Dados
 
 Recomendamos que as mesmas não estejam alocadas no momento da execução dos contêineres.
 Caso necessite mudar, vá até o arquivo docker-compose.yml e altere o parâmetro a seguir:

Para aplicação:

	ports:
		- "{porta_desejada}:8081"	
    
Para banco de dados:

	ports:
		- "{porta_desejada}:3306"	

### Run
Em um terminal, entre no diretorio do projeto e encontre o arquivo docker-compose.yaml, execute o seguinte comando:

    docker-compose up

Aguarde alguns minutos enquanto o docker monta o ambiente e disponibiliza a aplicação e o banco de dados para serem utilizados.

> Obs: O processo demora em torno de 3 a 4 minutos para ser concluído.

# Documentação da API

A api está documentada em swagger, para consulta-la acesse:

> http://localhost:8081/api/swagger-ui/index.html

# Desenvolvendo um Sistema para Eleição Usando Quarkus Framework

Implementação do projeto exemplo construído no aulão LAB Quarkus, disponível na DIO e ministrado por [Thiago Poiani](https://github.com/thpoiani).

## Projeto original

https://github.com/thpoiani/lab-quarkus

### Objetivo

Desenvolver um sistema distribuído e escalável para eleições, contendo aplicações responsáveis para votação, gerenciamento de eleição e consulta de resultados utilizando Java, Docker, MongoDB e Quarkus.

## Arquitetura

#### Traefik
	Reverse proxy utilizado como API Gateway e Load Balancing

#### Jaeger
	Tracing

#### Graylog
	Logging centralizado:
		- MongoDB (configurações) e OpenSearch (Elasticsearch open source para indexação)

### election-management
	- Gerenciamento dos candidatos
		* MariaDB

	- Gerenciamento das eleições
		* MariaDB
		
		* Redis: para posterior utilização pelo microsserviço de votação visando alta escalabilidade e publicação de evento quando uma eleição é iniciada (event driven)
		
		* Sincroniza resultado parcial da votação do Redis para o MariaDB a partir de um Scheduler a cada 10s

### voting-app
	- Gerenciamento das requisições de votos
		* Redis

### result-app
	- Disponibilização dos resultados da eleição a partir da comunicação com o microsserviço election-management
	
	- O endpoint dos resultados utiliza o conceito de Server Sent Events, que seria algo parecido com um WebSocket de via única onde apenas o server envia informações para o cliente

## Build

**./cicd-build.sh** _microservice_name_

Exemplo:

```shell script
./cicd-build.sh election-management
```

## Deploy

**./cicd-blue-green-deployment.sh** _microservice_name_ _tag_version_

Exemplo:

```shell script
./cicd-blue-green-deployment.sh election-management 1.0.0
```

## Scale

TAG=_tag_version_ **docker compose up -d** _microservice_name_ **--scale** _microservice_name_=_number_of_containers_ **--no-recreate**

Exemplo:

```shell script
TAG=1.0.0 docker compose up -d voting-app --scale voting-app=4 --no-recreate
```

## Testes

Rodar testes unitários e de integração do _election-management_:

```shell script
./mvnw verify -DskipITs=false -Dquarkus.log.handler.gelf.enabled=false -Dquarkus.opentelemetry.enabled=false -Dquarkus.datasource.jdbc.driver=org.mariadb.jdbc.Driver
```

### Testes de carga: **Locust**

## Frontend

Cliente web React com exibição do resultado parcial da votação em near real time

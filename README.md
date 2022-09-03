# Retail discount service

### Prerequisites:

First you should have java 11 installed on your machine:

	hhttps://www.oracle.com/java/technologies/downloads/#java11
	
	and you have to set JAVA_HOME if it wasn't set.

You should also have docker engine and docker-compose:

	https://docs.docker.com/compose/install/compose-desktop/
    https://docs.docker.com/compose/install/

## Getting Started:

Clone repository and checkout "feature/apply-spring-security" branch.

## To run the application:

Go to project root directory, you should see pom.xml and docker-compose.yml

Run below commands:

    ./mvnw clean install 

or if you have Maven installed you can run:

    mvn clean install 

Then run :

    docker-compose up --build

This you will have 2 containers up and running. One for Mongo and one for the discount service.

## To use discount service:

You should first create user (sign up) using : `/api/auth/signup`

User should be "**admin**" or "**moderator**" to ba able to access discount API

You can use below curl:

    curl --location --request POST 'localhost:8081/api/auth/signup' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "username":"zizo",
    "email":"zizo@gmail.com",
    "password":"zizo1234",
    "roles" : [
    "admin"
    ]
    }
    '

Then you should log in and get the access token using `/api/auth/signin`

You can use below curl for login:

    curl --location --request POST 'localhost:8081/api/auth/signin' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "username": "zizo",
    "password": "zizo1234"
    }'

Then You can use the bearer token to access the discount endpoint.

You can use below curl:

    curl --location --request POST 'localhost:8081/v1/bill/discount' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6aXpvIiwiaWF0IjoxNjYyMjQyNjMwLCJleHAiOjE2NjIzMjkwMzB9.1TWN3i9UCTTng6SctTklWrofpQCkDH8ho7xfW8K0Vjj3BdUji_rXu4EPrNrvcunCg__DZ_Ky2nmQgT_y_k5ndQ' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "userTye":"C2Y",
    "billAmount":100,
    "itemId":"44295f91-1b2a-4172-9f24-2b0eef64eb42"
    }
    '

## To generate coverage report:

First make sure that Mongo container is up and running. If not you can start it using below command:

    docker start discount_mongo_db_1

Then run:

    ./mvnw test -Dspring.profiles.active=test

Then go to project root directory then `target/site`. You should find the report
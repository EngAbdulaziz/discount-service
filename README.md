# Retail discount service

### Prerequisites:

First you should have java 11 installed on your machine:

	hhttps://www.oracle.com/java/technologies/downloads/#java11
	
	and you have to set JAVA_HOME if it wasn't set.

You should also have docker engine and docker-compose:

	https://docs.docker.com/compose/install/compose-desktop/
    https://docs.docker.com/compose/install/

## Getting Started:

Clone repository and checkout "main" branch.

## To run the application:

Go to project root. you should see pom.xml and docker-compose.yml

Run below commands:

    ./mvnw clean install 

or if you have Maven installed you can run:

    mvn clean install 

Then run :

    docker-compose up --build

This you will have 2 containers up and running. One for Mongo and one for the discount service.

## Try discount service:

You can access the application endpoint on port 8080. You can use below curl:

    curl --location --request POST 'localhost:8080/v1/bill/discount' \
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
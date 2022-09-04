#!/bin/bash
PS3='Choose action: '
options=("Run service" "Generate test report"  "Quit")
select fav in "${options[@]}"; do
    case $fav in
        "Run service")
        ./mvnw clean install
        docker-compose up --build
            ;;
        "Generate test report")
        docker start discount_mongo_db_1
        ./mvnw test -Dspring.profiles.active=test
            ;;
	"Quit")
	    echo "User requested exit"
	    exit
	    ;;
        *) echo "invalid option $REPLY";;
    esac
done
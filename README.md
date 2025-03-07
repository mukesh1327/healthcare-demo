# Health monitoring Demo app


Download kafka 

start zookeeper
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

start Kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties


start project
mvn spring-boot:run



Test in Postman

This URL is only used to produce some random dummy data and post it in DB to populate data
POST: http://localhost:8080/api/v1/health-data/send


This URL will query the DB and get the data
GET: http://localhost:8080/api/v1/health-data


This will get the alerts through Kafka
http://localhost:8080/api/v1/alerts




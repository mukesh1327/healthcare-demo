# Health monitoring Demo app


Download kafka 

start zookeeper
.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

start Kafka
.\bin\windows\kafka-server-start.bat .\config\server.properties


start project
mvn spring-boot:run



Test in Postman

http://localhost:8080/api/v1/health-data/send


http://localhost:8080/api/v1/health-data


http://localhost:8080/api/v1/alerts




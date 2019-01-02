# demo-project
Sample project to test modern IT technologies

1. GIT repo
https://github.com/laxa1986/demo-project


2. Docker
Sign up on docker web cite
Install docker from official web cite
links:
https://docs.docker.com/get-started/
Login into docker on your local machine
```
docker login
```


3. Pre-deploy initialization
in file 'docker/docker-compose.yml' you can change volume settings ~/docker/oracle and ~/docker/kafka


4. Deploy and run environment
```
demo-project/docker>docker-compose up -d
```
this will start 3 services: oracle, zookeeper and kafka


5. Post-deploy initialization
create a kafka topic and check it is created
```
docker-compose exec kafka kafka-topics.sh --create --zookeeper zookeeper:2181 --topic test --partitions 1 --replication-factor 1
docker-compose exec kafka kafka-topics.sh --list --zookeeper zookeeper:2181
```
once we try to work with kafka and query localhost:9092 it discovers its entire topology in form of <container_id>:port
outside of docker intranet "demonw" nobody knows that container_id is a localhost, so we need to add this mapping
```
#get container_id created for kafka server
docker ps -aqf "name=kafka"
#add this mapping into your /etc/hosts
127.0.0.1 <container_id>
```

6. IDE configuration
Add 'Lombok' plugin in Intellij IDEA

7. UI tool to explore DB State
Oracle SQL Developer
username: system, password: oracle, hostname: localhost, port: 1521, SID: xe
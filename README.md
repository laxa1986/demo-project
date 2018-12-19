# demo-project
Sample project to test modern IT technologies

1. Docker
Sign up on docker web cite
Install docker from official web cite
links:
https://docs.docker.com/get-started/

2. Docker oracle image
Get image from public docker hub (use the slim version)

```
docker login

# -d detached mode, -it basically enables terminal mode, -p expose port
# right after container name can specify path to data store: -v /data/OracleDBData:/ORCL
docker run -d -it --name oracle -p 18080:8080 -p 1521:1521 -v /Users/alex4/docker/oracle:/u01/app/oracle sath89/oracle-xe-11g

# too see logs
docker logs oracle

# to retire container
docker container stop oracle
docker container rm oracle
```

3. UI tool to explore DB State
Oracle SQL Developer
username: system, password: oracle, hostname: localhost, port: 1521, SID: xe

4. GIT repo
https://github.com/laxa1986/demo-project
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

# -d detached mode
# -it basically enables terminal mode
# -p expose port
# right after container name can specify path to data store: -v /data/OracleDBData:/ORCL
docker run -d -it --name oracle -p 1521:1521 store/oracle/database-enterprise:12.2.0.1-slim

# too see logs
docker logs oracle

# to retire container
docker container rm oracle
```

user=sys, pw=Oradoc_db1
DB_SID=ORCLCDB, DB_PDB=ORCLPDB1, DB_DOMAIN=localdomain

links:
https://hub.docker.com/_/oracle-database-enterprise-edition
https://docs.docker.com/engine/reference/run/#foreground
https://hub.docker.com/u/chekulaevalexey/content/sub-885eaa34-3b35-423c-8984-0fbe18e9271a

3. JDBC
Connected to DB via JDBC driver
Created DB table and checked how sql statements work

4. UI tool to explore DB State
Oracle SQL Developer

5. GIT repo
done with github:
https://github.com/laxa1986/demo-project

6. Project structure (min)

7. Oracle create schema and user 


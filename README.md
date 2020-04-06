# A Minimal JDBC application with Docker

## Run
This application use a experimental feature of docker (buildkit) to build faster (see docker/MyApp/Dockerfile). 
```COMPOSE_DOCKER_CLI_BUILD=1 DOCKER_BUILDKIT=1 docker-compose up```

## JDBC
 * Credentials and JDBC URL are set in 'src/main/ressources/db.properties', they should be moved to env. variables to be set in docker-compose.yml
 * Database ressources are manage with a datasource (with Apache DBCP). see ```fr.univtln.bruno.i211.dao.utils.DBCPDataSource```
 * A very simple DAO is illustrated in ```fr.univtln.bruno.i211.dao.ActorDAO``` which implements the interface ```fr.univtln.bruno.i211.dao.DAO```
 
## Docker
 * The database is initialized by extension of a standard images. See docker/postgresql-dvdrental/ . 
 The script init-db.sh is automatically executed by the standard postgresql image at first startup. 
 * The maven java app is compiled in two stages but with a mounted ~/.m2
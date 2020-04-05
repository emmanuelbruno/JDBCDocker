# A Minimal JDBC application with Docker

## Run
```COMPOSE_DOCKER_CLI_BUILD=1 DOCKER_BUILDKIT=1 docker-compose up```

## JDBC
 * Credentials and JDBC URL are set in 'src/main/ressources/db.properties', they should be moved to env. variables to be set in docker-compose.yml
 * Database ressources are manage with a datasource (with Apache DBCP). see ```fr.univtln.bruno.i211.dao.utils.DBCPDataSource```
 * A very simple DAO is illustrated in ```fr.univtln.bruno.i211.dao.ActorDAO``` which implements the interface ```fr.univtln.bruno.i211.dao.DAO```
 
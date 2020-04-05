#!/bin/bash
set -e

export PGPASSWORD="changeme"

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE DATABASE dvdrental;
EOSQL

pg_restore -U "$POSTGRES_USER" -d dvdrental dvdrental.tar

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname dvdrental <<-EOSQL
    CREATE USER dvdrentaldba WITH PASSWORD 'dvdrentalpassword';
    GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO dvdrentaldba;
    GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO dvdrentaldba;
EOSQL

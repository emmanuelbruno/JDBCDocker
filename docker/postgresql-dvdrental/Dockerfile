FROM postgres:9.6.17-alpine
RUN wget https://sp.postgresqltutorial.com/wp-content/uploads/2019/05/dvdrental.zip
RUN unzip dvdrental.zip && rm dvdrental.zip
ADD init-db.sh /docker-entrypoint-initdb.d/

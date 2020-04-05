# syntax=docker/dockerfile:experimental

## WARNING This build requires experimental features (native build and buildkit)
## run with the following command
## COMPOSE_DOCKER_CLI_BUILD=1 DOCKER_BUILDKIT=1 docker-compose up --build

## If needed invalidate the cache with
#docker builder prune --filter type=exec.cachemount

FROM maven:3.6.3-jdk-11-openj9 as maven
WORKDIR /build
COPY . /build
RUN --mount=type=cache,target=/root/.m2 mvn -B -e -T 1C verify

FROM adoptopenjdk:11-jre-hotspot
#ENTRYPOINT exec java $JAVA_OPTS -jar /app/my-app.jar
ENTRYPOINT ["java","-jar","/app/my-app.jar"]
COPY --from=maven /build/target/*jar-with-dependencies.jar /app/my-app.jar


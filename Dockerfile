FROM maven:3.6.3-jdk-8 as target
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn package

# Step : Package image
FROM openjdk:8-jre-alpine
CMD exec java $JAVA_OPTS -jar /app/my-app.jar
COPY --from=target /build/target/*jar-with-dependencies.jar /app/my-app.jar

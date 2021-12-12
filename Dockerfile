FROM maven:3-openjdk-14 AS java-build
LABEL name="vikas"

WORKDIR /app
COPY pom.xml .
RUN mvn clean dependency:go-offline
COPY . .
RUN mvn clean package -Dmaven.test.skip=true #TODO: remove skiptest


FROM openjdk:14
COPY --from=java-build /app/target/*.jar /app/app.jar
#RUN wget -O apm-agent.jar https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.18.0.RC1/elastic-apm-agent-1.18.0.RC1.jar
ENTRYPOINT ["java","-Duser.timezone=IST","-Dspring.profiles.active=${PROFILE}","-jar","/app/app.jar"]


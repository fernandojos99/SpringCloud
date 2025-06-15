FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENV EUREKA_URL null

ENTRYPOINT ["java","-jar","/app.jar"]
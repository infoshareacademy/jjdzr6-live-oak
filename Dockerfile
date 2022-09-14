FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY ServiceBoxWeb/.mvn .mvn
COPY ServiceBoxWeb/mvnw .
COPY ServiceBoxWeb/pom.xml .

RUN ./mvnw dependency:go-offline

COPY ServiceBoxWeb/src ./src

CMD ["./mvnw", "spring-boot:run"]
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/emsp-0.0.1-SNAPSHOT.jar app.jar

#ENV SPRING_PROFILES_ACTIVE=prod
#ENV DB_URL=jdbc:mysql://mysql:3306/emspdb
#ENV DB_USERNAME=emspuser
#ENV DB_PASSWORD=emspassword

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
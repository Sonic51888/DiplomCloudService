FROM amazoncorretto:17.0.10

EXPOSE 8888

COPY ./target/Diplom-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
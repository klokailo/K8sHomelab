FROM gradle:jdk17 as build
WORKDIR /app
COPY --chown=gradle:gradle . /app
RUN gradle build --no-daemon

# Start a new stage to create a smaller image
FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
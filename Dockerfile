# --- Stage 1: build the fat JAR ---
FROM gradle:jdk21 AS build

# Work inside /home/gradle/src
WORKDIR /home/gradle/src

# Copy project files into the image
COPY --chown=gradle:gradle . .

# Build the fat JAR created by the Ktor plugin
RUN gradle buildFatJar --no-daemon

# --- Stage 2: run the app ---
FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

# Render will set PORT env variable; 8080 is just a default fallback
ENV PORT=8080
EXPOSE 8080

# Copy the fat jar from the build stage
# This matches the ***-all.jar that buildFatJar generates
COPY --from=build /home/gradle/src/build/libs/*-all.jar app.jar

# Run the Ktor server
CMD ["java", "-jar", "/app/app.jar"]

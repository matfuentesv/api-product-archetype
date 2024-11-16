# Base image
FROM openjdk:21-ea-1-jdk

# Set working directory
WORKDIR /app

# Copy application JAR file
COPY target/api-product-archetype-1.0.0.jar /app/app.jar

# Copy Oracle Wallet
COPY Wallet_NKYTD6DF15M2NHAO /app/wallet/

# Copy application.properties
COPY src/main/resources/application.properties /app/application.properties

# Expose port for the API
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-Dspring.config.location=file:/app/application.properties", "-jar", "/app/app.jar"]

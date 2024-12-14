# Usa una imagen base de Java
FROM openjdk:17-ea-24-oracle

# Establece el directorio de trabajo en el contenedor
WORKDIR /miapp

# Copia el archivo JAR construido al contenedor
COPY target/api-product-archetype-1.0.0.jar app.jar

COPY Wallet_GYBK4N4OFESPGB8J /miapp/oracle_wallet
# Expone el puerto en el que la aplicación escuchará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
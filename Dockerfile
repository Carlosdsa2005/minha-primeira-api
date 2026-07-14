# 1. Usa a imagem oficial do Java 21 otimizada e leve (Alpine)
FROM eclipse-temurin:21-jdk-alpine

# 2. Define a pasta de trabalho dentro do contêiner
WORKDIR /app

# 3. Copia o arquivo .jar gerado pelo Maven (o executável da sua API) para dentro do contêiner
COPY target/*.jar app.jar

# 4. Expõe a porta 8080 (a mesma que configuramos no Tomcat)
EXPOSE 8080

# 5. Comando que o contêiner vai executar ao ligar
CMD ["java", "-jar", "app.jar"]
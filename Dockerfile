FROM openjdk:17
COPY "./target/ParcialPapeleria-1.jar" "app.jar"
EXPOSE 8092
ENTRYPOINT [ "java", "-jar", "app.jar" ]
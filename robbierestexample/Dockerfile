FROM openjdk:8-jdk-alpine
 
# copy the packaged jar file into our docker image
COPY target/restexample-0.0.1-SNAPSHOT.jar /restexample.jar
 
# set the startup command to execute the jar
CMD ["java", "-jar", "/restexample.jar"]
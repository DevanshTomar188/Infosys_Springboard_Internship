FROM openjdk:11
EXPOSE 8080
ADD target/license.jar license.jar
ENTRYPOINT ["java","-jar","license.jar"]
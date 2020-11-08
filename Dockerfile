FROM openjdk:8
add users-mysql.jar users-mysql.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","users-mysql.jar"]
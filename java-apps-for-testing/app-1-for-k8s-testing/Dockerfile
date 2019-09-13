FROM openjdk:11
ADD ./target/app-1-for-k8s-testing-1.0.0.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","app.jar"]
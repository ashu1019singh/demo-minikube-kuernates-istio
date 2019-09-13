FROM openjdk:11
ADD ./target/app-3-for-k8s-testing-1.0.0.jar app.jar
EXPOSE 8094
ENTRYPOINT ["java","-jar","app.jar"]
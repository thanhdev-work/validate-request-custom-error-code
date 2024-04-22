FROM maven:3.6-jdk-11 as build

ENV HOME=/opt/src/app
WORKDIR $HOME
ADD pom.xml $HOME
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
COPY . .
RUN ["mvn", "package"]

FROM openjdk:11-jdk
ENV TZ="Asia/Ho_Chi_Minh"
COPY --from=build opt/src/app/target/*.jar api1.jar
ENTRYPOINT ["java","-jar","api1.jar"]
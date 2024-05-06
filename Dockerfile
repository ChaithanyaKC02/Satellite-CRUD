FROM openjdk:8
EXPOSE 8084
ADD target/spring-boot-docker.war spring-boot-docker.war
ENTRYPOINT ["java","-Doracle.jdbc.timezoneAsRegion=false" ,"-Duser.timezone=CET","-jar","/spring-boot-docker.war"]
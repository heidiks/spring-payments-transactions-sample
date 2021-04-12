FROM openjdk:11.0.10-jre AS base
ENV TZ=America/Sao_Paulo
RUN export HOSTNAME=`hostname`
COPY target/payments-transactions-0.0.1-SNAPSHOT.jar /opt/transaction-app/transaction-app.jar
CMD ["/usr/bin/java", "-jar", "-Dfile.encoding=UTF-8", "/opt/transaction-app/transaction-app.jar"]
EXPOSE 8080
FROM apache/spark:3.5.1

USER root

WORKDIR /app
COPY target/scala-2.12/etl_simple.jar /app/etl_simple.jar

RUN mkdir -p /app/data /app/out && chmod -R 777 /app

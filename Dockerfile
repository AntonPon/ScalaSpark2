FROM p7hb/docker-spark

MAINTAINER Anton Ponomarchuk "ponomarchuk@ucu.edu.ua"

ENV PATH $PATH:/spark/bin/  

COPY ./SparkTesterMVN /app

WORKDIR /app


CMD spark-submit --class Tester --master local target/SparkTesterMVN-1.0-SNAPSHOT.jar

FROM daocloud.io/library/java:8u40-b22
VOLUME /tmp
ADD gexin-docker-demo-1.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
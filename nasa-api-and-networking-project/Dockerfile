FROM openjdk:22-jdk-slim

WORKDIR /app

RUN apt-get update && \
    apt-get install -y maven traceroute && \
    rm -rf /var/lib/apt/lists/*

COPY . .

CMD ["sh", "-c", "mvn clean install && mvn test -Dtest=TestRunner"]

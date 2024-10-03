# NASA API and Networking project
This project implements a set of automated tests for NASA's APIs and networking functionalities using Cucumber and JUnit.

# Technologies Used:
- Java
- Maven
- Cucumber
- JUnit 4
- JSON
- Spring Framework

# Known issue
- Scenario: Perform a traceroute to 8.8.8.8 fails when run from Docker container.

# Docker Run commands
To run the project with Docker, open a terminal in the root folder and execute the following commands:

```bash
docker build -t {your-image-name}:latest .
docker run --rm {your-image-name}:latest
```

Just replace {your-image-name} with the desired name for your Docker image!

# phoebus 
Restful service test for Phoebus interview

## getting started

To run in a Docker container first download source code. In root directory build project using Maven
```
mvn clean package
```

### Running from the command line

Tested using Docker desktop for Windows using openjdk:8-jdk-alpine image. Run docker build and docker run commands from root directory of project

```
docker build -t robbie/docker-package-only-build-restservicetest:1.0-SNAPSHOT .
docker run -d -p 8080:8080 robbie/docker-package-only-build-restservicetest:1.0-SNAPSHOT
```

This will run the container in background mode with the container's port 8080 mapped to our local port 8080

### Example URLs to test restful services

```
http://localhost:8080/account/getforcustomer/1
http://localhost:8080/customer/getforaccount/13657432
http://localhost:8080/account/addForCustomer/1

last example needs a JSON body for the request with an account e.g.

{
        "accountNumber": 77777777
}

```
Tested using Postman 

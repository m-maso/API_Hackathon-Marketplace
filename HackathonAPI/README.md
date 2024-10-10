# Backend Test for Hackató & Marketplace

<hr>

The following RestAPI was developed as a Backend Test for the Hackató & Marketplace.<br>
This hackathon is organised by Barcelona Activa and Mobile World Capital Barcelona and consists of five challenges that have been chosen to find solutions for the city of Barcelona. These goals are framed within the SDGs (Sustainable Development Goals) of the 2030 Agenda of the United Nations.

<hr>

## Table of contents

1. [Description](#description)
2. [Requirements](#requirements)
3. [Configuration](#configuration)\
   3.1. [Installation](#installation)\
   3.2. [Data Persistence](#persistence)\
   3.3. [Default properties](#properties)
4. [How to use the Project](#usingproject) 
5. [Author](#author)

<hr>

## 1. Description <a name="description"></a>
This project is an API for a web application that manages users and activities. This service allows users registration, the activities management and the import/export of these activities in Json format. 

This project was built with:
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Gradle](https://gradle.org/)
- [MongoDB](https://mongodb.com)
- [Swagger](https://swagger.io)

## 2. Requirements <a name="requirements"></a>
It is important to make sure you have all the following tools installed and correctly configured before trying our app.
- Java SE Development Kit 21
- Gradle 8.10.2 
- IntelliJ IDEA 2024 (or any other IDE you prefer)
- MongoDB
- Postman

## 3. Configuration <a name="configuration"></a>
### 3.1. Installation <a name="installation"></a>
Clone the repository to your local machine
```java Cloning repo
git clone https://github.com/m-maso/API_Hackathon-Marketplace.git
```
Before executing the program, please read all the configuration steps.

### 3.2. Data Persistence <a name="persistence"></a>
Before starting the project, you need to:
- Create your database (activitiesApp) on MongoDB and make sure the application.properties matches your local settings.
- Create two collections: users and activities.  
   
### 3.3. Default properties <a name="properties"></a>
Please ensure your properties file follows this pattern:
```properties
# Server config.
server.port = 8080

# Database config.
spring.data.mongodb.host = localhost
spring.data.mongodb.port = 27017
spring.data.mongodb.database = activitiesApp

# Swagger config.
springdoc.swagger-ui.path = /swagger-ui
```
## 4. How to use the Project  <a name="usingproject"></a>
Before start using the project, make sure you have read the requirements and have followed all the configuration steps detailed above.

If you have not done step 2.1 yet, you can do it now. Clone the remote repository into your local machine:
```java Cloning repo
git clone https://github.com/m-maso/API_Hackathon-Marketplace.git
```
Once you have followed all the installation process you are ready to use the project.
- Connect to MongoDB with the activitiesApp database you created before (maybe with [MonogDB Compass](https://www.mongodb.com/try/download/compass)). You can add the data you will find on the folder mongodb-test-data into your database collections, for testing purposes.
- To execute the program, use a Java IDE and build the project (gradle build) and then run the project (gradle bootRun).
- Open your browser and use [Swagger](http://localhost:8080/swagger-ui) to view the API documentation.
- You can use Postman to test the API endpoints. You can import into your postman workspace the environment and the collection json files that you will find in the postman folder inside the resources folder project.

## 5. Author <a name="author"></a>
This project was develop by: Montse [@m-maso](https://github.com/m-maso)
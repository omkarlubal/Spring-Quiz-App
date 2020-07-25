# Spring Based Quiz Application Backend

This a Java Spring Based Backend System built for a Quiz Application.
Basically provides two responses at the REST Endpoint. (quiz/1)
1) On Get Request: Returns a set of questions and options.
2) On Post Request: From the given response body, checks how many questions are answered correctly and returns a response
describing how many questions are correct.

This application can handle single correct answer, multiple correct answers, and text based answer for a question.

Main source code inside /buildout dolder.

## Usage

A Docker file is already created in DockerizedQuizApp folder. To run it just use the following commands.

```bash
cd DockerizedQuizApp
docker-compose up
```
Another way to run this application, is by using Gradle command. But that way you'll have to modify application.properties 
to accept local mongodb connection, as well as you'll have to populate the mongodb on your own.

To populate the mongo db use the following command

```bash
cd DockerizedQuizApp/dbseed
mongoimport --db quizdb --collection question --jsonArray --file ./initial_data_load.json
```
Then simply use this gradle command in parent directory to run the app.
```bash
./gradlew bootrun
```

## Tech Stack

Spring Boot, MongoDB, Gradle

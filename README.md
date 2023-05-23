Pentechgon microservice
============

A Spring Boot application that uses handles the backend logic for XPot

## Building the application

Use Maven to build the application:

~~~
$ mvn clean package
~~~

- [Project Description](#project-description)
- [Technologies](#technologies)
- [Features](#features)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)

## Project Description

Welcome to XPot, the ultimate savings challenge app! XPot is designed to help consumers develop healthy saving habits by gamifying the saving process. With XPot, you can create your own virtual savings pot and watch it grow as you save money. Challenge your friends, motivate each other, and see who can achieve the longest saving streak and build the biggest XPot!

XPot goes beyond just being a savings app. It fosters a supportive community of like-minded individuals who are on a mission to improve their financial discipline and achieve their savings goals together.

## Technologies

Java SpringBoot using JWT to validate user access

## Getting Started

~~~
$ mvn clean package
~~~
This will compile the source code and package it into an executable JAR file.

## API Documentation
The backend code provides the following APIs:

Login: POST /api/v1/login

This API is used for user authentication. It expects a JSON payload containing the user's credentials and returns a JWT token upon successful authentication.
Jackpot Status: GET /api/v1/user/jackpot

This API retrieves the status of the jackpot for the user. It requires a valid JWT token for authentication.
Savings Summary: GET /api/v1/summary

This API fetches the summary of the user's savings. It requires a valid JWT token for authentication.
Challenge Initiate: POST /api/v1/challenge

This API is used to initiate a savings challenge. It expects a JSON payload containing the challenge details and requires a valid JWT token for authentication.
Please refer to the API documentation or code implementation for more details on request/response structures and any additional parameters required.

## Contributing
Contributions to the project are welcome. If you want to contribute, please follow the standard guidelines for pull requests and issues (put in a pull request / submit a new issue)




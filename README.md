# Portfilio project IDATT2003 - 2024. Group 51

## Name
Chaos game application

STUDENT NAME = "Konrad Øye Seime"
STUDENT ID = 10094

STUDENT NAME = "Mathias Hølestøl"
STUDENT ID = 10060

## Description
This application is a javafx application that creates fractals with Chaos game, or Julia sets. The App lets the user edit various aspects about the fractals, save and load fractals. Generate random fractals, change color and save the generated fractal as an image.

## Project structure
The project is buildt with Maven, and is divided into two folders. main and test.

The main folder contains all source code, and is split into Model View and Controller.
Model has folders Called math, transform, chaosgame, observer, and factory. Files in model handle the core logic, and don't depend on any files outside of model.

View also has a folder for custom ui components. All files in View represent a part of the graphical interface.

Controller holds files that handle input from view, and use the model classes to return information.

The test folder contains the unit tests for the application.

## Link to repository
https://gitlab.stud.idi.ntnu.no/konrados/idatt2003_group51_chaos_game

## How to run the project
The class App that extends javafx.Application, is the class that starts the system. To run it in the IDE, you can use buildt in methods to run javafx, like setting run confuguration to javafx:run.

To run the system from command line, you can use the following command in the root folder
```bash
mvn javafx:run
```

## How to run the tests
To run the tests in the IDE, you can run the test files individually, or all at once with buildt in functionality.

To run the tests from the command line, use this command in the root folder
```bash
mvn clean test
```


## Authors and acknowledgment
One method in the JuliaSetGame class is inspired by the pseudocode in the following article
https://en.wikipedia.org/wiki/Julia_set


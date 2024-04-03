package org.example.controller;

import org.example.model.ChaosGame;
import org.example.model.ChaosGameDescription;
import org.example.model.ChaosGameFileHandler;

import java.util.Scanner;

/*
  * The controller class for the Chaos Game program.
  * it contains the methods that the user can interact with in the terminal.
 */
public class ChaosGameController {
  ChaosGameFileHandler chaosGameFileHandler;

  public ChaosGameController() {
    chaosGameFileHandler = new ChaosGameFileHandler();
  }

  /*
    * Method that gets the user input, verifies it and returns it.
    * @param scanner is the scanner object that reads the input.
    * @return the integer value of the user input.
   */

  public int getValidInput(Scanner scanner) {
    int input = 0;
    System.out.println("Enter here: ");
    while (true) {
      scanner.hasNextInt();
      try {
        input = scanner.nextInt();
        if (0 > input && input < 5) {
          System.out.println("Error! Enter a integer 1-5!");
        } else {
          break;
        }
      } catch (Exception e) {
        System.out.println("Error! Enter a integer 1-5!!");
        scanner.next();
      }
    }
    return input;
  }

  /*
    * Method that reads a description from a file. it also needs the user to enter the path to the file.
    * @return the ChaosGameDescription object that was read from the file.
   */
  public ChaosGameDescription userReadFromFile() {
    Scanner scanner = new Scanner(System.in);
    ChaosGameDescription description;
    String inputPath;
    System.out.println("Enter the text file you want to read from (Chaos Game/chaosFiles/...): ");
    while (true) {
      scanner.hasNextLine();
      try {
        inputPath = scanner.nextLine();
        description = chaosGameFileHandler.readFromFile("Chaos Game/chaosFiles/" + inputPath);
        if (description != null) {
          return description;
        }
      } catch (Exception e) {
        System.out.println("Error! Enter a valid path!");
      }
    }
  }

  /*
    * Method that writes a description to a file. it also needs the user to enter the path to the file.
    * @param description is the ChaosGameDescription object that is to be written to the file.
   */
  public void userWriteToFile(ChaosGameDescription description) {
    Scanner scanner = new Scanner(System.in);
    int choice;
    System.out.println("Enter the path to the file you want to write to: ");
    System.out.println("1: Juliamengde.txt");
    System.out.println("2: sierpinski.txt");
    while (true) {
      scanner.hasNextInt();
      try {
        choice = scanner.nextInt();
        if (choice == 1) {
          chaosGameFileHandler.writeToFile(description, "Chaos Game/chaosFiles/test/guiJuliamengdeOutput.txt");
          System.out.println("description written to guiJuliamengdeOutput.txt");
        } else if (choice == 2) {
          chaosGameFileHandler.writeToFile(description, "Chaos Game/chaosFiles/test/guiSierpinskiOutput.txt");
          System.out.println("description written to guiSierpinskiOutput.txt");
        } else {
          System.out.println("Not a valid choice!");
          break;
        }
      } catch (Exception e) {
        if (description != null) {
          System.out.println("Error! Unable to write to file!");
          scanner.next();
        }
        System.out.println("Error! No values to write to file! Make sure to get values first by reading a file.");
        break;
      }
    }
  }

  /*
* Method that gets the number of iterations the user wants to run. Method also verifies the input.
    * @return the number of iterations the user wants to run.
   */

  public int userSetIterations() {
    Scanner scanner = new Scanner(System.in);
    int iterations;
    System.out.println("Enter the number of iterations you want to run: ");
    while (true) {
      scanner.hasNextInt();
      try {
        iterations = scanner.nextInt();
        if (iterations > 0) {
          return iterations;
        } else {
          System.out.println("Error! Enter a positive integer!");
        }
      } catch (Exception e) {
        System.out.println("A non-integer value is invalid input!");
        scanner.next();
      }
    }
  }

  /*
    * Method that prints the ASCII fractal to the terminal.
    * @param description is the ChaosGameDescription object to be used to print the fractal.
    * @param runSteps is the number of iterations the fractal to be run.
   */
  public void userPrintAsciiFractal(ChaosGameDescription description, int runSteps) {

    try {
      ChaosGame chaosGame = new ChaosGame(description, 60, 60);
      chaosGame.runSteps(runSteps);
      System.out.println(chaosGame.getCanvas().toString());
    } catch (Exception e) {
      System.out.println("Error! Unable to print ASCII fractal!");
    }
  }
}

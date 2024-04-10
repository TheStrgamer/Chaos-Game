package org.example.view;

import org.example.controller.ChaosGameController;
import org.example.model.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The GUI class for the Chaos Game program. it displays information to the user in a menu system.
 */
public class Gui {

  private ChaosGameController chaosGameController;
  private Scanner scanner;
  private ChaosGameDescription chaosGameDescription;
  private int runSteps;

  private static final int READ_DESCRIPTION = 1;
  private static final int WRITE_DESCRIPTION = 2;
  private static final int RUN_ITERATIONS = 3;
  private static final int PRINT_ASCII_FRACTAL = 4;
  private static final int EXIT_PROGRAM = 5;

  /**
   * Constructor for the GUI class. it initializes the transforms and the scanner as well as a
   * default value for the runSteps and description.
   */
  public Gui() {
    chaosGameController = new ChaosGameController();
    scanner = new Scanner(System.in);
    this.runSteps = 10000;
  }

  /**
   * Method that generates the introduction prompt for the user. it displays the different options
   * the user can choose from.
   */
  public void generateIntroductionPrompt() {
    System.out.println("\nWelcome to the Chaos Game menu:");
    System.out.println("1. Read description from file");
    System.out.println("2. Write description to file");
    System.out.println("3. Set number of iterations");
    System.out.println("4. print ASCII-fractal");
    System.out.println("5. Exit program");
  }

  /**
   * Method that runs the GUI. it contains a while loop that runs until the user chooses to exit the
   * program. it also contains a switch statement that runs the different methods in the controller
   * class based on the user input.
   */
  public void run() {
    boolean running = true;
    while (running) {
      generateIntroductionPrompt();
      int choice = chaosGameController.getValidInput(scanner);
      switch (choice) {
        case READ_DESCRIPTION:
          chaosGameDescription = chaosGameController.userReadFromFile();
          setDescription(chaosGameDescription);
          System.out.println(chaosGameDescription.toString());
          break;

        case WRITE_DESCRIPTION:
          chaosGameController.userWriteToFile(getDescription());
          break;

        case RUN_ITERATIONS:
          setRunSteps(chaosGameController.userSetIterations());
          break;

        case PRINT_ASCII_FRACTAL:
          chaosGameController.userPrintAsciiFractal(getDescription(), getRunSteps());
          break;

        case EXIT_PROGRAM:
          System.out.println("Avslutter programmet");
          running = false;
          break;
        default:
          System.out.println("Ugyldig valg, prøv igjen");
      }
    }
    scanner.close();
  }

  /**
   * Method that sets the number of iterations the user wants to run.
   *
   * @param runSteps is the number of iterations the user wants to run.
   */
  private void setRunSteps(int runSteps) {
    this.runSteps = runSteps;
  }

  /**
   * Method that gets the number of iterations the user wants to run.
   *
   * @return the number of iterations the user wants to run.
   */
  private int getRunSteps() {
    return runSteps;
  }

  /**
   * Method that sets the description of the chaos game.
   *
   * @param chaosGameDescription is the description of the chaos game.
   */

  private void setDescription(ChaosGameDescription chaosGameDescription) {
    this.chaosGameDescription = chaosGameDescription;
  }

  /**
   * Method that gets the description of the chaos game.
   *
   * @return the description of the chaos game.
   */
  private ChaosGameDescription getDescription() {
    return chaosGameDescription;
  }
}

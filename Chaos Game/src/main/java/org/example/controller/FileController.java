package org.example.controller;

import org.example.model.chaosGame.ChaosGame;
import org.example.model.chaosGame.ChaosGameDescription;
import org.example.model.chaosGame.ChaosGameFileHandler;

/**
 * <h1>FileController</h1>
 * The controller class for handling file operations in the application. this class has methods
 * for writing and reading chaos game descriptions to and from files through file explorer.
 */
public class FileController {
  private final ChaosGameFileHandler chaosGameFileHandler;
  private final MainController mainController;

  /**
   * Constructor for the FileController class.
   *
   * @param maincontroller        the main controller for the application. Used for switching
   *                              between views.
   * @param chaosGameFileHandler  the backend file handler for the application. Used for reading and
   *                              writing chaos game descriptions to and from files.
   */
  public FileController(MainController maincontroller, ChaosGameFileHandler chaosGameFileHandler) {
    this.chaosGameFileHandler = chaosGameFileHandler;
    this.mainController = maincontroller;
  }

  /**
   * Method for writing the current chaos game description to a file.
   *
   * @param filePath the path to the file to write to.
   */
  public void writeToFile(String filePath) {
    try {
      ChaosGameDescription currentDescriptions = mainController.getChaosGameDescription();
      chaosGameFileHandler.writeToFile(currentDescriptions, filePath);
      System.out.println("File saved successfully");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Method for reading a chaos game description from a file.
   *
   * @param filePath the path to the file to read from.
   */
  public void readFromFile(String filePath) {
    try {
      ChaosGameDescription readDescription = chaosGameFileHandler.readFromFile(filePath);
      mainController.setCurrentDescription(readDescription);
      mainController.switchToChaosGameView();
      System.out.println("File read successfully");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

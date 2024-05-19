package org.example.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.model.chaosGame.ChaosGame;
import org.example.model.chaosGame.ChaosGameDescription;
import org.example.model.chaosGame.ChaosGameFileHandler;
import org.example.model.factory.ChaosGameDescriptionFactory;


/**
 * <h1>MainController</h1>
 * The main controller class for the application. Responsible for creating views and controllers,
 * and switching between the pages.
 */
public class MainController {

  private final ChaosGameController chaosGameController;
  private final ModifyDescriptionController modifyDescriptionController;
  private final ChaosGameDescriptionFactory chaosGameDescriptionFactory;
  private final FileController fileController;
  private final ChaosGameFileHandler chaosGameFileHandler;

  private ChaosGameDescription currentDescription;
  private final ChaosGame chaosGame;

  private final Stage stage;

  private final int originalWidth = 800;
  private final int originalHeight = 600;

  private int currentWidth = 800;
  private int currentHeight = 600;


  /**
   * Constructor for the MainController class.
   *
   * @param stage the primary stage for the application.
   */
  public MainController(Stage stage) {
    this.stage = stage;
    stage.setTitle("Chaos Game");
    chaosGameDescriptionFactory = new ChaosGameDescriptionFactory();
    chaosGameFileHandler = new ChaosGameFileHandler();
    currentDescription = chaosGameDescriptionFactory.createDescription("Sierpinski");
    chaosGame = new ChaosGame(currentDescription, currentWidth-30, currentHeight-100);

    chaosGameController = new ChaosGameController(this, chaosGame);
    fileController = new FileController(this, chaosGameFileHandler);
    modifyDescriptionController = new ModifyDescriptionController(this, currentDescription);
    chaosGame.addObserver(modifyDescriptionController);
    chaosGame.addObserver(chaosGameController);

    stage.widthProperty().addListener((obs, oldVal, newVal) -> changeScale(newVal.intValue(), currentHeight));
    stage.heightProperty().addListener((obs, oldVal, newVal) -> changeScale(currentWidth, newVal.intValue()));
  }


  /**
   * Method for switching to the Chaos Game view.
   */
  public void switchToChaosGameView() {
    Scene scene = new Scene(chaosGameController.getLayout(), originalWidth, originalHeight);
    stage.setScene(scene);
  }

  /**
   * Method for switching to the Modify Description view.
   */
  public void switchToDescriptionView() {
    Scene scene = new Scene(modifyDescriptionController.getLayout(), originalWidth, originalHeight);
    stage.setScene(scene);
  }

  /**
   * method for setting the current description of the Chaos Game.
   * sets the change description combobox empty.
   *
   * @param description the description to set as the current description.
   */
  public void setCurrentDescription(ChaosGameDescription description) {
    currentDescription = description;
    chaosGame.setDescription(currentDescription);
    chaosGameController.setComboBoxEmpty();
  }

  /**
   * Method for setting the current description of the Chaos Game from a string.
   *
   * @param description the name of the description to set as the current description.
   */
  public void setCurrentDescription(String description) {
    currentDescription = chaosGameDescriptionFactory.createDescription(description);
    chaosGame.setDescription(currentDescription);
  }

  /**
   * Method for getting the current description of the Chaos Game.
   *
   * @return the current description of the Chaos Game.
   */

  public ChaosGameDescription getChaosGameDescription() {
    return chaosGame.getDescription();
  }

  /**
   * Method for getting the stage of the application.
   *
   * @return the stage of the application.
   */
  public Stage getStage() {
    return this.stage;
  }

  /**
   * Method changing the scale of the canvas and the description view.
   *
   */
  private void changeScale(int width, int height) {
    this.currentWidth = width;
    this.currentHeight = height;
    chaosGame.setCanvasSize(currentWidth-30, currentHeight-100);
    modifyDescriptionController.setDescriptionSize(currentWidth-200, currentHeight-135);
  }


  /**
   * Method for saving the current description to a file.
   */
  public void saveToFile() {
    fileController.writeToFile(currentDescription);
  }

  public void readFromFile() {
    fileController.readFromFile();
  }

  public void saveImageToFile() {
    fileController.saveImageToFile(chaosGameController.getImage());
  }
}

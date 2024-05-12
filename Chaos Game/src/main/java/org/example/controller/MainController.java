package org.example.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.model.ChaosGame;
import org.example.model.ChaosGameDescription;
import org.example.model.ChaosGameDescriptionFactory;


/**
 * <h1>MainController</h1>
 * The main controller class for the application. Responsible for creating views and controllers,
 * and switching between the pages.
 */
public class MainController {

  private final ChaosGameController chaosGameController;
  private final ModifyDescriptionController modifyDescriptionController;
  private final ChaosGameDescriptionFactory chaosGameDescriptionFactory;

  private ChaosGameDescription currentDescription;
  private final ChaosGame chaosGame;

  private Stage stage;

  private final int originalWidth = 800;
  private final int originalHeight = 600;

  private int currentWidth = 800;
  private int currentHeight = 600;

  private int imageWidth = 800;
  private int imageHeight = 500;

  /**
   * Constructor for the MainController class.
   *
   * @param stage the primary stage for the application.
   */
  public MainController(Stage stage) {
    this.stage = stage;
    stage.setTitle("Chaos Game");
    chaosGameDescriptionFactory = new ChaosGameDescriptionFactory();
    currentDescription = chaosGameDescriptionFactory.createDescription("Sierpinski");
    chaosGame = new ChaosGame(currentDescription, imageWidth, imageHeight);

    chaosGameController = new ChaosGameController(this, chaosGame);
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
   *
   * @param description the description to set as the current description.
   */
  public void setCurrentDescription(ChaosGameDescription description) {
    currentDescription = description;
    chaosGame.setDescription(currentDescription);
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


  private void changeScale(int width, int height) {
    this.imageWidth = width-30;
    this.imageHeight = height - 100;
    this.currentWidth = width;
    this.currentHeight = height;
    chaosGame.setCanvasSize(imageWidth, imageHeight);
    modifyDescriptionController.setDescriptionSize(currentWidth-200, currentHeight-135);
  }



}

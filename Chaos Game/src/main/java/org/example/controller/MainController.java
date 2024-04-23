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
  private ChaosGame chaosGame;

  private Stage stage;

  private int width = 800;
  private int height = 600;

  private int imageWidth = 800;
  private int imageHeight = 600;

  /**
   * Constructor for the MainController class.
   *
   * @param stage the primary stage for the application.
   */
  public MainController(Stage stage) {
    this.stage = stage;
    chaosGameDescriptionFactory = new ChaosGameDescriptionFactory();
    currentDescription = chaosGameDescriptionFactory.createDescription("Barnsley");
    chaosGame = new ChaosGame(currentDescription, imageWidth, imageHeight);

    chaosGameController = new ChaosGameController(this, chaosGame);
    modifyDescriptionController = new ModifyDescriptionController(this);
  }


  /**
   * Method for switching to the Chaos Game view.
   */
  public void switchToChaosGameView() {
    Scene scene = new Scene(chaosGameController.getLayout(), width, height);
    stage.setScene(scene);
  }

  /**
   * Method for switching to the Modify Description view.
   */
  public void switchToDescriptionView() {
    Scene scene = new Scene(modifyDescriptionController.getLayout(), width, height);
    stage.setScene(scene);
  }

  /**
   * method for setting the current description of the Chaos Game.
   * @param description the description to set as the current description.
   */
  public void setCurrentDescription(ChaosGameDescription description) {
    currentDescription = description;
    chaosGame = new ChaosGame(currentDescription, imageWidth, imageHeight);
    chaosGameController.setChaosGame(chaosGame);
  }

  /**
   * Method for refreshing the image of the Chaos Game.
   */
  public void refreshImage() {
    chaosGameController.refreshImage();
  }


}

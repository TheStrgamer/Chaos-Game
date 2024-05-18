package org.example.controller;

import java.util.Objects;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.model.chaosGame.ChaosGame;
import org.example.model.chaosGame.ChaosGameDescription;
import org.example.model.factory.ChaosGameDescriptionFactory;


/**
 * <h1>MainController</h1>
 * The main controller class for the application. Responsible for creating views and controllers,
 * and switching between the pages.
 */
public class MainController {

  private final ChaosGameController chaosGameController;
  private final ModifyDescriptionController modifyDescriptionController;
  private final PopupController popupController;
  private final ChaosGameDescriptionFactory chaosGameDescriptionFactory;

  private ChaosGameDescription currentDescription;
  private final ChaosGame chaosGame;

  private int currentWidth = 800;
  private int currentHeight = 600;

  private final int minCanvasWidth = 600;
  private final int minCanvasHeight = 350;

  Scene chaosGameScene;


  /**
   * Constructor for the MainController class.
   *
   * @param stage the primary stage for the application.
   */
  public MainController(Stage stage) {
    stage.setTitle("Chaos Game");

    chaosGameDescriptionFactory = new ChaosGameDescriptionFactory();
    currentDescription = chaosGameDescriptionFactory.createDescription("Sierpinski");
    chaosGame = new ChaosGame(currentDescription, currentWidth - 30, currentHeight - 100);

    chaosGameController = new ChaosGameController(this, chaosGame);
    modifyDescriptionController = new ModifyDescriptionController(this, currentDescription);
    popupController = new PopupController();

    chaosGame.addObserver(modifyDescriptionController);
    chaosGame.addObserver(chaosGameController);

    int originalWidth = 800;
    int originalHeight = 600;
    chaosGameScene = new Scene(chaosGameController.getLayout(), originalWidth, originalHeight);
    stage.setScene(chaosGameScene);
    setStageListeners(stage);

    chaosGameScene.getStylesheets().add(
        Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
  }

  /**
   * Sets the stage listeners for the primary stage.
   *
   * @param stage the primary stage for the application.
   */
  private void setStageListeners(Stage stage) {
    stage.widthProperty().addListener((obs, oldVal, newVal) -> {
      if (newVal.intValue() < minCanvasWidth) {
        stage.setWidth(minCanvasWidth);
        return;
      }
      changeScale(newVal.intValue(), currentHeight);
    });
    stage.heightProperty().addListener((obs, oldVal, newVal) -> {
      if (newVal.intValue() < minCanvasHeight) {
        stage.setHeight(minCanvasHeight);
        return;
      }
      changeScale(currentWidth, newVal.intValue());
    });
    stage.setOnCloseRequest(e -> popupController.closePopup());
  }

  /**
   * Opens the modify description popup.
   */
  public void openModifyPopup() {
    popupController.showPopup(modifyDescriptionController.getLayout(), 375, 500);
  }

  /**
   * Sets the current description of the Chaos Game. sets the change description
   * combobox empty.
   *
   * @param description the description to set as the current description.
   */
  public void setCurrentDescription(ChaosGameDescription description) {
    currentDescription = description;
    chaosGame.setDescription(currentDescription);
    chaosGameController.setComboBoxEmpty();
  }

  /**
   * Sets the current description of the Chaos Game from a string.
   *
   * @param description the name of the description to set as the current description.
   */
  public void setCurrentDescription(String description) {
    currentDescription = chaosGameDescriptionFactory.createDescription(description);
    chaosGame.setDescription(currentDescription);
  }


  /**
   * Changes the scale of the canvas and the description view.
   */
  private void changeScale(int width, int height) {
    this.currentWidth = width;
    this.currentHeight = height;
    chaosGameController.setCanvasSize(currentWidth, currentHeight);
  }


}

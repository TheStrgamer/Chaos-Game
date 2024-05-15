package org.example.controller;

import java.util.ArrayList;
import java.util.List;
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
  private final ChaosGameDescriptionFactory chaosGameDescriptionFactory;

  private ChaosGameDescription currentDescription;
  private final ChaosGame chaosGame;

  private final Stage stage;

  private final int originalWidth = 800;
  private final int originalHeight = 600;

  private int currentWidth = 800;
  private int currentHeight = 600;

  private final int minCanvasWidth = 200;
  private final int minCanvasHeight = 200;

  List<Scene> scenes;


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
    chaosGame = new ChaosGame(currentDescription, currentWidth - 30, currentHeight - 100);

    chaosGameController = new ChaosGameController(this, chaosGame);
    modifyDescriptionController = new ModifyDescriptionController(this, currentDescription);
    chaosGame.addObserver(modifyDescriptionController);
    chaosGame.addObserver(chaosGameController);

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

    scenes = new ArrayList<>();
    scenes.add(new Scene(chaosGameController.getLayout(), originalWidth, originalHeight));
    scenes.add(new Scene(modifyDescriptionController.getLayout(), originalWidth, originalHeight));

    scenes.get(0).getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

    scenes.get(1).getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

  }


  /**
   * Method for switching to the Chaos Game view.
   */
  public void switchToChaosGameView() {
    switchToSceneView(0);
  }

  /**
   * Method for switching to the Modify Description view.
   */
  public void switchToDescriptionView() {
    switchToSceneView(1);
  }

  /**
   * Method for switching to a specific scene.
   *
   * @param index the index of the scene to switch to.
   */
  private void switchToSceneView(int index) {
    stage.setScene(scenes.get(index));
    stage.show();
  }

  /**
   * method for setting the current description of the Chaos Game. sets the change description
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
   * Method for setting the current description of the Chaos Game from a string.
   *
   * @param description the name of the description to set as the current description.
   */
  public void setCurrentDescription(String description) {
    currentDescription = chaosGameDescriptionFactory.createDescription(description);
    chaosGame.setDescription(currentDescription);
  }


  /**
   * Method changing the scale of the canvas and the description view.
   */
  private void changeScale(int width, int height) {
    this.currentWidth = width;
    this.currentHeight = height;
    chaosGameController.setCanvasSize(currentWidth - 30, currentHeight - 100);
    modifyDescriptionController.setDescriptionSize(currentWidth - 200, currentHeight - 135);
  }

}

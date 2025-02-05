package org.example.controller;

import java.util.Objects;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.model.chaosgame.ChaosGame;
import org.example.model.chaosgame.ChaosGameDescription;
import org.example.model.chaosgame.JuliaSetGame;
import org.example.model.factory.ChaosGameDescriptionFactory;
import org.example.view.ErrorHandlingView;


/**
 * <h2>MainController.</h2>
 * <p>
 * The main controller class for the application. Responsible for creating controllers, and handling
 * communication between them.
 * </p>
 *
 * @version 1.0.0
 * @since 0.3.0
 */
public class MainController {

  private final ChaosGameController chaosGameController;
  private final ModifyDescriptionController modifyDescriptionController;
  private final PopupController popupController;
  private final ChaosGameDescriptionFactory chaosGameDescriptionFactory;
  private final FileController fileController;

  private ChaosGameDescription currentDescription;

  private int currentWidth = 800;
  private int currentHeight = 600;

  private final int minCanvasWidth = 600;
  private final int minCanvasHeight = 350;

  private final Stage stage;


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
    ChaosGame chaosGame = new ChaosGame(currentDescription, currentWidth - 30, currentHeight - 100);
    JuliaSetGame juliaSetGame = new JuliaSetGame(
        chaosGameDescriptionFactory.createDescription("EmptyJulia"), currentWidth - 30,
        currentHeight - 100, 255, 3.0);

    chaosGameController = new ChaosGameController(this, chaosGame, juliaSetGame);
    fileController = new FileController(this);
    modifyDescriptionController = new ModifyDescriptionController(this, currentDescription);
    popupController = new PopupController();

    chaosGame.addObserver(modifyDescriptionController);
    chaosGame.addObserver(chaosGameController);
    juliaSetGame.addObserver(chaosGameController);
    juliaSetGame.addObserver(modifyDescriptionController);

    int originalWidth = 800;
    int originalHeight = 600;
    Scene chaosGameScene = new Scene(chaosGameController.getLayout(), originalWidth,
        originalHeight);
    stage.setScene(chaosGameScene);
    setStageListeners();
    chaosGameController.setKeyListeners(chaosGameScene);

    chaosGameScene.getStylesheets().add(
        Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
  }

  /**
   * Sets the stage listeners for the primary stage.
   */
  private void setStageListeners() {
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
   * Sets the current description of the Chaos Game. sets the change description combobox empty.
   *
   * @param description the description to set as the current description.
   */
  public void setCurrentDescription(ChaosGameDescription description) {
    if (description == null) {
      return;
    }
    currentDescription = description;
    chaosGameController.setDescription(currentDescription);
    chaosGameController.setComboBoxEmpty();
  }

  /**
   * Sets the current description of the Chaos Game from a string.
   *
   * @param description the name of the description to set as the current description.
   */
  public void setCurrentDescription(String description) {
    currentDescription = chaosGameDescriptionFactory.createDescription(description);
    chaosGameController.setDescription(currentDescription);
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
   * Changes the scale of the canvas and the description view.
   */
  private void changeScale(int width, int height) {
    this.currentWidth = width;
    this.currentHeight = height;
    chaosGameController.setCanvasSize(currentWidth, currentHeight);
  }

  /**
   * Method for saving the current description to a file.
   */
  public void saveToFile() {
    fileController.writeToFile(currentDescription);
  }

  /**
   * Method for reading a description from a file.
   */
  public void readFromFile() {
    try {
      setCurrentDescription(fileController.readFromFile());
    } catch (Exception e) {
      showErrorPopup("An error occurred when trying to load a file: \n" + e.getMessage());
    }

  }

  /**
   * Method for saving the current image to a file.
   */
  public void saveImageToFile() {
    fileController.saveImageToFile(chaosGameController.getImage());
  }

  /**
   * Method for displaying error message.
   *
   * @param errorMessage a String with description of the error
   */

  public void showErrorPopup(String errorMessage) {
    popupController.showErrorPopup(new ErrorHandlingView(errorMessage), 300, 100);
  }
}

package org.example.controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.model.ChaosGame;
import org.example.model.ChaosGameDescription;
import org.example.model.ChaosGameDescriptionFactory;
import org.example.view.ChaosGameView;
import org.example.view.ModifyDescriptionView;

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
  private int imageHeight = 500;

  public MainController(Stage stage) {
    this.stage = stage;
    chaosGameDescriptionFactory = new ChaosGameDescriptionFactory();
    currentDescription = chaosGameDescriptionFactory.createDescription("Barnsley");
    chaosGame = new ChaosGame(currentDescription,imageWidth, imageHeight);

    chaosGameController = new ChaosGameController(this, chaosGame);
    modifyDescriptionController = new ModifyDescriptionController(this);
  }


  public void switchToChaosGameView() {
    Scene scene = new Scene(chaosGameController.getLayout(), width, height);
    stage.setScene(scene);
  }

  public void switchToDescriptionView() {
    Scene scene = new Scene(modifyDescriptionController.getLayout(), width, height);
    stage.setScene(scene);
  }

  public void setCurrentDescription(ChaosGameDescription description) {
    currentDescription = description;
    chaosGame = new ChaosGame(currentDescription,imageWidth, imageHeight);
    chaosGameController.setChaosGame(chaosGame);
  }

  public void refreshImage() {
  chaosGameController.refreshImage();
  }


}

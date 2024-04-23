package org.example.controller;

import javafx.scene.layout.VBox;
import org.example.model.ChaosGameDescription;
import org.example.model.ChaosGameDescriptionFactory;
import org.example.view.ModifyDescriptionView;

public class ModifyDescriptionController {

  private final MainController mainController;
  private final ModifyDescriptionView modifyDescriptionView;

  private final ChaosGameDescriptionFactory chaosGameDescriptionFactory;

  public ModifyDescriptionController(MainController mainController) {
    this.mainController = mainController;
    chaosGameDescriptionFactory = new ChaosGameDescriptionFactory();
    modifyDescriptionView = new ModifyDescriptionView(this, mainController);
  }

  public VBox getLayout() {
    return modifyDescriptionView.getLayout();
  }

  public void chooseDescription(String description) {
    mainController.setCurrentDescription(chaosGameDescriptionFactory.createDescription(description));
    mainController.refreshImage();
    mainController.switchToChaosGameView();
  }

  public void readFromFile() {
    //TODO
    System.out.println("Read from file");
  }


}

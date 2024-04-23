package org.example.controller;

import javafx.scene.layout.VBox;
import org.example.model.ChaosGameDescriptionFactory;
import org.example.view.ModifyDescriptionView;

/**
 * <h1>ModifyDescriptionController</h1>
 * The controller class for the Modify Description page of the application. It handles the logic for
 * the Modify Description page, and is responsible for choosing and creating descriptions for the
 * Chaos Game.
 */
public class ModifyDescriptionController {

  private final MainController mainController;
  private final ModifyDescriptionView modifyDescriptionView;

  private final ChaosGameDescriptionFactory chaosGameDescriptionFactory;

  /**
   * Constructor for the ModifyDescriptionController class.
   *
   * @param mainController the main controller for the application. Used for switching between views.
   */
  public ModifyDescriptionController(MainController mainController) {
    this.mainController = mainController;
    chaosGameDescriptionFactory = new ChaosGameDescriptionFactory();
    modifyDescriptionView = new ModifyDescriptionView(this, mainController);
  }

  /**
   * Method for getting the layout of the Modify Description page.
   *
   * @return the layout of the Modify Description page.
   */
  public VBox getLayout() {
    return modifyDescriptionView.getLayout();
  }

  /**
   * Method for choosing a description for the Chaos Game.
   *
   * @param description the description to choose.
   */
  public void chooseDescription(String description) {
    mainController.setCurrentDescription(
        chaosGameDescriptionFactory.createDescription(description));
    mainController.refreshImage();
    mainController.switchToChaosGameView();
  }

  /**
   * Method for reading a description from a file.
   */
  public void readFromFile() {
    //TODO
    System.out.println("Read from file");
  }


}

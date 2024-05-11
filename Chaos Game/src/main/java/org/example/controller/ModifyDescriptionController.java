package org.example.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.VBox;
import org.example.model.ChaosCanvas;
import org.example.model.ChaosGameDescription;
import org.example.model.ChaosGameDescriptionFactory;
import org.example.model.ChaosGameObserver;
import org.example.view.ModifyDescriptionView;

/**
 * <h1>ModifyDescriptionController</h1>
 * The controller class for the Modify Description page of the application. It handles the logic for
 * the Modify Description page, and is responsible for choosing and creating descriptions for the
 * Chaos Game.
 */
public class ModifyDescriptionController implements ChaosGameObserver {

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
   * Method for reading a description from a file.
   */
  public void readFromFile() {
    //TODO
    System.out.println("Read from file");
  }

  /**
   * Method for saving the current description to a file.
   */
  public void saveToFile() {
    //TODO
    System.out.println("Save to file");
  }


  /**
   * Method for listening to changes in the description of the Chaos Game.
   * @param description the description of the Chaos Game.
   */
  @Override
  public void updateDescription(ChaosGameDescription description) {
    modifyDescriptionView.updateDescriptionList();
  }

  /**
   * Method for listening to changes in the canvas of the Chaos Game.
   * @param canvas the canvas of the Chaos Game.
   */
  @Override
  public void updateCanvas(ChaosCanvas canvas) {

  }
}

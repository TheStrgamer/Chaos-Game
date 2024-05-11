package org.example.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.controller.MainController;
import org.example.controller.ModifyDescriptionController;

/**
 * <h1>ModifyDescriptionView</h1>
 * The view class for the Modify Description page of the application.
 * Responsible for displaying the Modify Description page.
 * Implements the PageView interface.
 */
public class ModifyDescriptionView implements PageViewInterface {

  private final ModifyDescriptionController modifyDescriptionController;
  private final MainController mainController;

  private VBox layout;



  /**
   * Constructor for the ModifyDescriptionView class.
   *
   * @param modifyDescriptionController the controller for the Modify Description page.
   * @param mainController             the main controller for the application.
   */
  public ModifyDescriptionView(ModifyDescriptionController modifyDescriptionController,
      MainController mainController) {
    this.modifyDescriptionController = modifyDescriptionController;
    this.mainController = mainController;
    this.layout = createLayout();

  }

  /**
   * Method for creating the layout of the Modify Description page.
   *
   * @return the layout of the Modify Description page.
   */
  private VBox createLayout() {
    VBox layout = new VBox();
    HBox buttonLayout = new HBox();
    Button toChaosGame = new Button("To Chaos Game");

    HBox content = new HBox();
    VBox readAndWrite = new VBox();

    Label descriptionLabel = new Label("Description: ");
    Button readFromFile = new Button("Read description from file");
    Button saveToFile = new Button("Save current description");
    readAndWrite.getChildren().addAll(descriptionLabel, readFromFile, saveToFile);

    readFromFile.setOnAction(event -> modifyDescriptionController.readFromFile());
    saveToFile.setOnAction(event -> modifyDescriptionController.saveToFile());
    toChaosGame.setOnAction(event -> mainController.switchToChaosGameView());

    buttonLayout.getChildren().addAll(toChaosGame);
    layout.getChildren().addAll(buttonLayout, content);

    //Style
    buttonLayout.setStyle(
        "-fx-alignment: center; -fx-spacing: 10px;  -fx-padding: 10px; -fx-background-color: #8f8f8f;");
    editDescription.setStyle("-fx-font-size: 25px;");
    readAndWrite.setStyle(" -fx-spacing: 10px; -fx-background-color: #8f8f8f; -fx-padding: 10px;");
    description.setStyle("-fx-alignment: center; -fx-spacing: 10px; -fx-background-color: #bfbfbf;");

    return layout;
  }

  /**
   * Method for getting the layout of the Modify Description page.
   *
   * @return the layout of the Modify Description page.
   */
  public VBox getLayout() {
    return createLayout();
  }

}

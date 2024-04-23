package org.example.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

    Button toChaosGame = new Button("Chaos Game");

    HBox content = new HBox();

    VBox chooseDescription = new VBox();
    chooseDescription.setStyle("-fx-alignment: center; -fx-spacing: 10px; -fx-background-color: #8f8f8f; -fx-padding: 10px;");

    Label descriptionLabel = new Label("Description: ");

    HBox sierpinski = new HBox();
    Label sierpinskiLabel = new Label("Sierpinski:");
    Button sierpinskiButton = new Button("Choose");
    sierpinski.getChildren().addAll(sierpinskiLabel, sierpinskiButton);

    HBox barnsley = new HBox();
    Label barnsleyLabel = new Label("Barnsley:");
    Button barnsleyButton = new Button("Choose");
    barnsley.getChildren().addAll(barnsleyLabel, barnsleyButton);

    HBox julia = new HBox();
    Label juliaLabel = new Label("Juliamengde:");
    Button juliaButton = new Button("Choose");
    julia.getChildren().addAll(juliaLabel, juliaButton);

    Button readFromFile = new Button("Read from file");

    chooseDescription.getChildren().addAll(descriptionLabel, sierpinski, barnsley, julia, readFromFile);

    content.getChildren().addAll(chooseDescription);

    sierpinskiButton.setOnAction(event -> modifyDescriptionController.chooseDescription("Sierpinski"));
    barnsleyButton.setOnAction(event -> modifyDescriptionController.chooseDescription("Barnsley"));
    juliaButton.setOnAction(event -> modifyDescriptionController.chooseDescription("Julia"));
    readFromFile.setOnAction(event -> modifyDescriptionController.readFromFile());
    toChaosGame.setOnAction(event -> mainController.switchToChaosGameView());

    buttonLayout.getChildren().addAll(toChaosGame);
    layout.getChildren().addAll(buttonLayout);

    //Style
    buttonLayout.setStyle(
        "-fx-alignment: center; -fx-spacing: 10px; -fx-background-color: #8f8f8f; -fx-padding: 10px;");

    layout.getChildren().addAll(content);
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
